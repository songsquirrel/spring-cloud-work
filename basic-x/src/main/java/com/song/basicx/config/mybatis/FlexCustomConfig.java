package com.song.basicx.config.mybatis;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlexCustomConfig implements MyBatisFlexCustomizer {


    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        //我们可以在这里进行一些列的初始化配置

    }

}
