package com.queuesystem.queuesystem.config;

import com.queuesystem.queuesystem.util.IdWorkerUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 通用bean
 * </p>
 *
 * @author
 * date
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorkerUtil idWorkerUtil(){
        return new IdWorkerUtil(1,1,1);
    }
}
