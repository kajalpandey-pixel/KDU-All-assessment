package org.example.library.web.security;

import org.example.library.domain.entity.User;
import org.example.library.domain.enums.Role;
import org.example.library.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberCannotCreateBookTest {

    @Autowired MockMvc mockMvc;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository.findByUsername("member").orElseGet(() -> {
            User u = new User();
            u.setUsername("member");
            u.setPassword(passwordEncoder.encode("pass"));
            u.setRole(Role.MEMBER);
            u.setEnabled(true);
            return userRepository.save(u);
        });
    }

    @Test
    void memberCannotCreateBook_returns403() throws Exception {
        mockMvc.perform(
                post("/books")
                        .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic("member", "pass"))
        ).andExpect(status().isForbidden());
    }
}
