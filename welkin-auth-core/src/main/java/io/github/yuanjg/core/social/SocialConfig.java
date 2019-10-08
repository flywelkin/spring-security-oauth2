package io.github.yuanjg.core.social;

import io.github.yuanjg.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 社交配置类
 *
 * @author yuanjg
 * @create 2019-10-06 17:39
 */

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;//数据源
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 默认配置类  包括了过滤器SocialAuthenticationFilter 添加到security过滤链中
     *
     * @return
     */
    @Bean
    public SpringSocialConfigurer welkinSpringSocialConfigurer() {
        WelkinSpringSocialConfigurer welkinSpringSocialConfigurer = new WelkinSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
        //修改默认注册界面
        welkinSpringSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignupUrl());
        return welkinSpringSocialConfigurer;
    }

    /**
     * 业务系统用户和服务提供商用户对应关系,保存在表UserConnection
     * JdbcUsersConnectionRepository.sql 中有建表语句
     * userId 业务系统Id
     * providerId 服务提供商的Id
     * providerUserId  同openId
     * Encryptors  加密策略 这里不加密
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //设定表UserConnection的前缀 表名不可以改变
        //jdbcUsersConnectionRepository.setTablePrefix("tiger_");
        return jdbcUsersConnectionRepository;
    }

    /**
     * 从认证中获取用户信息
     *
     * @return
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    //https://docs.spring.io/spring-social/docs/1.1.x-SNAPSHOT/reference/htmlsingle/#creating-connections-with-connectcontroller
    /*@Bean
    public ConnectController connectController(
            ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }*/

}
