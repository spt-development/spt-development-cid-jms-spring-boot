package com.spt.development.cid.jms.spring.boot.autoconfigure;

import com.spt.development.cid.jms.spring.CorrelationIdSetter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration Auto-Configuration} for
 * <a href="https://github.com/spt-development/spt-development-cid-jms-spring">spt-development/spt-development-cid-jms-spring</a>.
 */
@Configuration
@ConditionalOnClass({ JmsListener.class })
public class CidJmsSpringAutoConfiguration {

    /**
     * Creates a vanilla {@link CorrelationIdSetter} (aspect) bean.
     *
     * @return a new {@link CorrelationIdSetter} bean.
     */
    @Bean
    @Order(0)
    @ConditionalOnMissingBean
    public CorrelationIdSetter correlationIdSetter() {
        return new CorrelationIdSetter();
    }
}
