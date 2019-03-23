package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.RejectRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.RejectList;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentReject extends Fragment {
    private RecyclerView recyclerRejected;
    private RejectRecyclerAdapter rejectRecyclerAdapter;
    private List<RejectList.Datum> rejectList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_reject,container,false);
        recyclerRejected=view.findViewById(R.id.recyclerRejected);
        recyclerRejected.setLayoutManager(new LinearLayoutManager(getActivity()));
        getRejectedData();
        return view;
    }

    private void getRejectedData() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("user_id","PSI/0010");
        ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.showRejectData(hashMap).enqueue(new Callback<RejectList>() {
            @Override
            public void onResponse(Call<RejectList> call, Response<RejectList> response) {
                if (response.isSuccessful()){
                    rejectList=response.body().getData();
                    setAdapter(rejectList);
                    Log.e("response size",""+rejectList.size());
                }
            }

            @Override
            public void onFailure(Call<RejectList> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<RejectList.Datum> rejectList) {
        rejectRecyclerAdapter=new RejectRecyclerAdapter(getActivity(),rejectList);
        recyclerRejected.setHasFixedSize(true);
        recyclerRejected.setAdapter(rejectRecyclerAdapter);
    }

}
