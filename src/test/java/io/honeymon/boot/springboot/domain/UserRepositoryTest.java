package io.honeymon.boot.springboot.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
