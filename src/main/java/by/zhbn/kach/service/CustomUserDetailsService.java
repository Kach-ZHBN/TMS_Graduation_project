package by.zhbn.kach.service;

import by.zhbn.kach.model.Person;
import by.zhbn.kach.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PersonRepository userRepository;

    @Autowired
    public CustomUserDetailsService(PersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = userRepository.findPersonByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }

        return org.springframework.security.core.userdetails.User.withUsername(person.getUsername())
                .password(person.getPassword())
                .roles(person.getRole().getRoleName())
                .build();
    }
}
