package io.github.yuanjg.core.social.qq;

import io.github.yuanjg.core.properties.QQProperties;
import io.github.yuanjg.core.properties.SecurityProperties;
import io.github.yuanjg.core.social.qq.connect.QQOAuth2ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * qq社交自动配置类 （新老版本配置不一样）
 *
 * @author yuanjg
 * @create 2019-10-06 17:50
 */
@Configuration
//有这个配置才会生效
@ConditionalOnProperty(prefix = "welkin.auth.social.qq", name = "app-id")
public class QQAuthoConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /*
      SocialAutoConfigurerAdapter  高版本已经移除
      ConnectionFactory<?> createConnectionFactory() 创建连接工厂
      参见  https://docs.spring.io/spring-social/docs/1.1.x/
   */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(createConnectionFactory());
    }

    private ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQOAuth2ConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }

    // 后补：做到处理注册逻辑的时候发现的一个bug：登录完成后，数据库没有数据，但是再次登录却不用注册了
    // 就怀疑是否是在内存中存储了。结果果然发现这里父类的内存ConnectionRepository覆盖了SocialConfig中配置的jdbcConnectionRepository
    // 这里需要返回null，否则会返回内存的 ConnectionRepository
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}

