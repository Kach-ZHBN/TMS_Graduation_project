package by.zhbn.kach.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "finished_time")
    private Timestamp finishedTime;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private List<TaskSpentTime> taskSpentTimes;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "taskSetter_id", referencedColumnName = "id")
    private Person taskSetter;

    @ManyToOne
    @JoinColumn(name = "taskExecutor_id", referencedColumnName = "id")
    private Person taskExecutor;

    public Task() {
    }

    public Task(Long id, String name, String description, Timestamp createdTime, Timestamp finishedTime, TaskStatus taskStatus, List<TaskSpentTime> taskSpentTimes, Project project, Person taskSetter, Person taskExecutor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.finishedTime = finishedTime;
        this.taskStatus = taskStatus;
        this.taskSpentTimes = taskSpentTimes;
        this.project = project;
        this.taskSetter = taskSetter;
        this.taskExecutor = taskExecutor;
    }

    public Task(String name, String description, Timestamp createdTime, Timestamp finishedTime, TaskStatus taskStatus, List<TaskSpentTime> taskSpentTimes, Project project, Person taskSetter, Person taskExecutor) {
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.finishedTime = finishedTime;
        this.taskStatus = taskStatus;
        this.taskSpentTimes = taskSpentTimes;
        this.project = project;
        this.taskSetter = taskSetter;
        this.taskExecutor = taskExecutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Timestamp finishedTime) {
        this.finishedTime = finishedTime;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<TaskSpentTime> getTaskSpentTimes() {
        return taskSpentTimes;
    }

    public void setTaskSpentTimes(List<TaskSpentTime> taskSpentTimes) {
        this.taskSpentTimes = taskSpentTimes;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Person getTaskSetter() {
        return taskSetter;
    }

    public void setTaskSetter(Person taskSetter) {
        this.taskSetter = taskSetter;
    }

    public Person getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Person taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
