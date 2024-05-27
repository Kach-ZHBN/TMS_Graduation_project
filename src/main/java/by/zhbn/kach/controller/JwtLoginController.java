package by.zhbn.kach.controller;

import by.zhbn.kach.config.JwtService;
import by.zhbn.kach.dto.AuthRequestDTO;
import by.zhbn.kach.dto.JwtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtLoginController {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public JwtLoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new JwtResponseDTO(jwtService.generateToken(authRequestDTO.getUsername())));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
