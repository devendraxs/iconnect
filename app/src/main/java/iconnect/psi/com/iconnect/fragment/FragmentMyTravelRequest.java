package iconnect.psi.com.iconnect.fragment;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.NavigationDrawerActivity;
import iconnect.psi.com.iconnect.adapter.TravelPagerAdapter;

public class FragmentMyTravelRequest extends Fragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    private NavigationDrawerActivity mActivity;
    private TravelPagerAdapter travelPagerAdapter;
    private android.support.v7.widget.Toolbar toolbar;
    private Button newTravelRequest;
    private ImageView floppy,showSubmit,showReject,showApproved,showComplete;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mytravel_request,container,false);
        viewPager =view.findViewById(R.id.pager);
        mActivity= (NavigationDrawerActivity) getActivity();

        floppy=view.findViewById(R.id.floppy);
        showSubmit=view.findViewById(R.id.showSubmit);
        showReject=view.findViewById(R.id.showReject);
        showApproved=view.findViewById(R.id.showApproved);
        showComplete=view.findViewById(R.id.showComplete);

         toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
       //tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.pager);
        newTravelRequest=view.findViewById(R.id.newTravelRequest);

        newTravelRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              FragmentCreateNewTravelRequest fragmentCreateNewTravelRequest=new FragmentCreateNewTravelRequest();
              mActivity.replaceFragment(mActivity,R.id.ll_dashboard_container_fragment,fragmentCreateNewTravelRequest,null,false);


            }
        });

        //Adding the tabs using addTab() method

      /*  tabLayout.addTab(tabLayout.newTab().setText("Saved"));
        tabLayout.addTab(tabLayout.newTab().setText("Submitted"));
        tabLayout.addTab(tabLayout.newTab().setText("Rejected"));
        tabLayout.addTab(tabLayout.newTab().setText("Approved"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/

        //Initializing viewPager
/*
        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setAdapter(pagerAdapter);
            }
        });
*/

        travelPagerAdapter=new TravelPagerAdapter(mActivity.getSupportFragmentManager());
        viewPager.setAdapter(travelPagerAdapter);

        floppy.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }

    private void onChangeTab(int position) {
        if (position==0){
            floppy.setImageDrawable(Drawable.createFromPath("plus"));
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
    }
}
