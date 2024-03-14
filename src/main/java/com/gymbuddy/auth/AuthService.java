package com.gymbuddy.auth;

import com.gymbuddy.db.model.UserRecord;
import com.gymbuddy.db.repository.TokenRepository;
import com.gymbuddy.db.repository.UserRepository;
import com.gymbuddy.token.EmailService;
import com.gymbuddy.token.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    EmailService emailService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            TokenRepository tokenRepository,
            AuthenticationManager authenticationManager) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserRecord request) {

        if (userRepository.findByUserEmail(request.getEmail()) != null) {
            return new AuthenticationResponse(null, null, "Error: Email is already in use!");
        }

        UserRecord user = new UserRecord();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsEnabled(false);

        user.setRole("USER");

        user = userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/confirm-account?token="
                + jwt);
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + jwt);

        return new AuthenticationResponse(user, jwt, "User registration was successful");
    }

    public AuthenticationResponse authenticate(UserRecord request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        UserRecord user = userRepository.findByUserEmail(request.getEmail());

        if (user.getIsEnabled() == false) {
            return new AuthenticationResponse(null, null, "User is not confirm email address!");
        }

        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user.getId());
        saveUserToken(jwt, user);

        return new AuthenticationResponse(user, jwt, "User login was successful");
    }

    private void revokeAllTokenByUser(Integer userId) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(userId);

        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, UserRecord user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUserId(user.getId());
        tokenRepository.save(token);
    }

    public ResponseEntity<?> confirmEmail(String tokenParam) {

        if (tokenParam != null) {
            UserRecord user = userRepository
                    .findUserById(tokenRepository.findUserIdByToken(tokenParam));
            user.setIsEnabled(true);
            userRepository.save(user);

            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
