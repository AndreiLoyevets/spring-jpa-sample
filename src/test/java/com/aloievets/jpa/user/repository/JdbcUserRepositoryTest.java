package com.aloievets.jpa.user.repository;

import com.aloievets.jpa.TestAppConfig;
import com.aloievets.jpa.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew on 01.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class JdbcUserRepositoryTest {

    @Autowired
    @Qualifier("jdbcUserRepository")
    private UserRepository userRepository;

    @Test(expected = NullPointerException.class)
    public void findByEmailNullEmail() {
        userRepository.findByEmail(null);
    }

    @Test
    public void findByEmailSingleResult() {
        final String email = "user1@gmail.com";
        User expectedUser = User.builder().id(1L).email(email).build();

        assertEquals(expectedUser, userRepository.findByEmail(email).get(0));
    }

    @Test
    public void findByEmailMultipleResults() {
        final String email = "user2@gmail.com";
        List<User> expectedUsers = new ArrayList<>(2);
        expectedUsers.add(User.builder().id(2L).email(email).build());
        expectedUsers.add(User.builder().id(3L).email(email).build());

        List<User> actualUsers = userRepository.findByEmail(email);

        assertTrue(expectedUsers.containsAll(actualUsers));
        assertTrue(actualUsers.containsAll(expectedUsers));
    }
}