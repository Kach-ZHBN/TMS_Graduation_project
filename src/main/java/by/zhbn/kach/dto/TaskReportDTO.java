package by.zhbn.kach.dto;

public class TaskReportDTO {
    private String personFirstName;
    private String personLastName;
    private String personUsername;
    private long tasksAmount;
    private double workTime;
    private long finishedTaskAmount;

    public TaskReportDTO() {
    }

    public TaskReportDTO(String personFirstName, String personLastName, String personUsername, long tasksAmount, double workTime, long finishedTaskAmount) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.personUsername = personUsername;
        this.tasksAmount = tasksAmount;
        this.workTime = workTime;
        this.finishedTaskAmount = finishedTaskAmount;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonUsername() {
        return personUsername;
    }

    public void setPersonUsername(String personUsername) {
        this.personUsername = personUsername;
    }

    public long getTasksAmount() {
        return tasksAmount;
    }

    public void setTasksAmount(long tasksAmount) {
        this.tasksAmount = tasksAmount;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        this.workTime = workTime;
    }

    public long getFinishedTaskAmount() {
        return finishedTaskAmount;
    }

    public void setFinishedTaskAmount(long finishedTaskAmount) {
        this.finishedTaskAmount = finishedTaskAmount;
    }
}
