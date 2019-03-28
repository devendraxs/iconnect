package iconnect.psi.com.iconnect.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.model.ProjectResponse;

public class MyRecyclerAdapter  extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private CheckBox projectCheckbox;
    private OnClickListener mListener;
    List<ProjectResponse.Datum> projectList;
    private Context mContext;
    List<ProjectResponse.Datum> projectCounting=new ArrayList<>();


    public interface OnClickListener {
        void onItemClickListener(int position, View view, List<ProjectResponse.Datum> listdOrder);
    }

    public void setClickListener(OnClickListener mListener) {
        this.mListener = mListener;
    }

    public MyRecyclerAdapter(FragmentActivity activity, List<ProjectResponse.Datum> projectName) {
        this.projectList=projectName;
        Log.e("Project Res",""+projectList.size());
        this.mListener=mListener;
        this.mContext=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
       // ProjectResponse.Datum projectResponse = projectList.get(position);
        holder.project.setText(projectList.get(position).getProjectName());

        holder.projectCheckbox.setChecked(projectList.get(position).getselected());
        holder.projectCheckbox.setTag(position);

        holder.projectCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Integer pos = (Integer) holder.projectCheckbox.getTag();
                    if (projectList.get(pos).getselected()) {
                        projectList.get(pos).setSelected(false);
                        projectCounting.remove(projectCounting.get(pos));
                        Log.e(" Dialog remove ", " =========================== " + projectCounting.size());
                        mListener.onItemClickListener(pos, v, projectCounting);
                        notifyDataSetChanged();
                    } else {
                        projectList.get(pos).setSelected(true);
                        ProjectResponse.Datum order = new ProjectResponse.Datum();
                        order.setProjectName(projectList.get(pos).getProjectName());
                        order.setProjectCode(projectList.get(pos).getProjectCode());
                        projectCounting.add(order);
                        Log.e( "Dialog data 2 ", " =========================== " + projectCounting.size());
                        mListener.onItemClickListener(pos,v,projectCounting);
                        notifyDataSetChanged();
                    }
                }catch (IndexOutOfBoundsException e){
                    Log.e("Error", "" +e);
                    e.printStackTrace();
                }
            }
        });

    }
    public List<ProjectResponse.Datum>checkedData(){
        return  projectList;
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView project, percentage;
        public CheckBox projectCheckbox;
        public MyViewHolder(View view)
        {
            super(view);
            project =view.findViewById(R.id.projectView);
            percentage = view.findViewById(R.id.percentage);
            projectCheckbox=view.findViewById(R.id.projectCheckbox);
        }
    }


}