package by.zhbn.kach.dto;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String email;
    private String project;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String firstName, String lastName, String username, String role, String email, String project) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.email = email;
        this.project = project;
    }

    public PersonDTO(Long id, String firstName, String lastName, String username, String role, String project) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.project = project;
    }

    public PersonDTO(String firstName, String lastName, String username, String role, String project) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
