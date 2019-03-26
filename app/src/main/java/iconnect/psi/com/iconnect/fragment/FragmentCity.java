package iconnect.psi.com.iconnect.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import iconnect.psi.com.iconnect.activity.CreateNewTravelRequestActivity;
import iconnect.psi.com.iconnect.adapter.CityRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.CityResponse;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class FragmentCity extends DialogFragment implements View.OnClickListener {
    private RecyclerView mCityRecyclerView;
    private CityRecyclerAdapter mAdapter;
    private List<CityResponse.Datum> cityName=new ArrayList<CityResponse.Datum>();
    private ArrayList<String> list=new ArrayList<>();

    @SuppressLint("ValidFragment")
    public FragmentCity(CreateNewTravelRequestActivity createNewTravelRequestActivity) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.city_dialog,container,false);
        mCityRecyclerView = view.findViewById(R.id.cityRecyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCityData();
        return view;
    }

    private void getCityData() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        ApiInterface apiInterface= ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendCityResponse(hashMap).enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                Log.e("Sign in Url", "" + call.request().url());
                cityName=response.body().getData();
                setAdapter(cityName);
/*
                for (int i=0;i<response.body().getData().size();i++) {
                    String city=response.body().getData().get(i).getCityName();
                    list.add(city);
                }
*/
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.e("Login Failed", "" + t.getMessage());
            }
        });
    }

    private void setAdapter(List<CityResponse.Datum> cityName) {
        mAdapter = new CityRecyclerAdapter (getActivity(),cityName);
        mCityRecyclerView.setHasFixedSize(true);

        mCityRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}
