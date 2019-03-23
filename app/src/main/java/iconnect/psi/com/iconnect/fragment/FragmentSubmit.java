package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.SubmitRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.BookingList;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSubmit extends Fragment
{

    private RecyclerView submitRecyclerView;
    private SubmitRecyclerAdapter submitRecyclerAdapter;
    private List<BookingList.Datum> bookingList=new ArrayList();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_submit,container,false);
        submitRecyclerView=view.findViewById(R.id.submitRecyclerView);



        return view;
    }

    private void getBookingData() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("user_id","PSI/0010");
        ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.showBookingData(hashMap).enqueue(new Callback<BookingList>() {
            @Override
            public void onResponse(Call<BookingList> call, Response<BookingList> response)
            {
                if (response.isSuccessful()) {
                    bookingList=response.body().getData();
                    setAdapter(bookingList);
                    Log.e("response size",""+bookingList.size());
                }
            }
            @Override
            public void onFailure(Call<BookingList> call, Throwable t) {
                Log.e("SubmitFragment",""+t.getMessage());

            }
        });
    }

    private void setAdapter(List<BookingList.Datum> bookingist)
    {
        submitRecyclerAdapter = new SubmitRecyclerAdapter(getActivity(), bookingist);
        submitRecyclerView.setHasFixedSize(true);
        submitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        submitRecyclerView.setAdapter(submitRecyclerAdapter);
        DividerItemDecoration itemDecor = new DividerItemDecoration( getActivity() , DividerItemDecoration.VERTICAL);
        submitRecyclerView.addItemDecoration(itemDecor);
        submitRecyclerView.setLayoutManager(new LinearLayoutManager( getActivity()));
        submitRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getBookingData();
        Toast.makeText(getActivity(), "show submit data", Toast.LENGTH_SHORT).show();
    }
}
