package by.zhbn.kach.dto;

import by.zhbn.kach.model.TaskSpentTime;
import by.zhbn.kach.model.TaskStatus;

import java.sql.Timestamp;
import java.util.List;

public class TaskDTO {

    private Long id;

    private String name;

    private String description;

    private Timestamp createdTime;

    private Timestamp finishedTime;

    private TaskStatus taskStatus;

    private List<TaskSpentTime> taskSpentTimes;

    private String projectName;

    private String taskSetterUsername;

    private String taskExecutorUsername;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String name, String description, Timestamp createdTime, Timestamp finishedTime, TaskStatus taskStatus, List<TaskSpentTime> taskSpentTimes, String projectName, String taskSetterUsername, String taskExecutorUsername) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.finishedTime = finishedTime;
        this.taskStatus = taskStatus;
        this.taskSpentTimes = taskSpentTimes;
        this.projectName = projectName;
        this.taskSetterUsername = taskSetterUsername;
        this.taskExecutorUsername = taskExecutorUsername;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskSetterUsername() {
        return taskSetterUsername;
    }

    public void setTaskSetterUsername(String taskSetterUsername) {
        this.taskSetterUsername = taskSetterUsername;
    }

    public String getTaskExecutorUsername() {
        return taskExecutorUsername;
    }

    public void setTaskExecutorUsername(String taskExecutorUsername) {
        this.taskExecutorUsername = taskExecutorUsername;
    }
}
