package by.zhbn.kach.service;

import by.zhbn.kach.dto.PersonDTO;
import by.zhbn.kach.model.Person;
import by.zhbn.kach.model.Project;
import by.zhbn.kach.model.Role;
import by.zhbn.kach.repository.PersonRepository;
import by.zhbn.kach.repository.ProjectRepository;
import by.zhbn.kach.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, ProjectRepository projectRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.projectRepository = projectRepository;
    }

    public PersonDTO getPersonDtoByUsername(String username) {
        Person personByUsername = personRepository.findPersonByUsername(username);
        PersonDTO personDTO = new PersonDTO(
                personByUsername.getId(),
                personByUsername.getFirstName(),
                personByUsername.getLastName(),
                personByUsername.getUsername(),
                personByUsername.getRole().getRoleName(),
                personByUsername.getProject().getName()
        );
        return personDTO;
    }

    public List<PersonDTO> getAllPersonDto() {
        List<Person> persons = personRepository.findAll(Sort.by("id"));
        List<PersonDTO> allPersonDto = new ArrayList<>();

        for (Person person : persons) {
            PersonDTO personDTO = new PersonDTO(
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getUsername(),
                    person.getRole().getRoleName(),
                    person.getProject().getName()
            );
            allPersonDto.add(personDTO);
        }
        return allPersonDto;
    }

    public List<PersonDTO> getProjectPersonsDtoByProjectName(String projectName){
        Project project = projectRepository.findProjectByName(projectName);
        List<Person> engineers = project.getEngineers();

        List<PersonDTO> projectPersons = new ArrayList<>();
        for (Person engineer : engineers) {
            projectPersons.add(new PersonDTO(
                    engineer.getId(),
                    engineer.getFirstName(),
                    engineer.getLastName(),
                    engineer.getUsername(),
                    engineer.getRole().getRoleName(),
                    engineer.getProject().getName()
            ));
        }

        return projectPersons;
    }

    @Transactional
    public void savePerson(PersonDTO newPersonDTO, String password) {
        Role role = roleRepository.findRoleByRoleName(newPersonDTO.getRole());
        Project project = projectRepository.findProjectByName(newPersonDTO.getProject());

        Person newPerson = new Person(
                newPersonDTO.getFirstName(),
                newPersonDTO.getLastName(),
                newPersonDTO.getUsername(),
                new BCryptPasswordEncoder().encode(password),
                role,
                project
        );

        if (role.getRoleName().equals("manager")) {
            newPerson.setManagedProject(project);
            project.setManager(newPerson);
        }

        personRepository.save(newPerson);
    }

    @Transactional
    public void updatePerson(PersonDTO updatedPersonDTO, String password, String currentUsername) {
        Role role = roleRepository.findRoleByRoleName(updatedPersonDTO.getRole());
        Project project = projectRepository.findProjectByName(updatedPersonDTO.getProject());
        Person updatedPerson = personRepository.findPersonByUsername(currentUsername);

        updatedPerson.setFirstName(updatedPersonDTO.getFirstName());
        updatedPerson.setLastName(updatedPersonDTO.getLastName());
        updatedPerson.setUsername(updatedPersonDTO.getUsername());
        if(!password.isEmpty()){
            updatedPerson.setPassword(new BCryptPasswordEncoder().encode(password));
        }
        updatedPerson.setProject(project);
        updatedPerson.setRole(role);

        if (role.getRoleName().equals("manager")) {
            updatedPerson.setManagedProject(project);
            project.setManager(updatedPerson);
        }
        personRepository.save(updatedPerson);
    }

    public void deletePerson(Long id) {
        Person person = personRepository.findPersonById(id);
        if (person.getRole().getRoleName().equals("manager")) {
            person.getManagedProject().setManager(null);
        }
        personRepository.delete(person);
    }
}
