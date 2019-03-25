package iconnect.psi.com.iconnect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.LoginResponse;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import iconnect.psi.com.iconnect.sharedprefs.SharedPrefs;
import iconnect.psi.com.iconnect.utils.Utility1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_userName,et_password;
    private Button submit;
    SharedPrefs sharedPefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPefs.getInstance(this);
        et_userName=findViewById(R.id.et_userName);
        et_password=findViewById(R.id.et_password);
        submit=findViewById(R.id.login);
        submit.setOnClickListener(this);
      /*  et_userName.setText("rosily@psi.org.in");
        et_password.setText("rose*2014");*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
               /* Intent intent=new Intent(LoginActivity.this,NavigationDrawerActivity.class);
               startActivity(intent);*/
                if (Utility1.isNetworkConnected(LoginActivity.this)){
                    if (et_userName.getText().toString().equalsIgnoreCase("")){
                        et_userName.setError("Please enter username");
                        et_userName.requestFocus();
                    }else if (et_password.getText().toString().equalsIgnoreCase("")){
                        et_password.setError("Please enter password");
                        et_password.requestFocus();
                    }else {
                        sendLoginData(et_userName.getText().toString().trim(),et_password.getText().toString().trim());
                    }
                }else {
                    Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void sendLoginData(String username, String password) {
        // login_btn.setClickable(false);
        Log.e("Send Data", "Email = " + username + " Password = " + password  );
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_name",et_userName.getText().toString().trim());
        hashMap.put("password", et_password.getText().toString().trim());
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        ApiInterface apiInterface=ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendLoginData(hashMap).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("Sign in Url", "" + call.request().url());
                if (response.body().getErrorCode()==1) {

                    Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                    intent.putExtra("emp_name", response.body().getData().getEmpName());
                    intent.putExtra("Designation", response.body().getData().getDesignation());
                    intent.putExtra("CostCenter", response.body().getData().getCostCenter());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Login Failed", "" + t.getMessage());
                Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        //ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
/*
        apiClient.sendLoginData(hashMap).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("Sign in Url", "" + call.request().url());

                Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                intent.putExtra("emp_name",response.body().getData().getEmpName());
                intent.putExtra("Designation",response.body().getData().getDesignation());
                intent.putExtra("CostCenter",response.body().getData().getCostCenter());
                startActivity(intent);
                }
                // Log.e("Num of village",response.body().getCountData().getCountVillage().get(0).getVillage());
*/
/*
                if (response.body().getSucc())
                {
                    Intent i = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                    i.putExtra("sc_name", response.body().getData().getName());
                    i.putExtra("sc_id", response.body().getData().getSc_id());
                    i.putExtra("sc_email", response.body().getData().getEmail());
                    i.putExtra("sc_phone", response.body().getData().getPhone());
                      *//*

*/
/*  i.putExtra("num_of_village", response.body().getCountData().getCountVillage().get(0).getVillage());
                        i.putExtra("num_of_members", response.body().getCountData().getCountMembers().get(0).getMembers());
                        i.putExtra("num_of_visits", response.body().getCountData().getCountVisits().get(0).getVisit());
                        Log.e("NumOfVisits",""+response.body().getCountData().getCountVisits().get(0).getVisit());*//*
*/
/*

                    startActivity(i);
                }
*//*


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Login Failed", "" + t.getMessage());
                Toast.makeText(LoginActivity.this, "wrong login credentials", Toast.LENGTH_SHORT).show();
                //login_btn.setClickable(true);

            }
        });
*/
    }


/*
    private void sendLoginData(String username, String password) {
        Log.e("Send Data", "username = " + username + " password = " + password);

        Intent i = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
        i.putExtra("username","devendra");
        i.putExtra("password","12345");
        startActivity(i);

    }
*/
}
