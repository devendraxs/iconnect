package iconnect.psi.com.iconnect.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.NavigationDrawerActivity;
import iconnect.psi.com.iconnect.adapter.NewTravelRequestAdapter;

public class FragmentCreateNewTravelRequest extends Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayoutNewRequest;
    private ViewPager pagerNewRequest;
    private NavigationDrawerActivity mActivity;
    private NewTravelRequestAdapter newTravelRequestAdapter;
    private TextView itineary,purpose,advance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_newtravel_request,container,false);

        pagerNewRequest =view.findViewById(R.id.pagerNewRequest);
        mActivity= (NavigationDrawerActivity) getActivity();

        itineary=view.findViewById(R.id.itineary);
        purpose=view.findViewById(R.id.purpose);
        advance=view.findViewById(R.id.advance);

       // tabLayoutNewRequest = (TabLayout) view.findViewById(R.id.tabLayoutNewRequest);
        pagerNewRequest=(ViewPager)view.findViewById(R.id.pagerNewRequest);

       /* tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Itineary"));
        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Purpose"));
        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Advance"));*/


//        tabLayoutNewRequest.setTabGravity(TabLayout.GRAVITY_FILL);

    /*
            tabLayoutNewRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pagerNewRequest.setAdapter(newTravelRequestAdapter);
                }
            });
    */
       newTravelRequestAdapter=new  NewTravelRequestAdapter(mActivity.getSupportFragmentManager());
       pagerNewRequest.setAdapter(newTravelRequestAdapter);
        itineary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerNewRequest.setCurrentItem(0);
            }
        });
        purpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerNewRequest.setCurrentItem(1);
            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerNewRequest.setCurrentItem(2);
            }
        });

       pagerNewRequest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        new setAdapterTask().execute();


        return view;
    }

    private void onChangeTab(int position) {
    if (position==0){
        itineary.setTextSize(20);
        itineary.setTextColor(Color.RED);
        purpose.setTextColor(Color.WHITE);
        advance.setTextColor(Color.WHITE);
        purpose.setTextSize(20);
        advance.setTextSize(20);
    }
        if (position==1){
            itineary.setTextSize(20);
            purpose.setTextSize(20);
            purpose.setTextColor(Color.RED);
            itineary.setTextColor(Color.WHITE);
            advance.setTextColor(Color.WHITE);
            advance.setTextSize(20);
        }
        if (position==2){
            itineary.setTextSize(20);
            purpose.setTextSize(20);
            advance.setTextSize(20);
            advance.setTextColor(Color.RED);
            itineary.setTextColor(Color.WHITE);
            purpose.setTextColor(Color.WHITE);
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
    class setAdapterTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pagerNewRequest.setAdapter(newTravelRequestAdapter);

        }
    }

}
