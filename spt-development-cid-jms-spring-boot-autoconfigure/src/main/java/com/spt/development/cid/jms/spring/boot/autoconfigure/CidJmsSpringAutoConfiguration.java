package com.spt.development.cid.jms.spring.boot.autoconfigure;

import com.spt.development.cid.jms.spring.CorrelationIdSetter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;

/**
 * {@link AutoConfiguration Auto-Configuration} for
 * <a href="https://github.com/spt-development/spt-development-cid-jms-spring">spt-development/spt-development-cid-jms-spring</a>.
 */
@AutoConfiguration
@ConditionalOnClass({ JmsListener.class })
public class CidJmsSpringAutoConfiguration {

    /**
     * Creates a vanilla {@link CorrelationIdSetter} (aspect) bean.
     *
     * @return a new {@link CorrelationIdSetter} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public CorrelationIdSetter correlationIdSetter() {
        return new CorrelationIdSetter();
    }
}
