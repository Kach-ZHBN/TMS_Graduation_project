package by.zhbn.kach.service;

import by.zhbn.kach.dto.ProjectDTO;
import by.zhbn.kach.model.Project;
import by.zhbn.kach.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAllProjectDto() {
        List<ProjectDTO> projectsDto = new ArrayList<>();
        List<Project> projects = projectRepository.findAll(Sort.by("id"));
        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO(project.getId(), project.getName());
            if (project.getManager() == null) {
                projectDTO.setManagerName("Менеджер не назначен");
            }else{
                projectDTO.setManagerName(project.getManager().getFirstName() + project.getManager().getLastName());
            }
            projectsDto.add(projectDTO);
        }
        return projectsDto;
    }

    public List<String> getAllProjectInStringFormat() {
        List<Project> projects = projectRepository.findAll();
        List<String> projectNames = new ArrayList<>();
        for (Project project : projects) {
            projectNames.add(project.getName());
        }
        return projectNames;
    }

    public void createNewProject(ProjectDTO projectDTO) {
        Project newProject = new Project();
        newProject.setName(projectDTO.getName());
        projectRepository.save(newProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO getProjectDtoById(Long id) {
        Project project = projectRepository.findById(id).get();
        return new ProjectDTO(project.getId(), project.getName());
    }

    public void updateProject(ProjectDTO projectDTO) {
        Project updatedProject = projectRepository.findById(projectDTO.getId()).get();
        updatedProject.setName(projectDTO.getName());
        projectRepository.save(updatedProject);
    }
}
