package by.zhbn.kach.service;

import by.zhbn.kach.model.Role;
import by.zhbn.kach.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<String> getAllRolesInStringFormat(){
        List<Role> roles = roleRepository.findAll();
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }
}
