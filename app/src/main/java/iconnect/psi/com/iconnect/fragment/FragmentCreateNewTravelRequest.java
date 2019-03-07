package iconnect.psi.com.iconnect.fragment;

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

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.NavigationDrawerActivity;
import iconnect.psi.com.iconnect.adapter.NewTravelRequestAdapter;

public class FragmentCreateNewTravelRequest extends Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayoutNewRequest;
    private ViewPager pagerNewRequest;
    private NavigationDrawerActivity mActivity;
    private NewTravelRequestAdapter newTravelRequestAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_newtravel_request,container,false);

        pagerNewRequest =view.findViewById(R.id.pagerNewRequest);
        mActivity= (NavigationDrawerActivity) getActivity();

        tabLayoutNewRequest = (TabLayout) view.findViewById(R.id.tabLayoutNewRequest);
        pagerNewRequest=(ViewPager)view.findViewById(R.id.pagerNewRequest);

        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Itineary"));
        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Purpose"));
        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Advance"));


        tabLayoutNewRequest.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayoutNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerNewRequest.setAdapter(newTravelRequestAdapter);
            }
        });
       newTravelRequestAdapter=new  NewTravelRequestAdapter(mActivity.getSupportFragmentManager());
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
