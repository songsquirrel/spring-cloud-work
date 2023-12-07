package com.song.basicx.config.mybatis;

import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-flex将日志交给mybatis日志打印类打印
 */
//@Configuration
public class FlexLogConfig implements ConfigurationCustomizer {

    @Override
    public void customize(FlexConfiguration configuration) {
        configuration.setLogImpl(StdOutImpl.class);
    }
}
