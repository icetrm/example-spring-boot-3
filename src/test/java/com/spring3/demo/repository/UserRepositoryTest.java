package com.spring3.demo.repository;

import com.spring3.demo.entity.Role;
import com.spring3.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void CheckFindByUsername() {

        User user = new User(
                "test011",
                "111111111111",
                "T",
                "A",
                "0882746152",
                "sss@gmail.com",
                "M",
                23,
                new Date(),
                new Role(2L)
                );
        userRepository.save(user);

        User userResult = userRepository.findByUsername(user.getUsername());

        assertThat(user).isEqualTo(userResult);
    }
}
