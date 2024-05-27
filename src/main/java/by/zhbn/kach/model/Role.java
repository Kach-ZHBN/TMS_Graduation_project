package by.zhbn.kach.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Role")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "role")
    private List<Person> persons;

    public Role() {
    }

    public Role(Long id, String roleName, List<Person> persons) {
        this.id = id;
        this.roleName = roleName;
        this.persons = persons;
    }

    public Role(String roleName, List<Person> persons) {
        this.roleName = roleName;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
