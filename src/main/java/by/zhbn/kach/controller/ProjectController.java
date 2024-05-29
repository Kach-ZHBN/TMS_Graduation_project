package by.zhbn.kach.controller;


import by.zhbn.kach.dto.ProjectDTO;
import by.zhbn.kach.service.PersonService;
import by.zhbn.kach.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private final PersonService personService;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(PersonService personService, ProjectService projectService) {
        this.personService = personService;
        this.projectService = projectService;
    }

    @GetMapping
    public String getAllProjectPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("admin", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("projects", projectService.getAllProjectDto());
        return "admin/admin-project-page";
    }

    @GetMapping("/new")
    public String getNewProjectPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("admin", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("newProject", new ProjectDTO());
        return "project/new-project-page";
    }

    @PostMapping
    public String createProject(@ModelAttribute("newProject") ProjectDTO projectDTO){
        projectService.createNewProject(projectDTO);
        return "redirect:/project";
    }


    @GetMapping("/{id}/edit")
    public String getEditProjectPage(@AuthenticationPrincipal UserDetails userDetails, Model model,
                                     @PathVariable("id") Long id){
        model.addAttribute("admin", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("editProject", projectService.getProjectDtoById(id));
        return "project/edit-project-page";
    }

    @PostMapping("/{id}")
    public String editProject(@ModelAttribute("editProject") ProjectDTO projectDTO){
        projectService.updateProject(projectDTO);
        return "redirect:/project";
    }


    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable("id") Long id){
        projectService.deleteProject(id);
        return "redirect:/project";
    }
}
