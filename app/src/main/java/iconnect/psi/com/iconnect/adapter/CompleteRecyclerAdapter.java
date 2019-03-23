package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.model.CompleteList;

public class CompleteRecyclerAdapter extends RecyclerView.Adapter<CompleteRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<CompleteList.Datum> completeList;

    public CompleteRecyclerAdapter(FragmentActivity activity, List<CompleteList.Datum> completeList) {
        this.completeList=completeList;
        Log.e("Project Res"," ==== "+completeList.size());
        this.mContext=activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("booking Id",""+completeList.get(position).getBookingId());
       holder.tId.setText(String.valueOf(completeList.get(position).getBookingId()));

    }

    @Override
    public int getItemCount() {
        return completeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RadioButton radioButton;
        private TextView tId,fDate,tDate,name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tId=itemView.findViewById(R.id.tId);
            fDate=itemView.findViewById(R.id.fDate);
            tDate=itemView.findViewById(R.id.tDate);
            name=itemView.findViewById(R.id.name);
        }
    }
}
