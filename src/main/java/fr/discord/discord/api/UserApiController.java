package fr.discord.discord.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.discord.discord.model.User;
import fr.discord.discord.repository.UserRepository;
import fr.discord.discord.request.AuthRequest;
import fr.discord.discord.request.SubscriptionRequest;
import fr.discord.discord.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class UserApiController {
    @Autowired
    private UserRepository repoUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String auth(@RequestBody AuthRequest request) {
        // On va demander à SPRING SECURITY d'authentifier l'utilisateur
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return JwtUtil.generate();
        }
        catch (BadCredentialsException e) {
            return "";
        }
    }

    @PostMapping("/subscribe")
    public Integer subscribe(@RequestBody SubscriptionRequest request) {
        User user = new User();

        BeanUtils.copyProperties(request, user);

        user.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.repoUser.save(user);

        return user.getId();
    }
}