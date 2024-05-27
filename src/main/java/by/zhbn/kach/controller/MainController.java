package by.zhbn.kach.controller;

import by.zhbn.kach.model.TaskStatus;
import by.zhbn.kach.service.PersonService;
import by.zhbn.kach.service.ProjectService;
import by.zhbn.kach.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private PersonService personService;
    private TaskService taskService;
    private ProjectService projectService;

    @Autowired
    public MainController(PersonService personService, TaskService taskService, ProjectService projectService) {
        this.personService = personService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails){
        List<? extends GrantedAuthority> roles = new ArrayList<>(userDetails.getAuthorities());

         if(roles.get(0).getAuthority().equals("ROLE_admin")){
            return "redirect:/admin";
        }else if(roles.get(0).getAuthority().equals("ROLE_manager")){
            return "redirect:/manager";
        }else if(roles.get(0).getAuthority().equals("ROLE_engineer")){
            return "redirect:/engineer";
        }else {
            return "error-page";
        }
    }

    @GetMapping("/admin")
    public String getAdminMainPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("admin", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("users", personService.getAllPersonDto());
        return "admin/admin-main-page";
    }

    @GetMapping("/manager")
    public String getManagerMainPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("manager", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("tasks", taskService.getTasksDtoByManagerUsername(userDetails.getUsername()));
        return "manager/manager-main-page";
    }

    @GetMapping("/engineer")
    public String getEngineerMainPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("engineer", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("tasks", taskService.getTaskDtoByExecutorUsername(userDetails.getUsername()));
        model.addAttribute("taskStatusNONEXECUTABLE", TaskStatus.NONEXECUTABLE);
        model.addAttribute("taskStatusEXECUTABLE", TaskStatus.EXECUTABLE);
        model.addAttribute("taskStatusFINISHED", TaskStatus.FINISHED);
        return "engineer/engineer-main-page";
    }
}
