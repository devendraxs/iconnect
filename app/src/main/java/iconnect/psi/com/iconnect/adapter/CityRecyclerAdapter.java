package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.model.CityResponse;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.MyViewHolder> {
    List<CityResponse.Datum> cityList;
    private Context mContext;

    public CityRecyclerAdapter(FragmentActivity activity, List<CityResponse.Datum> cityName) {
        this.cityList=cityName;
        this.mContext=activity;
    }

    @NonNull
    @Override
    public CityRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_city,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.city.setText(cityList.get(position).getCityName());

    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView city;

        public MyViewHolder(View view)
        {
            super(view);
            city =view.findViewById(R.id.tv_city);
        }
    }

}
