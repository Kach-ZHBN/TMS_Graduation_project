package by.zhbn.kach.dto;

public class ProjectDTO {

    private Long id;

    private String name;

    private String managerName;


    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String managerName) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
    }

    public ProjectDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
