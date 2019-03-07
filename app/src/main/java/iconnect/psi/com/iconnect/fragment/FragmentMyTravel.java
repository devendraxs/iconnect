package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.NavigationDrawerActivity;

public class FragmentMyTravel extends Fragment implements View.OnClickListener {
    private Button travelRequest,approvedRequest;
    private NavigationDrawerActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mytravel,container,false);
        mActivity= (NavigationDrawerActivity)getActivity();
        setViews(view);
        return view;
    }

    private void setViews(View view) {
        travelRequest=view.findViewById(R.id.travelRequest);
        approvedRequest=view.findViewById(R.id.approvedRequest);
        travelRequest.setOnClickListener(this);
        approvedRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.travelRequest:
                FragmentMyTravelRequest fragmentMyTravelRequest=new FragmentMyTravelRequest();
                mActivity.replaceFragment(mActivity,R.id.ll_dashboard_container_fragment,fragmentMyTravelRequest,null,false);
                break;
            case R.id.approvedRequest:
                FragmentApprovedTravelRequest fragmentApprovedTravelRequest=new FragmentApprovedTravelRequest();
                mActivity.replaceFragment(mActivity,R.id.ll_dashboard_container_fragment,fragmentApprovedTravelRequest,null,false);
                break;

        }

    }
}
