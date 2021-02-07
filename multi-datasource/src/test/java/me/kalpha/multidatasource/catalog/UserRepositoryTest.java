package me.kalpha.multidatasource.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    public void initializeData() {
        user = User.builder()
                .userId("jjd")
                .email("jjd@email.com")
                .password("jjd")
                .role(UserRole.USER)
                .build();
    }

    @Test
    public void findById() {
        User savedUser = userRepository.save(user);

        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        System.out.println(optionalUser.get().getUserId());

        assertTrue(optionalUser.isPresent());
    }

}