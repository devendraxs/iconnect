package iconnect.psi.com.iconnect.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.CreateNewTravelRequestActivity;

public class DateParadimRecyclerview extends RecyclerView.Adapter<DateParadimRecyclerview.dateViewHolder> {

    private CreateNewTravelRequestActivity mContext;
    private ArrayList<String>dateList;
    String breakfast,lunch,dinner,none,insedendal,estd_perdiam;
    private int  calCheck2,calcheck3,calcheck4,calcheck5;

    public DateParadimRecyclerview(CreateNewTravelRequestActivity mContext, ArrayList<String> dateList, String breakfast, String lunch, String dinner, String none, String incedental, String estd_perdiam) {
        this.mContext = mContext;
        this.dateList = dateList;
        this.breakfast=breakfast;
        this.lunch=lunch;
        this.dinner=dinner;
        this.none=none;
        this.insedendal=incedental;
        this.estd_perdiam=estd_perdiam;
    }

    @NonNull
    @Override
    public dateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.perdiam_recyclerview,parent,false);
        //dateViewHolder holder =new dateViewHolder(view);
        return new dateViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final dateViewHolder holder, int position) {
        String data =dateList.get(position);
        holder.tvDateAdvance.setText(data);
        holder.check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.check1.isChecked())
                {
                    holder.check2.setChecked(false);
                    holder.check3.setChecked(false);
                    holder.check4.setChecked(false);
                }else {
                   holder.check2.setChecked(true);
                    holder.check3.setChecked(true);
                    holder.check4.setChecked(true);

                }


            }
        });

        holder.check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.check2.isChecked()){
                    double b1=Double.valueOf(estd_perdiam);
                    double b2=Double.valueOf(breakfast);
                    calCheck2= (int) ((b1*b2)/100);
                    Log.e("calcheck1",""+calCheck2);
                    mContext.seekBarCalculation(calCheck2);
                }

            }
        });
        holder.check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.check3.isChecked()){

                    double l1=Double.valueOf(estd_perdiam);
                    double l2=Double.valueOf(lunch);
                    calcheck3=((int)(l1*l2)/100);
                    mContext.seekBarCalculation(calCheck2,calcheck3);
                }
            }
        });

        holder.check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.check4.isChecked()) {
                    double d1 = Double.valueOf(estd_perdiam);
                    double d2 = Double.valueOf(dinner);
                    calcheck4 = (int) ((d1 * d2) / 100);
                    mContext.seekBarCalculation(calCheck2,calcheck3,calcheck4);
                }
            }
        });
        holder.check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.check5.isChecked()) {
                    double i1 = Double.valueOf(estd_perdiam);
                    double i2 = Double.valueOf(insedendal);
                    calcheck5 = (int) ((i1 * i2) / 100);
                    mContext.seekBarCalculation(calCheck2,calcheck3,calcheck4,calcheck5);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return dateList.size();
    }
    public class dateViewHolder extends RecyclerView.ViewHolder{
        TextView tvDateAdvance;
        CheckBox check1,check2,check3,check4,check5;



        public dateViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateAdvance=itemView  .findViewById(R.id.tvDateAdvance);
            check1=itemView.findViewById(R.id.check1);
            check2=itemView.findViewById(R.id.check2);
            check3=itemView.findViewById(R.id.check3);
            check4=itemView.findViewById(R.id.check4);
            check5=itemView.findViewById(R.id.check5);
        }
    }
}
