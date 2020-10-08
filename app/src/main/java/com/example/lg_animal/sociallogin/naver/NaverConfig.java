package com.example.lg_animal.sociallogin.naver;

import com.example.lg_animal.sociallogin.impl.SocialConfig;

/**
 * SocialLogin
 * Class: TwitterConfig
 * <p>
 * Description:
 */

public class NaverConfig extends SocialConfig {
    private String authClientId;
    private String authClientSecret;
    private String clientName;

    private NaverConfig(String authClientId, String authClientSecret, String clientName) {
        this.authClientId = authClientId;
        this.authClientSecret = authClientSecret;
        this.clientName = clientName;
    }

    public String getAuthClientId() {
        return authClientId;
    }

    public String getAuthClientSecret() {
        return authClientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public static class Builder {
        private String oAuthClientId;
        private String oAuthClientSecret;
        private String clientName;

        public Builder setAuthClientId(String oAuthClientId) {
            this.oAuthClientId = oAuthClientId;
            return this;
        }

        public Builder setAuthClientSecret(String oAuthClientSecret) {
            this.oAuthClientSecret = oAuthClientSecret;
            return this;
        }

        public Builder setClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public NaverConfig build() {
            return new NaverConfig(oAuthClientId, oAuthClientSecret, clientName);
        }
    }
}
