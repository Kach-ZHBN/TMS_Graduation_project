package by.zhbn.kach.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @OneToOne(mappedBy = "manager")
    private Project managedProject;

    @OneToMany(mappedBy = "taskSetter")
    private List<Task> setTasks;

    @OneToMany(mappedBy = "taskExecutor")
    private List<Task> executeTasks;

    public Person() {
    }

    public Person(Long id, String firstName, String lastName, String username, String password, Role role, Project project, Project managedProject, List<Task> setTasks, List<Task> executeTasks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.project = project;
        this.managedProject = managedProject;
        this.setTasks = setTasks;
        this.executeTasks = executeTasks;
    }

    public Person(String firstName, String lastName, String username, String password, Role role, Project project, Project managedProject, List<Task> setTasks, List<Task> executeTasks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.project = project;
        this.managedProject = managedProject;
        this.setTasks = setTasks;
        this.executeTasks = executeTasks;
    }

    public Person(String firstName, String lastName, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Person(String firstName, String lastName, String username, String password, Role role, Project project) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getManagedProject() {
        return managedProject;
    }

    public void setManagedProject(Project managedProject) {
        this.managedProject = managedProject;
    }

    public List<Task> getSetTasks() {
        return setTasks;
    }

    public void setSetTasks(List<Task> setTasks) {
        this.setTasks = setTasks;
    }

    public List<Task> getExecuteTasks() {
        return executeTasks;
    }

    public void setExecuteTasks(List<Task> executeTasks) {
        this.executeTasks = executeTasks;
    }
}
