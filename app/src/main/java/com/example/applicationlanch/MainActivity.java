package com.example.applicationlanch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText login,
                     password;

    public static final String LOGIN = "LOGIN",
                               PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.edit_text_main_login);
        password = findViewById(R.id.edit_text_main_password);
    }

    private String getLogin() {
        return getString(login.getText());
    }

    private String getPassword() {
        return getString(password.getText());
    }

    private String getString(Editable text) {
        return text.toString().trim();
    }

    public void onClickBuy(View view) {

        String login = getLogin();
        String password = getPassword();

        if (!login.isEmpty() && !password.isEmpty()) {

            Intent intent = new Intent(this, MenuActivity.class);

            intent.putExtra(LOGIN, login);
            intent.putExtra(PASSWORD, password);

            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.toast_main_clean_login_or_password, Toast.LENGTH_LONG).show();
        }

    }
}