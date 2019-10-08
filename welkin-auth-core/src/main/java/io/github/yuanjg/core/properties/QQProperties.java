package io.github.yuanjg.core.properties;

/**
 * qq配置文件
 *
 * @author yuanjg
 * @create 2019-10-06 17:47
 */

public class QQProperties {
    private String providerId = "qq";//供应商id

    private String appId;//应用id

    private String appSecret;//应用密匙

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
