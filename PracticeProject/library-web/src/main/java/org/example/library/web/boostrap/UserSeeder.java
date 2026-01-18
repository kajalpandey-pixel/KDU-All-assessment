package org.example.library.web.boostrap;


import org.example.library.domain.entity.User;
import org.example.library.domain.enums.Role;
import org.example.library.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("librarian").isEmpty()) {
            User u = new User();
            u.setUsername("librarian");
            u.setPassword(passwordEncoder.encode("pass"));
            u.setRole(Role.LIBRARIAN);
            u.setEnabled(true);
            userRepository.save(u);
        }

        if (userRepository.findByUsername("member").isEmpty()) {
            User u = new User();
            u.setUsername("member");
            u.setPassword(passwordEncoder.encode("pass"));
            u.setRole(Role.MEMBER);
            u.setEnabled(true);
            userRepository.save(u);
        }
    }
}
