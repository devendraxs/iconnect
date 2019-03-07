package iconnect.psi.com.iconnect.fragment;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.NavigationDrawerActivity;

public class FragmentMyTravelRequest extends Fragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    private NavigationDrawerActivity mActivity;
    private PagerAdapter pagerAdapter;
    private android.support.v7.widget.Toolbar toolbar;
    private Button newTravelRequest;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mytravel_request,container,false);
        viewPager =view.findViewById(R.id.pager);
        mActivity= (NavigationDrawerActivity) getActivity();

         toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
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
        pagerAdapter = new iconnect.psi.com.iconnect.adapter.PagerAdapter(mActivity.getSupportFragmentManager());
        new setAdapterTask().execute();

        return view;
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
            viewPager.setAdapter(pagerAdapter);

        }
    }
}
