package io.honeymon.boot.springboot.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;
    
    @Test
    public void add() throws Exception {
        // given
        User user = new User("ihoneymon+test@gmail.com", "password"); 

        // when
        repository.save(user);

        // then
        assertThat(user.getId()).isNotNull();
    }

}
