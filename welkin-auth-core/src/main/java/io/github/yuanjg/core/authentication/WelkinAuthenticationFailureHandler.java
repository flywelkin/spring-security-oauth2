package io.github.yuanjg.core.authentication;

import com.alibaba.fastjson.JSON;
import io.github.yuanjg.core.model.enums.LoginTypeEnum;
import io.github.yuanjg.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author yuanjg
 * @create 2019-10-06 8:58
 */
@Slf4j
@Component("welkinAuthenticationFailureHandler")
public class WelkinAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");
        if (LoginTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(exception.getMessage()));
        } else {
            // 如果用户配置为跳转，则跳到Spring Boot默认的错误页面
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
