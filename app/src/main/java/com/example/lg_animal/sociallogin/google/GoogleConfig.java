package com.example.lg_animal.sociallogin.google;

import com.example.lg_animal.sociallogin.impl.SocialConfig;

/**
 * SocialLogin
 * Class: GoogleConfig
 * <p>
 * Description:
 */

public class GoogleConfig extends SocialConfig {
    private boolean requireEmail = false;

    private GoogleConfig(boolean requireEmail) {
        this.requireEmail = requireEmail;
    }

    public boolean isRequireEmail() {
        return requireEmail;
    }

    public static class Builder {
        private boolean requireEmail = false;

        public Builder setRequireEmail() {
            requireEmail = true;
            return this;
        }

        public GoogleConfig build() {
            return new GoogleConfig(requireEmail);
        }
    }
}
