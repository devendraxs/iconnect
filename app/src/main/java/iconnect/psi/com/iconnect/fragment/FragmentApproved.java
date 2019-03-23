package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.ApprovedRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.ApprovedList;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentApproved extends Fragment {
    private List<ApprovedList.Datum> approvedList=new ArrayList<>();
    private ApprovedRecyclerAdapter approvedRecyclerAdapter;
    private RecyclerView approvedRecycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_approved,container,false);
        approvedRecycler=view.findViewById(R.id.approvedRecycler);
        approvedRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        getApprovedData();
        return view;
    }

    private void getApprovedData() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("user_id","PSI/0010");
        ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.showApprovedData(hashMap).enqueue(new Callback<ApprovedList>() {
            @Override
            public void onResponse(Call<ApprovedList> call, Response<ApprovedList> response) {
                if (response.isSuccessful()){
                    approvedList=response.body().getData();
                    setAdapter(approvedList);

                }
            }

            @Override
            public void onFailure(Call<ApprovedList> call, Throwable t) {

            }
        });

    }
    private void setAdapter(List<ApprovedList.Datum> approvedList){
        approvedRecyclerAdapter=new ApprovedRecyclerAdapter(getActivity(),approvedList);
        approvedRecycler.setHasFixedSize(true);
        approvedRecycler.setAdapter(approvedRecyclerAdapter);


    }
}
