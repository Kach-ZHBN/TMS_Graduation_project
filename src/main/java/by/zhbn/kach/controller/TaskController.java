package by.zhbn.kach.controller;

import by.zhbn.kach.dto.TaskDTO;
import by.zhbn.kach.dto.TaskReportDTO;
import by.zhbn.kach.service.PersonService;
import by.zhbn.kach.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private PersonService personService;
    private TaskService taskService;

    @Autowired
    public TaskController(PersonService personService, TaskService taskService) {
        this.personService = personService;
        this.taskService = taskService;
    }

    @GetMapping("/new")
    public String getNewTaskPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("manager", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("newTask", new TaskDTO());
        model.addAttribute("engineers", personService.getProjectPersonsDtoByProjectName(
                personService.getPersonDtoByUsername(userDetails.getUsername()).getProject()
        ));
        return "task/new-task-page";
    }

    @PostMapping()
    public String createTask(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("newTask") TaskDTO newTaskDto){

        taskService.saveTask(newTaskDto, userDetails.getUsername());

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @PostMapping("/{id}/start")
    public String startTask(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long taskId){
        taskService.startTask(taskId);
        return "redirect:/";
    }

    @PostMapping("/{id}/stop")
    public String stopTask(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long taskId){
        taskService.stopTask(taskId);
        return "redirect:/";
    }

    @PostMapping("/{id}/finish")
    public String finishTask(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long taskId){
        taskService.finishTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/reports")
    public String getReportsPage(@AuthenticationPrincipal UserDetails userDetails, Model model,
                                 @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        model.addAttribute("manager", personService.getPersonDtoByUsername(userDetails.getUsername()));
        if(!startDate.isEmpty() && !endDate.isEmpty()){
            List<TaskReportDTO> reports = taskService.getReports(userDetails.getUsername(), startDate, endDate);
            model.addAttribute("reports", reports);
        }
        return "task/report-task-page";
    }
}
