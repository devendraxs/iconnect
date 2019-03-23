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
import iconnect.psi.com.iconnect.adapter.CompleteRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.CompleteList;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentComplete extends Fragment{
        private RecyclerView completeRecycler;
        private CompleteRecyclerAdapter completeRecyclerAdapter;
        private List<CompleteList.Datum> completeList=new ArrayList();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_complete,container,false);
        completeRecycler=view.findViewById(R.id.completeRecycler);
        completeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        getCompleteData();
        return view;
    }

    private void getCompleteData() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("user_id","PSI/0010");
        ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.showCompleteData(hashMap).enqueue(new Callback<CompleteList>() {
            @Override
            public void onResponse(Call<CompleteList> call, Response<CompleteList> response) {
                if (response.isSuccessful()){
                    completeList=response.body().getData();
                    setAdapter(completeList);
                    Log.e("response size",""+completeList.size());
                }

            }

            @Override
            public void onFailure(Call<CompleteList> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<CompleteList.Datum> completeList) {
        completeRecyclerAdapter = new CompleteRecyclerAdapter(getActivity(), completeList);
        completeRecycler.setHasFixedSize(true);
        completeRecycler.setAdapter(completeRecyclerAdapter);
    }
}
