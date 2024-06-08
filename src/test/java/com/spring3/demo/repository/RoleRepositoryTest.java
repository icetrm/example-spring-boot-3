package com.spring3.demo.repository;

import com.spring3.demo.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindById() {
        // Create a sample role
        Role role = new Role();
        role.setId(4L);
        role.setTitle("ROLE_USER");

        // Save the role to the MySQL database
        Role savedRole = roleRepository.save(role);

        // Retrieve the role by ID
        Role optionalRole = roleRepository.findById(savedRole.getId()).orElse(null);

        // Assert that the retrieved role is present and has the correct name
        assertEquals("ROLE_USER", optionalRole != null ? optionalRole.getTitle() : "");
    }
}
