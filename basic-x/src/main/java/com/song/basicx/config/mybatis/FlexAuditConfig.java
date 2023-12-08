package com.song.basicx.config.mybatis;

import com.mybatisflex.core.audit.AuditManager;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 开启sql审计功能，可查看sql执行时间
 * 可实现为在配置中心动态开启
 * 此项会导致性能下降. 非排查问题不建议开启,
 * 建议关闭此功能, 实测性能下降约15%;
 * 建议flex仅仅使用通用mapper功能, 其余功能慎重使用
 */
@Slf4j
//@Configuration
public class FlexAuditConfig {

    @Value("${mybatis-flex.sql.audit.enable:false}")
    private boolean auditEnable;


    @Value("${mybatis-flex.sql.audit.slowSqlExecTime:2000}")
    private int slowSqlExecTime;

    @PostConstruct
    private void init() {
        // @PostConstruct 注解保证在属性注入之后执行
        // spring依赖注入步骤.
        //Bean 实例化： 当 Spring 容器启动时，它会创建和管理所有的 Bean。当容器实例化一个 Bean 的时候，它会调用 Bean 的构造函数。
        //依赖注入： 容器在实例化 Bean 后，会将依赖注入到 Bean 的属性中。
        //PostConstruct 回调： 在依赖注入完成后，Spring 容器会检查 Bean 是否使用了 @PostConstruct 注解标记了一个方法。如果使用了，则容器将调用这个方法。
        //执行 @PostConstruct 方法： 容器调用被 @PostConstruct 注解标记的方法，执行在这个方法中定义的逻辑。这个方法可以进行一些初始化的操作，确保 Bean 已经被正确配置
        AuditManager.setAuditEnable(auditEnable);
        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> {
                    if (auditMessage.getElapsedTime() > slowSqlExecTime) {
                        log.warn("{}|{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime());
                    } else {
                        log.debug("{}|{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime());
                    }
                }
        );
    }
}
