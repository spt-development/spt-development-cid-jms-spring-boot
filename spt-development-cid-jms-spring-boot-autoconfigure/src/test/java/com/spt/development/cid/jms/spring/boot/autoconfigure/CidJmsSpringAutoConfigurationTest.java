package com.spt.development.cid.jms.spring.boot.autoconfigure;

import com.spt.development.cid.jms.spring.CorrelationIdSetter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class CidJmsSpringAutoConfigurationTest {
    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void init() {
        this.context = new AnnotationConfigApplicationContext();
    }

    @AfterEach
    void closeContext() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    void register_happyPath_shouldRegisterCorrelationIdSetter() {
        context.register(CidJmsSpringAutoConfiguration.class);
        context.refresh();

        assertThat(context.getBean(CorrelationIdSetter.class), is(notNullValue()));
    }
}