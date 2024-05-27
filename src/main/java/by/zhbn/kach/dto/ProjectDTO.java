package by.zhbn.kach.dto;

import java.util.List;

public class ProjectDTO {

    private Long id;

    private String name;

    private PersonDTO manager;

    private List<Long> engineersId;

    private List<Long> tasksId;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, PersonDTO manager, List<Long> engineersId, List<Long> tasksId) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.engineersId = engineersId;
        this.tasksId = tasksId;
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

    public PersonDTO getManager() {
        return manager;
    }

    public void setManager(PersonDTO manager) {
        this.manager = manager;
    }

    public List<Long> getEngineersId() {
        return engineersId;
    }

    public void setEngineersId(List<Long> engineersId) {
        this.engineersId = engineersId;
    }

    public List<Long> getTasksId() {
        return tasksId;
    }

    public void setTasksId(List<Long> tasksId) {
        this.tasksId = tasksId;
    }
}
