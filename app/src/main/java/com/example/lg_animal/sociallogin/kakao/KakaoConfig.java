package com.example.lg_animal.sociallogin.kakao;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.lg_animal.sociallogin.impl.SocialConfig;

/**
 * SocialLogin
 * Class: KakaoConfig
 * <p>
 * Description:
 */

public class KakaoConfig extends SocialConfig implements Serializable {
    private ArrayList<String> requestOptions;

    private KakaoConfig(ArrayList<String> requestOptions) {
        this.requestOptions = requestOptions;
    }

    public ArrayList<String> getRequestOptions() {
        return requestOptions;
    }

    public static class Builder {
        private boolean isRequireEmail = false;
        private boolean isRequireAgeRange = false;
        private boolean isRequireBirthday = false;
        private boolean isRequireGender = false;

        public Builder setRequireEmail() {
            isRequireEmail = true;
            return this;
        }

        public Builder setRequireAgeRange() {
            isRequireAgeRange = true;
            return this;
        }

        public Builder setRequireBirthday() {
            isRequireBirthday = true;
            return this;
        }

        public Builder setRequireGender() {
            isRequireGender = true;
            return this;
        }

        public KakaoConfig build() {
            // v 1.2.5 migrate with V1 -> V2
            // according to https://tinyurl.com/ycaf5yua
            ArrayList<String> requestOptions = new ArrayList<>();
            requestOptions.add("properties.nickname");
            requestOptions.add("properties.profile_image");
            requestOptions.add("properties.thumbnail_image");

            if (isRequireEmail) {
                requestOptions.add("kakao_account.email");
            }

            if (isRequireAgeRange) {
                requestOptions.add("kakao_account.age_range");
            }

            if (isRequireBirthday) {
                requestOptions.add("kakao_account.birthday");
            }

            if (isRequireGender) {
                requestOptions.add("kakao_account.gender");
            }

            return new KakaoConfig(requestOptions);
        }
    }
}
