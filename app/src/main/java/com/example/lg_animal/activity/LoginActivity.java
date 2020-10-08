package com.example.lg_animal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lg_animal.R;
import com.example.lg_animal.sociallogin.SocialLogin;
import com.example.lg_animal.sociallogin.impl.OnResponseListener;
import com.example.lg_animal.sociallogin.impl.ResultType;
import com.example.lg_animal.sociallogin.impl.SocialType;
import com.example.lg_animal.sociallogin.impl.UserInfoType;
import com.example.lg_animal.sociallogin.kakao.KakaoConfig;
import com.example.lg_animal.sociallogin.kakao.KakaoLogin;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mEmailLoginButton;
    private Button mJoinButton;
    private Button setting_button;
    private ProgressBar mProgressView;
    // sociallogin
    private KakaoLogin kakaoModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        mEmailLoginButton = (Button) findViewById(R.id.login_button);
        mJoinButton = (Button) findViewById(R.id.join_button);
        setting_button = (Button) findViewById(R.id.setting_button);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

        mEmailLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                // sociallogin
                // socialLoginInit();
            }
        });
        mJoinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        setting_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingMainActivity.class);
                startActivity(intent);
            }
        });
        setting_button.performClick();
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mEmailView.setError("비밀번호를 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //startLogin(new LoginData(email, password));
            showProgress(true);
        }
    }

//    private void startLogin(LoginData data) {
//        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                LoginResponse result = response.body();
//                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
//                showProgress(false);
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
//                Log.e("로그인 에러 발생", t.getMessage());
//                showProgress(false);
//            }
//        });
//    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void socialLoginInit() {
        SocialLogin.init(this);
        KakaoConfig kakaoConfig = new KakaoConfig.Builder()
                .setRequireEmail()
                .setRequireAgeRange()
                .setRequireBirthday()
                .setRequireGender()
                .build();

        SocialLogin.addType(SocialType.KAKAO, kakaoConfig);
        kakaoModule = new KakaoLogin(this, new OnResponseListener() {
            @Override
            public void onResult(SocialType socialType, ResultType resultType, Map<UserInfoType, String> map) {

            }
        });

        if (kakaoModule != null) {
            kakaoModule.onLogin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (kakaoModule != null) {
            kakaoModule.onDestroy();
        }
    }
}

