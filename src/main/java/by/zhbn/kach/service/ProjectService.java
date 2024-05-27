package by.zhbn.kach.service;

import by.zhbn.kach.model.Project;
import by.zhbn.kach.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<String> getAllProjectInStringFormat(){
        List<Project> projects = projectRepository.findAll();
        List<String> projectNames = new ArrayList<>();
        for (Project project : projects) {
            projectNames.add(project.getName());
        }
        return projectNames;
    }
}
