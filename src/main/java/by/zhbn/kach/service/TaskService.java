package by.zhbn.kach.service;

import by.zhbn.kach.dto.TaskDTO;
import by.zhbn.kach.dto.TaskReportDTO;
import by.zhbn.kach.model.*;
import by.zhbn.kach.repository.PersonRepository;
import by.zhbn.kach.repository.ProjectRepository;
import by.zhbn.kach.repository.TaskRepository;
import by.zhbn.kach.repository.TaskSpentTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private PersonRepository personRepository;
    private ProjectRepository projectRepository;
    private TaskSpentTimeRepository taskSpentTimeRepository;
    private EmailService emailService;

    @Autowired
    public TaskService(TaskRepository taskRepository, PersonRepository personRepository, ProjectRepository projectRepository, TaskSpentTimeRepository taskSpentTimeRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
        this.taskSpentTimeRepository = taskSpentTimeRepository;
        this.emailService = emailService;
    }

    public List<TaskDTO> getTaskDtoByExecutorUsername(String executorUsername) {
        Person taskExecutor = personRepository.findPersonByUsername(executorUsername);
        List<Task> tasks = taskExecutor.getExecuteTasks();
        List<TaskDTO> tasksDto = new ArrayList<>();

        for (Task task : tasks) {
            tasksDto.add(new TaskDTO(
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getCreatedTime(),
                    task.getFinishedTime(),
                    task.getTaskStatus(),
                    task.getTaskSpentTimes(),
                    taskExecutor.getProject().getName(),
                    task.getTaskSetter().getUsername(),
                    task.getTaskExecutor().getUsername()
            ));
        }


        return tasksDto;
    }

    public List<TaskDTO> getTasksDtoByManagerUsername(String managerUsername) {
        Person manager = personRepository.findPersonByUsername(managerUsername);
        Project project = manager.getProject();
        List<Task> projectTasks = taskRepository.findAllByProject(project);
        List<TaskDTO> tasksDto = new ArrayList<>();

        for (Task projectTask : projectTasks) {
            tasksDto.add(new TaskDTO(
                    projectTask.getId(),
                    projectTask.getName(),
                    projectTask.getDescription(),
                    projectTask.getCreatedTime(),
                    projectTask.getFinishedTime(),
                    projectTask.getTaskStatus(),
                    projectTask.getTaskSpentTimes(),
                    project.getName(),
                    projectTask.getTaskSetter().getFirstName() + " " + projectTask.getTaskSetter().getLastName(),
                    projectTask.getTaskExecutor().getFirstName() + " " + projectTask.getTaskExecutor().getLastName()
            ));
        }
        return tasksDto;
    }

    @Transactional
    public void saveTask(TaskDTO newTaskDto, String managerUsername) {
        Person projectManager = personRepository.findPersonByUsername(managerUsername);
        Person taskExecutor = personRepository.findPersonByUsername(newTaskDto.getTaskExecutorUsername());
        Task newTask = new Task(
                newTaskDto.getName(),
                newTaskDto.getDescription(),
                new Timestamp(System.currentTimeMillis()),
                null,
                TaskStatus.NONEXECUTABLE,
                new ArrayList<TaskSpentTime>(),
                projectManager.getProject(),
                projectManager,
                taskExecutor
        );
        taskRepository.save(newTask);
        emailService.sendEmail(
                newTask.getTaskExecutor().getEmail(),
                "Новая задача в проекте " + newTask.getProject().getName(),
                "Добавлена новая задача \"" + newTask.getName() + "\""
        );
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void startTask(Long id) {
        Task task = taskRepository.findById(id).get();

        TaskSpentTime taskSpentTime = new TaskSpentTime();
        taskSpentTime.setStartedTime(new Timestamp(System.currentTimeMillis()));
        taskSpentTime.setTask(task);

        if (task.getTaskSpentTimes() == null) {
            task.setTaskSpentTimes(new ArrayList<>());
        }
        task.getTaskSpentTimes().add(taskSpentTime);
        task.setTaskStatus(TaskStatus.EXECUTABLE);

        taskSpentTimeRepository.save(taskSpentTime);
        taskRepository.save(task);
    }

    public void stopTask(Long id) {
        Task task = taskRepository.findById(id).get();

        TaskSpentTime taskSpentTime = task.getTaskSpentTimes().get(task.getTaskSpentTimes().size() - 1);
        taskSpentTime.setStoppedTime(new Timestamp(System.currentTimeMillis()));

        task.setTaskStatus(TaskStatus.NONEXECUTABLE);

        taskSpentTimeRepository.save(taskSpentTime);
    }

    public void finishTask(Long id) {
        Task task = taskRepository.findById(id).get();

        TaskSpentTime taskSpentTime = task.getTaskSpentTimes().get(task.getTaskSpentTimes().size() - 1);
        taskSpentTime.setStoppedTime(new Timestamp(System.currentTimeMillis()));

        task.setTaskStatus(TaskStatus.FINISHED);
        task.setFinishedTime(new Timestamp(System.currentTimeMillis()));

        taskSpentTimeRepository.save(taskSpentTime);
        emailService.sendEmail(
                task.getTaskSetter().getEmail(),
                "Завершение задачи в проекте " + task.getProject().getName(),
                "Задача " + task.getName() + " завершена пользователем " + task.getTaskExecutor().getFirstName() + " "
                        + task.getTaskExecutor().getLastName() + ". Время потраченное на задачу - " +
                        new DecimalFormat("#0.00").format(getTimeSpentOnTask(task)) + " часа(ов)."
        );

    }

    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void autoStopTask() {
        List<Task> tasks = taskRepository.findAllByTaskStatus(TaskStatus.EXECUTABLE);
        for (Task task : tasks) {
            TaskSpentTime taskSpentTime = task.getTaskSpentTimes().get(task.getTaskSpentTimes().size() - 1);
            taskSpentTime.setStoppedTime(new Timestamp(System.currentTimeMillis()));


            TaskSpentTime newTaskSpentTime = new TaskSpentTime();
            newTaskSpentTime.setTask(task);

            if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                //FRI
                newTaskSpentTime.setStartedTime(new Timestamp(System.currentTimeMillis() + 226800000)); // 63 hours
            } else {
                //MON-THU
                newTaskSpentTime.setStartedTime(new Timestamp(System.currentTimeMillis() + 54000000)); // 15 hours
            }

            taskSpentTimeRepository.save(taskSpentTime);
            taskSpentTimeRepository.save(newTaskSpentTime);
            taskRepository.save(task);
        }
    }

    public List<TaskReportDTO> getReports(String managerUsername, String startDate, String endDate) {
        List<TaskReportDTO> reports = new ArrayList<>();

        Person manager = personRepository.findPersonByUsername(managerUsername);
        Project project = manager.getProject();
        List<Person> engineers = project.getEngineers();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp startTime = null;
        Timestamp endTime = null;
        try {
            startTime = new Timestamp(dateFormat.parse(startDate).getTime());
            endTime = new Timestamp(dateFormat.parse(endDate).getTime() + 86400000 - 1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (Person engineer : engineers) {
            reports.add(new TaskReportDTO(
                    engineer.getFirstName(),
                    engineer.getLastName(),
                    engineer.getUsername(),
                    taskRepository.countByTaskExecutorAndCreatedTimeBetween(engineer, startTime, endTime),
                    getTaskTimeByEngineer(engineer, startTime, endTime),
                    taskRepository.countByTaskExecutorAndFinishedTimeBetween(engineer, startTime, endTime)
            ));
        }
        return reports;
    }

    private double getTaskTimeByEngineer(Person engineer, Timestamp startTime, Timestamp endTime) {
        double resultTime = 0;
        List<Task> engineerTasks = engineer.getExecuteTasks();
        for (Task engineerTask : engineerTasks) {
            List<TaskSpentTime> taskSpentTimes = taskSpentTimeRepository.getAllByTaskAndStartedTimeIsAfterAndStoppedTimeIsBefore(
                    engineerTask, startTime, endTime);
            for (TaskSpentTime taskSpentTime : taskSpentTimes) {
                resultTime += taskSpentTime.getStoppedTime().getTime();
                resultTime -= taskSpentTime.getStartedTime().getTime();
            }
        }
        return resultTime / 3600000;
    }

    public double getTimeSpentOnTask(Task task){
        double resultTime = 0;
        List<TaskSpentTime> taskSpentTimes = taskSpentTimeRepository.getAllByTask(task);
        for (TaskSpentTime taskSpentTime : taskSpentTimes) {
            resultTime += taskSpentTime.getStoppedTime().getTime();
            resultTime -= taskSpentTime.getStartedTime().getTime();
        }
        return resultTime / 3600000;
    }


}
