package iconnect.psi.com.iconnect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.DrawerItemNavigationAdapter;
import iconnect.psi.com.iconnect.fragment.FragmentAdvance;
import iconnect.psi.com.iconnect.fragment.FragmentItineary;
import iconnect.psi.com.iconnect.fragment.FragmentMyTravel;
import iconnect.psi.com.iconnect.fragment.FragmentPurpose;
import iconnect.psi.com.iconnect.fragment.FragmentUserProfile;
import iconnect.psi.com.iconnect.model.NavigationModel;

public class NavigationDrawerActivity extends BaseActivity implements View.OnClickListener {
    private String[] mNavigationDrawerItemTitle;
    private ListView mDrawerList;
    public DrawerLayout mDrawerLayout;
    public Toolbar mToolbar;
    public ActionBarDrawerToggle mActionBarDrawerToggle;
    private String TAG=NavigationDrawerActivity.class.getSimpleName(),username,password;
    public ImageView ivLogo;
    public TextView tvToolbar;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private LinearLayout llLeftDrawer;
    private MainActivity mActivity;
    private ExpandableListView mExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        password=intent.getStringExtra("password");

        setUpToolbar();
        setUpUserProfile();
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitle = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        llLeftDrawer = findViewById(R.id.ll_left_drawer);

        NavigationModel[] navigationDrawer = new NavigationModel[6];
        navigationDrawer[0]  = new NavigationModel(R.drawable.ic_profile, mNavigationDrawerItemTitle[0]);
        navigationDrawer[1]  = new NavigationModel(R.drawable.ic_profile, mNavigationDrawerItemTitle[1]);
        navigationDrawer[2]  = new NavigationModel(R.drawable.ic_profile, mNavigationDrawerItemTitle[2]);
        navigationDrawer[3]  = new NavigationModel(R.drawable.ic_profile, mNavigationDrawerItemTitle[3]);
        navigationDrawer[4]  = new NavigationModel(R.drawable.ic_profile, mNavigationDrawerItemTitle[4]);
        navigationDrawer[5]  = new NavigationModel(R.drawable.ic_profile, mNavigationDrawerItemTitle[5]);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        DrawerItemNavigationAdapter navigationAdapter=new DrawerItemNavigationAdapter(this,R.layout.nav_list_row,navigationDrawer);
        mDrawerList.setAdapter(navigationAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        setUpDrawerToggle();
     }

    private void setUpDrawerToggle() {

        mActionBarDrawerToggle = new ActionBarDrawerToggle(NavigationDrawerActivity.this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mActionBarDrawerToggle.syncState();
    }


    private void setUpUserProfile() {
        getSupportActionBar().setHomeButtonEnabled(true);
        FragmentUserProfile fragmentUserProfile=new FragmentUserProfile();
        replaceFragment(NavigationDrawerActivity.this,R.id.ll_dashboard_container_fragment,fragmentUserProfile,null,true);

    }

    private void setUpToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        ivLogo = mToolbar.findViewById(R.id.iv_logo);
        tvToolbar = mToolbar.findViewById(R.id.tv_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View view) {

    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

        private void selectItem(int position) {
            Fragment fragment=null;
            FragmentManager manager=null;
            FragmentTransaction transaction=null;
            switch (position){
                case 0:
                    setUpUserProfile();
                    mDrawerLayout.closeDrawers();

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    FragmentMyTravel fragmentMyTravel=new FragmentMyTravel();
                    manager=getSupportFragmentManager();
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.ll_dashboard_container_fragment,fragmentMyTravel).commit();
                    mDrawerLayout.closeDrawers();

                    break;
                case 5:
                    signOut();
                    break;
            }
        }
    }
    private void signOut() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
    @Override
    public void onBackPressed() {
        Fragment fragment=this.getSupportFragmentManager().findFragmentById(R.id.ll_dashboard_container_fragment);
        if (fragment instanceof FragmentUserProfile){
            finish();
        }else if (fragment instanceof FragmentMyTravel){
            FragmentUserProfile fragmentUserProfile=new FragmentUserProfile();
            this.replaceFragment(this,R.id.ll_dashboard_container_fragment,fragmentUserProfile,null,false);
        }else if (fragment instanceof FragmentItineary){
            FragmentUserProfile fragmentUserProfile=new FragmentUserProfile();
            this.replaceFragment(this,R.id.ll_dashboard_container_fragment,fragmentUserProfile,null,false);
        }else if (fragment instanceof FragmentPurpose){
            FragmentUserProfile fragmentUserProfile=new FragmentUserProfile();
            this.replaceFragment(this,R.id.ll_dashboard_container_fragment,fragmentUserProfile,null,false);
        }else if (fragment instanceof FragmentAdvance){
            FragmentUserProfile fragmentUserProfile=new FragmentUserProfile();
            this.replaceFragment(this,R.id.ll_dashboard_container_fragment,fragmentUserProfile,null,false);
        }
        super.onBackPressed();
    }
}
