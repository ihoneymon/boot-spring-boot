package io.honeymon.boot.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.honeymon.boot.springboot.service.RestClientExampleBean.Post;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClientExampleBeanTest {

    @Autowired
    RestClientExampleBean restClientExampleBean;
    
    @Test
    public void test() {
        long postId = 1L;
        Post findOne = restClientExampleBean.findOne(postId);
        
        assertThat(findOne.getId()).isEqualTo(postId);
        assertThat(findOne.getUserId()).isEqualTo(1L);
    }

}
