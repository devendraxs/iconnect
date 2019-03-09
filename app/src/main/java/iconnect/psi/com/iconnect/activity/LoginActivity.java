package iconnect.psi.com.iconnect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.sharedprefs.SharedPrefs;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText userName,password;
    private Button submit;
    SharedPrefs sharedPefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPefs.getInstance(this);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(this);
        userName.setText("chenchaiah@chenchaiah.com");
        password.setText("123456");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                Intent intent=new Intent(LoginActivity.this,NavigationDrawerActivity.class);
                startActivity(intent);
/*                if (Utility.isNetworkConnected(LoginActivity.this)){
                    if (userName.getText().toString().equalsIgnoreCase("")){
                        userName.setError("Please enter username");
                        userName.requestFocus();
                    }else if (password.getText().toString().equalsIgnoreCase("")){
                        password.setError("Please enter password");
                        password.requestFocus();
                    }else {
                        sendLoginData(userName.getText().toString().trim(), password.getText().toString().trim());

                    }
                }*//*else {
                    Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show();
                }break;*/
        }
    }

    private void sendLoginData(String username, String password) {
        Log.e("Send Data", "username = " + username + " password = " + password);

        Intent i = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
        i.putExtra("username","devendra");
        i.putExtra("password","12345");
        startActivity(i);

    }
}
