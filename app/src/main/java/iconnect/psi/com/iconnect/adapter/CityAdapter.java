package iconnect.psi.com.iconnect.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.model.CityResponse;

public class CityAdapter extends ArrayAdapter<CityResponse> {

    private ArrayList<CityResponse> cityArrayList;
    private Context mContext;

    public CityAdapter(@NonNull Context context, int resource,ArrayList<CityResponse> city) {
        super(context, resource);
        this.cityArrayList=city;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return cityArrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        CityName holder=null;
        if (listItem==null){
            LayoutInflater layoutInflater= ((Activity) mContext).getLayoutInflater();
            listItem=layoutInflater.inflate(R.layout.row_city, parent, false);
            holder=new CityName();
            holder.tvCityName=listItem.findViewById(R.id.tv_city);
            listItem.setTag(holder);
        }else   {
            holder=(CityName)listItem.getTag();
            CityResponse cityResponse=cityArrayList.get(position);
            holder.tvCityName.setText(cityResponse.getData().get(position).getCityName());
            return listItem;
        }

        return super.getView(position, convertView, parent);
    }
    static class CityName{
        TextView tvCityName;
    }
}
