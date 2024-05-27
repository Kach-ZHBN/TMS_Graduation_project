package by.zhbn.kach.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TaskSpentTime")
public class TaskSpentTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Column(name = "started_time")
    private Timestamp startedTime;

    @Column(name = "stopped_time")
    private Timestamp stoppedTime;

    public TaskSpentTime() {
    }

    public TaskSpentTime(long id, Task task, Timestamp startedTime, Timestamp stoppedTime) {
        this.id = id;
        this.task = task;
        this.startedTime = startedTime;
        this.stoppedTime = stoppedTime;
    }

    public TaskSpentTime(Task task, Timestamp startedTime, Timestamp stoppedTime) {
        this.task = task;
        this.startedTime = startedTime;
        this.stoppedTime = stoppedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Timestamp getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Timestamp startedTime) {
        this.startedTime = startedTime;
    }

    public Timestamp getStoppedTime() {
        return stoppedTime;
    }

    public void setStoppedTime(Timestamp stoppedTime) {
        this.stoppedTime = stoppedTime;
    }
}

