package com.spt.development.cid.jms.spring.boot.autoconfigure;

import com.spt.development.cid.jms.spring.CorrelationIdSetter;
import com.spt.development.cid.jms.spring.MdcCorrelationIdPutter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static com.spt.development.cid.jms.spring.MdcCorrelationIdPutter.MDC_CID_KEY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Nested
    class CorrelationIdSetterTest {

        @Test
        void register_happyPath_shouldRegisterCorrelationIdSetterBean() {
            context.register(CidJmsSpringAutoConfiguration.class);
            context.refresh();

            assertThat(context.getBean(CorrelationIdSetter.class), is(notNullValue()));
        }
    }

    @Nested
    class MdcCorrelationIdPutterTest {
        private static final String BEAN_NAME = "mdcCorrelationIdPutter";

        @Test
        void register_defaultConfig_shouldRegisterMdcCorrelationIdPutterBean() {
            context.register(CidJmsSpringAutoConfiguration.class);
            context.refresh();

            final MdcCorrelationIdPutter result = context.getBean(BEAN_NAME, MdcCorrelationIdPutter.class);

            assertThat(result, is(notNullValue()));
            assertThat(ReflectionTestUtils.getField(result, "cidKey"), is(MDC_CID_KEY));
        }

        @Test
        void register_customConfig_shouldRegisterMdcCorrelationIdPutterBean() {
            final String cidKey = "test-correlation-id";

            TestPropertyValues.of(
                    "spt.cid.mdc.disabled:false",
                    "spt.cid.mdc.cid-key:" + cidKey
            ).applyTo(context);

            context.register(CidJmsSpringAutoConfiguration.class);
            context.refresh();

            final MdcCorrelationIdPutter result = context.getBean(BEAN_NAME, MdcCorrelationIdPutter.class);

            assertThat(result, is(notNullValue()));
            assertThat(ReflectionTestUtils.getField(result, "cidKey"), is(cidKey));
        }

        @Test
        void register_mdcDisabled_shouldNotRegisterMdcCorrelationIdPutterBean() {
            TestPropertyValues.of(
                    "spt.cid.mdc.disabled:true"
            ).applyTo(context);

            context.register(CidJmsSpringAutoConfiguration.class);
            context.refresh();

            final NoSuchBeanDefinitionException result = assertThrows(
                    NoSuchBeanDefinitionException.class, () -> context.getBean(BEAN_NAME)
            );

            assertThat(result, is(notNullValue()));
            assertThat(result.getMessage(), is("No bean named 'mdcCorrelationIdPutter' available"));
        }
    }
}