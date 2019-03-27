package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.fragment.FragmentCity;
import iconnect.psi.com.iconnect.model.CityResponse;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.MyViewHolder>{
    List<CityResponse.Datum> cityList;
    List<CityResponse.Datum>  cityListFilter;
    private FragmentCity mContext;
  private OnClickListener mListener;
  CityAdapterListener listener;

    public CityRecyclerAdapter(Context context, List<CityResponse.Datum> cityName) {
        this.cityListFilter=cityName;
        this.cityList=cityName;
    }

/*
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    cityListFilter=cityList;

                }else {
                    List<CityResponse.Datum> filterList=new ArrayList<>();
                    for (CityResponse.Datum row:cityList){
                        if (row.getCityName().contains(charString.toLowerCase())){
                            filterList.add(row);

                        }
                    }
                    cityListFilter=cityList;

                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=cityListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cityListFilter=(ArrayList<CityResponse.Datum>) filterResults.values;


            }
        };
    }
*/

    public interface OnClickListener {
        boolean onItemClickListener(int position, View view, List<CityResponse.Datum> listcity);

    }
    public void setClickListener(CityRecyclerAdapter.OnClickListener mListener) {
        this.mListener=mListener;
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
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView city;

        public MyViewHolder(View view)
        {
            super(view);
            city =view.findViewById(R.id.tv_city);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCitySelected(cityListFilter.get(getAdapterPosition()));
                    
                }
            });
        }
        }
        public CityRecyclerAdapter(FragmentCity context, List<CityResponse.Datum> cityList, CityAdapterListener listener){
        this.mContext=context;
        this.cityList=cityList;
        this.cityListFilter= cityList;
        this.listener=listener;
        }

     public interface CityAdapterListener {
        void onCitySelected(CityResponse.Datum cityResponse);
    }
    }
