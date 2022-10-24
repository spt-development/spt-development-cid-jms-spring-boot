package com.spt.development.cid.jms.spring.boot.autoconfigure;

import com.spt.development.cid.jms.spring.CorrelationIdSetter;
import com.spt.development.cid.jms.spring.MdcCorrelationIdPutter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;

import java.util.Optional;

import static com.spt.development.cid.jms.spring.MdcCorrelationIdPutter.MDC_CID_KEY;

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

    @Bean
    @ConditionalOnProperty(name = "spt.cid.mdc.disabled", havingValue = "false", matchIfMissing = true)
    public MdcCorrelationIdPutter mdcCorrelationIdPutter(@Value("${spt.cid.mdc.cid-key:#{null}}") String mdcCidKey) {
        return new MdcCorrelationIdPutter(Optional.ofNullable(mdcCidKey).orElse(MDC_CID_KEY));
    }
}
