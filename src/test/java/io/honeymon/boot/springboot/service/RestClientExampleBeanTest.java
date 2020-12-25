package io.honeymon.boot.springboot.service;

import io.honeymon.boot.springboot.service.RestClientExampleBean.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ExtendWith는 JUnit5 에서 반복적으로 실행되는 클래스나 메서드에 선언한다.
 * {@link SpringExtension} 클래스는 는 스프링 5에 추가된
 * JUnit 5의 주피터(Jupiter) 모델에서 스프링 테스트컨텍스트(TestContext)를 사용할 수 있도록 해준다.
 *
 * 출처: https://java.ihoney.pe.kr/525 [허니몬(Honeymon)의 자바guru]
 *
 * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications
 */
//@ExtendWith(SpringExtension.class) // JUnit5 와 스프링 부트를 사용한다면 @SpringBootTest 로 대체 가능
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
