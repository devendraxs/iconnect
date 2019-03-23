package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.model.ApprovedList;

public class ApprovedRecyclerAdapter extends RecyclerView.Adapter<ApprovedRecyclerAdapter.MyViewHolder> {
    private Context mContext;
  private List<ApprovedList.Datum> approvedList;

    public ApprovedRecyclerAdapter(FragmentActivity activity, List<ApprovedList.Datum> approvedList) {
        this.approvedList=approvedList;
        Log.e("Project Res"," ==== "+approvedList.size());
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
        Log.e("booking Id",""+approvedList.get(position).getBookingId());
        //ApprovedList.Datum datum=approvedList.get(position);
        //holder.tId.setText(datum.getId());
        holder.tId.setText(String.valueOf(approvedList.get(position).getBookingId()));
    }

    @Override
    public int getItemCount() {
        return approvedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
