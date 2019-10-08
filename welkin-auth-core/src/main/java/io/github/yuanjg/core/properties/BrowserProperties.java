package io.github.yuanjg.core.properties;

import io.github.yuanjg.core.model.enums.LoginTypeEnum;

/**
 * 浏览器配置
 *
 * @author yuanjg
 * @create 2019-10-06 8:41
 */

public class BrowserProperties {
    /**
     * 登录页面 不配置默认标准登录界面
     */
    private String loginPage = "/welkin-login.html";

    /**
     * 退出登录
     */
    private String loginOut = "/loginOut";

    /**
     * 跳转类型 默认返回json数据
     */
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;

    /**
     * 注册页面
     */
    private String signupUrl = "/welkin-signup.html";

    /**
     * 记住我秒数
     *
     * @return
     */
    private int remberMeSeconds = 3600;

    /**
     * 浏览器session配置
     */
    private SessionProperties session = new SessionProperties();


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public int getRemberMeSeconds() {
        return remberMeSeconds;
    }

    public void setRemberMeSeconds(int remberMeSeconds) {
        this.remberMeSeconds = remberMeSeconds;
    }

    public String getSignupUrl() {
        return signupUrl;
    }

    public void setSignupUrl(String signupUrl) {
        this.signupUrl = signupUrl;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getLoginOut() {
        return loginOut;
    }

    public void setLoginOut(String loginOut) {
        this.loginOut = loginOut;
    }
}


