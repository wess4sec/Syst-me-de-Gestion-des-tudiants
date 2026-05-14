package com.example.sge.controller;

import com.example.sge.dto.AuthResponse;
import com.example.sge.dto.LoginRequest;
import com.example.sge.model.Utilisateur;
import com.example.sge.repository.UtilisateurRepository;
import com.example.sge.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtService jwtService;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // POST /auth/login — obtenir un token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Identifiants incorrects");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);

        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(request.getUsername()).orElseThrow();

        return ResponseEntity.ok(new AuthResponse(token, utilisateur.getUsername(), utilisateur.getRole()));
    }

    // POST /auth/register — créer un compte (usage admin/initial)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
        if (utilisateurRepository.findByUsername(utilisateur.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username déjà utilisé");
        }
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        if (utilisateur.getRole() == null || utilisateur.getRole().isBlank()) {
            utilisateur.setRole("ROLE_USER");
        }
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }
}
