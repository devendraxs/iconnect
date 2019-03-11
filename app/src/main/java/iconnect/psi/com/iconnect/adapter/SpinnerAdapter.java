package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import iconnect.psi.com.iconnect.R;

public class SpinnerAdapter extends ArrayAdapter<String> {
    Context c;
    String [] name;
    int[] images;

    public SpinnerAdapter(Context ctx, String[] name, int[] images){
        super(ctx, R.layout.facilities_row,name);
        this.c=ctx;
        this.name=name;
        this.images=images;

    }

    @Override
    public View getDropDownView(int position,View convertView,ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.facilities_row,null);
        }

        TextView nameTv=convertView.findViewById(R.id.nameTv);
        ImageView icon=convertView.findViewById(R.id.icon);

        // Set Data
        nameTv.setText(name[position]);
        icon.setImageResource(images[position]);
        return convertView;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.facilities_row,null);
        }

        TextView nameTv=convertView.findViewById(R.id.nameTv);
        ImageView icon=convertView.findViewById(R.id.icon);

        // Set Data
        nameTv.setText(name[position]);
        icon.setImageResource(images[position]);
        return convertView;
    }
}

