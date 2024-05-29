package by.zhbn.kach.controller;

import by.zhbn.kach.dto.PersonDTO;
import by.zhbn.kach.service.PersonService;
import by.zhbn.kach.service.ProjectService;
import by.zhbn.kach.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;
    private RoleService roleService;
    private ProjectService projectService;

    @Autowired
    public PersonController(PersonService personService, RoleService roleService, ProjectService projectService) {
        this.personService = personService;
        this.roleService = roleService;
        this.projectService = projectService;
    }

    @GetMapping("/new")
    public String getNewPersonPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("admin", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("newUser", new PersonDTO());
        model.addAttribute("roles", roleService.getAllRolesInStringFormat());
        model.addAttribute("projects", projectService.getAllProjectInStringFormat());
        return "person/new-person-page";
    }

    @PostMapping
    public String saveNewPerson(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("newUser") PersonDTO newPerson,
                                @ModelAttribute("userPassword") String password) {
        personService.savePerson(newPerson, password);
        return "redirect:/";
    }

    @GetMapping("/{username}/edit")
    public String getEditPersonPage(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("username") String username,
                                    Model model){
        model.addAttribute("admin", personService.getPersonDtoByUsername(userDetails.getUsername()));
        model.addAttribute("editedUser", personService.getPersonDtoByUsername(username));
        model.addAttribute("currentUsername", username);
        model.addAttribute("roles", roleService.getAllRolesInStringFormat());
        model.addAttribute("projects", projectService.getAllProjectInStringFormat());
        return "person/edit-person-page";
    }

    @PostMapping("/{username}")
    public String updatePerson(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("editedUser") PersonDTO newPerson,
                                @ModelAttribute("userPassword") String password, @PathVariable("username") String username) {
        personService.updatePerson(newPerson, password, username);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
        return "redirect:/";
    }
}
