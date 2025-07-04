package com.song.basic_x.config;

import com.song.basic_x.Constants.SymbolConstant;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置, 基于redis conf
 */
@Configuration
@EnableCaching
public class RedissonConfig {

    @Value("${spring.data.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.data.redis.port:6379}")
    private String port;

    @Value("${spring.data.redis.password:}")
    private String password;

    @Value("${spring.data.redis.timeout:15000}")
    private int timeout;

    @Value("${spring.data.redis.maxPoolSize:64}")
    private int maxPoolSize;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        /*
         * 可配置主从、哨兵、集群等
         *  config.useMasterSlaveServers().setMasterAddress("xx").addSlaveAddress("xxx");
         *  config.useSentinelServers().setMasterName("xxx").addSentinelAddress("xxx");
         *  config.useClusterServers().addNodeAddress("xxx");
         */
        config.useSingleServer()
                .setAddress("redis://" + host + SymbolConstant.COLON + port)
                .setPassword("".equals(password) ? null : password)
                .setConnectionPoolSize(maxPoolSize)
                .setTimeout(timeout);
        return Redisson.create(config);
    }
}
