package iconnect.psi.com.iconnect.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.CallBackResult;
import iconnect.psi.com.iconnect.adapter.MyRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.ProjectResponse;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class FragmentProject extends DialogFragment implements View.OnClickListener {
    public static String projectListName;
    public  MyDialogFragmentListenerProject listenerProject;
    int intial_position=0;
    Context context;
    CallBackResult callBackResult;


/*
    public FragmentProject(CreateNewTravelRequestActivity createNewTravelRequestActivity){
    }
*/

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private Button cancle,ok;
    private List<ProjectResponse.Datum> projectSelected=new ArrayList<ProjectResponse.Datum>();
    private List<ProjectResponse.Datum> projectName=new ArrayList<ProjectResponse.Datum>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listenerProject = (MyDialogFragmentListenerProject) getActivity();
        }
        catch (ClassCastException e)
        {
            Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    /*
        @SuppressLint("ValidFragment")
        public FragmentProject(CreateNewTravelRequestActivity createNewTravelRequestActivity) {
            listener1 = null;
        }
    */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.project_dialog,container,false);


        mRecyclerView = view.findViewById(R.id.projectRecyclerview);
        cancle=view.findViewById(R.id.cancle);
        ok=view.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getProjectData();
              return view;
    }
     private  void getProjectData(){
         HashMap <String,String> hashMap=new HashMap();
         hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");

         ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
         apiInterface.sendProjectData(hashMap).enqueue(new Callback<ProjectResponse>() {
             @Override
             public void onResponse(Call<ProjectResponse> call, Response<ProjectResponse> response) {
                 if (response.isSuccessful()){

                        projectName = response.body().getData();
                        setAdapter(projectName);
                        Log.e("response size",""+projectName.size());

                     }
                 }

                 @Override
             public void onFailure(Call<ProjectResponse> call, Throwable t) {

             }
         });
      }



    private void setAdapter(final List<ProjectResponse.Datum> projectName) {
        adapter = new MyRecyclerAdapter(getActivity(),projectName);
        mRecyclerView.setHasFixedSize(true);

        adapter.setClickListener(new MyRecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClickListener(int position, View view, List<ProjectResponse.Datum> projectlist) {

//                callBackResult.getResult(String.valueOf(projectlist.size()));
                Log.e("Selected List", "" + projectlist.size());
                projectSelected = projectlist;

//                if(projectlist.size()==0){
//
//                }else{
//
//                }
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

/*
    public static FragmentProject newInstance() {
        FragmentProject f = new FragmentProject();
        return f;
    }
*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok:
                if (projectSelected.size() > -1) {

for(int i=0;i<projectSelected.size();i++) {
    projectListName = projectSelected.get(intial_position).projectName.trim();
    if(projectSelected.size()>1){
        listenerProject.onReturnValueProject("multproject");
    }
    else {
        listenerProject.onReturnValueProject(projectListName);
    }
}

        dismiss();

                    //FragmentItineary fragmentItineary = new FragmentItineary();
                   /* Intent intent=new Intent(getActivity(), CreateNewTravelRequestActivity.class);
                    intent.putExtra("select_project",projectSelected.size());
                    intent.putExtra("projact_name", projectSelected.get(0).getProjectName());
                    startActivity(intent);
                    getActivity().finish();*/
                    //dismiss();
                   /* Bundle b = new Bundle();
                    b.putInt("select_project", projectSelected.size());
                    b.putString("projact_name", projectSelected.get(0).getProjectName());
                    fragmentItineary.setArguments(b);*/

                   // callBackResult.getResult(String.valueOf(projectSelected.size()));
                } else {

                }
                break;
        }
    }


    public interface MyDialogFragmentListenerProject {
        public void onReturnValueProject(String fooProject);
    }
}