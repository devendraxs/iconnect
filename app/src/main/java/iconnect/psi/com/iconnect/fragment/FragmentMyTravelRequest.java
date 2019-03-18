package iconnect.psi.com.iconnect.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.BaseActivity;
import iconnect.psi.com.iconnect.activity.NavigationDrawerActivity;

public class FragmentMyTravelRequest extends BaseActivity implements View.OnClickListener {
    //This is our tablayout
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public Button newTravelRequest;
    private NavigationDrawerActivity mActivity;
    private String emp_name,Designation,CostCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mytravel_request);

        Intent intent=getIntent();
        emp_name=intent.getStringExtra("emp_name");
        Designation=intent.getStringExtra("Designation");
        CostCenter=intent.getStringExtra("CostCenter");


        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPager =  findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        newTravelRequest=findViewById(R.id.newTravelRequest);
        newTravelRequest.setOnClickListener(this);
        newTravelRequest.setVisibility(View.VISIBLE);

        tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }



        private void setupTabIcons(){
            int[] tabIcons = {R.drawable.save,
                    R.drawable.submit,
                    R.drawable.thumb_down,
                    R.drawable.thumbs_up,
                    R.drawable.complete

            };

            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
            tabLayout.getTabAt(3).setIcon(tabIcons[3]);
            tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new FragmentSave(), "ONE");
            adapter.addFrag(new FragmentSubmit(), "TWO");
            adapter.addFrag(new FragmentReject(), "THREE");
            adapter.addFrag(new FragmentApproved(), "FOUR");
            adapter.addFrag(new FragmentComplete(), "FIVE");
            viewPager.setAdapter(adapter);
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newTravelRequest:
               // FragmentCreateNewTravelRequest fragmentCreateNewTravelRequest=new FragmentCreateNewTravelRequest();

            /*    Bundle bundle=new Bundle();
                bundle.putString("emp_name",emp_name);
                bundle.putString("Designation",Designation);
                bundle.putString("CostCenter",CostCenter);*/
                Intent intent=new Intent(this,FragmentCreateNewTravelRequest.class);
                startActivity(intent);
               // replaceFragment(FragmentMyTravelRequest.this,R.id.main_layout,fragmentCreateNewTravelRequest,bundle,true);
               // this.replaceFragment(this,R.id.ll_dashboard_container_fragment,fragmentCreateNewTravelRequest,null,false);
                }
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

/*
            public void addFrag(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }
*/

            @Override
            public CharSequence getPageTitle(int position) {

                // return null to display only the icon
                return null;
            }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        }

       // viewPager = findViewById(R.id.pager);
        //mActivity= (NavigationDrawerActivity) getActivity();

  /*      showSave=findViewById(R.id.showSave);
        showSubmit=findViewById(R.id.showSubmit);
        showReject=findViewById(R.id.showReject);
        showApproved=findViewById(R.id.showApproved);
        showComplete=findViewById(R.id.showComplete);

         toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
       //tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.pager);
        newTravelRequest=findViewById(R.id.newTravelRequest);

        newTravelRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              FragmentCreateNewTravelRequest fragmentCreateNewTravelRequest=new FragmentCreateNewTravelRequest();
              mActivity.replaceFragment(mActivity,R.id.ll_dashboard_container_fragment,fragmentCreateNewTravelRequest,null,false);


            }
        });

        //Adding the tabs using addTab() method

        tabLayout.addTab(tabLayout.newTab().setText("Saved"));
        tabLayout.addTab(tabLayout.newTab().setText("Submitted"));
        tabLayout.addTab(tabLayout.newTab().setText("Rejected"));
        tabLayout.addTab(tabLayout.newTab().setText("Approved"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setAdapter(pagerAdapter);
            }
        });

        travelPagerAdapter=new TravelPagerAdapter(mActivity.getSupportFragmentManager());
        viewPager.setAdapter(travelPagerAdapter);

        showSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                Toast.makeText(mActivity, "show save data", Toast.LENGTH_SHORT).show();
            }
        });
        showSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                Toast.makeText(mActivity, "show submitted data", Toast.LENGTH_SHORT).show();
            }
        });
        showReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                Toast.makeText(mActivity, "show reject data", Toast.LENGTH_SHORT).show();
            }
        });
        showApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
                Toast.makeText(mActivity, "show approved data", Toast.LENGTH_SHORT).show();
            }
        });
        showComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(4);
                Toast.makeText(mActivity, "show complete data", Toast.LENGTH_SHORT).show();
            }
        });
        
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int ositionOffsetpixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
       // pagerAdapter = new iconnect.psi.com.iconnect.adapter.TravelPagerAdapter(mActivity.getSupportFragmentManager());
        new setAdapterTask().execute();

       // return view;
    }

    private void onChangeTab(int position) {
        if (position==0){
            showSave.setImageDrawable(Drawable.createFromPath("plus"));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    class setAdapterTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            viewPager.setAdapter(travelPagerAdapter);

        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,NavigationDrawerActivity.class));
    }
}
