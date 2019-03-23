package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
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
import iconnect.psi.com.iconnect.model.BookingList;

public class SubmitRecyclerAdapter extends RecyclerView.Adapter<SubmitRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<BookingList.Datum> bookLIst;
    private Context mContext;
    private MyRecyclerAdapter.OnClickListener mListener;

    public void setClickListener(MyRecyclerAdapter.OnClickListener mListener) {
        this.mListener = mListener;
    }

    public SubmitRecyclerAdapter(FragmentActivity activity, List<BookingList.Datum> bookingist) {
        this.bookLIst=bookingist;
        Log.e("Project Res"," ==== "+bookingist.size());
        this.mContext=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
//        return new MyViewHolder(itemView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        Log.e("booking Id",""+bookLIst.get(position).getBookingId());
         holder.tId.setText(String.valueOf(bookLIst.get(position).getBookingId()));
        holder.name.setText(""+bookLIst.get(position).getName());
         holder.fDate.setText(""+bookLIst.get(position).getStartDate());
         holder.tDate.setText(""+bookLIst.get(position).getEndDate());
    }

    @Override
    public int getItemCount() {
        return bookLIst.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RadioButton radioButton;
        public TextView tId,fDate,tDate,name,route;

        public MyViewHolder(View view) {
            super(view);
            tId=view.findViewById(R.id.tId);
            fDate=view.findViewById(R.id.fDate);
            tDate=view.findViewById(R.id.tDate);
            route=view.findViewById(R.id.route);
            name=view.findViewById(R.id.name);
        }
    }
}
