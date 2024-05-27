package by.zhbn.kach.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Person manager;

    @OneToMany(mappedBy = "project")
    private List<Person> engineers;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Project() {
    }

    public Project(long id, String name, Person manager, List<Person> engineers, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.engineers = engineers;
        this.tasks = tasks;
    }

    public Project(String name, Person manager, List<Person> engineers, List<Task> tasks) {
        this.name = name;
        this.manager = manager;
        this.engineers = engineers;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public List<Person> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<Person> engineers) {
        this.engineers = engineers;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
