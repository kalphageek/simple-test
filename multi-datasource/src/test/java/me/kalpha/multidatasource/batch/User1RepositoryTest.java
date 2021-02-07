package me.kalpha.multidatasource.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class User1RepositoryTest {

    @Autowired
    User1Repository user1Repository;

    private User1 user1;

    @BeforeEach
    public void initializeData() {
        user1 = User1.builder()
                .userId("jjd1")
                .email("jjd1@email.com")
                .password("jjd1")
                .role(UserRole1.USER)
                .build();
    }

    @Test
    public void findById() {
        User1 savedUser1 = user1Repository.save(user1);

        Optional<User1> optionalUser = user1Repository.findById(user1.getUserId());
        System.out.println(optionalUser.get().getUserId());

        assertTrue(optionalUser.isPresent());
    }

}