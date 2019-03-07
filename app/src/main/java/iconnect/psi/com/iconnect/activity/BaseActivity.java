package iconnect.psi.com.iconnect.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import iconnect.psi.com.iconnect.R;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void replaceFragment(Activity mContext, int layout, Fragment fragment, Bundle bundle, Boolean addTobackStack){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        fragment.setArguments(bundle);
        ft.replace(layout,fragment,fragment.getTag());

        if (addTobackStack){
            ft.addToBackStack(mContext.getLocalClassName());
        }ft.commit();
    }
    public void showProgressDialog(Context mContext) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.show();
    }
    public void hideProgressDialog(Context mContext) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }

    }



//    public abstract void onTerminate();
}
