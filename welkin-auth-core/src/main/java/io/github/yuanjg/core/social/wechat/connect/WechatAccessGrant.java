package io.github.yuanjg.core.social.wechat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * 微信的access_token信息。与标准OAuth2协议不同，微信在获取access_token时会同时返回openId,
 * 并没有单独的通过accessToke换取openId的服务所以在这里继承了标准AccessGrant，添加了openId字段，
 * 作为对微信access_token信息的封装。
 * @author yuanjg
 * @create 2019-10-07 8:29
 */

public class WechatAccessGrant extends AccessGrant {

    private String openId;

    public WechatAccessGrant() {
        super("");
    }

    public WechatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}

