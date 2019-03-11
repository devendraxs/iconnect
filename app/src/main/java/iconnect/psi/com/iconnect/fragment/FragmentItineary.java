package iconnect.psi.com.iconnect.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.model.ItinearyDatabase;

public class FragmentItineary extends Fragment implements View.OnClickListener {
    private TextView project,tvDate;
    private Button itinearySave;
    ItinearyDatabase itinearyDatabase;
    private int day, month, year;
    private String date,getCurentDate;
    private TextView start,end,destination;
    private String[] listItems,startList,endList;
    private FragmentMyTravelRequest mActivity;
    boolean[] checkedItem;
    ArrayList<Integer> mUserItem=new ArrayList<>();
    ArrayList<Integer> startItem=new ArrayList<>();
    ArrayList<Integer> endItem=new ArrayList<>();
    private Spinner facilities;
    String[] name={" "," "," "," ",""};
    int[] images={R.drawable.fascilities,R.drawable.none,R.drawable.hotel,R.drawable.flight,R.drawable.flight_hotel};
    private CheckBox sameDayReturn;
    private List<ItinearyDatabase> passItinearyDatabase;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_itineary,container,false);

        passItinearyDatabase=new ArrayList<ItinearyDatabase>();

        listItems=getResources().getStringArray(R.array.poject_name);
        startList=getResources().getStringArray(R.array.source_station);
        endList=getResources().getStringArray(R.array.destination_station);
        checkedItem= new boolean[listItems.length];
        checkedItem=new boolean[startList.length];
        checkedItem=new boolean[endList.length];

        setViews(view);
        SpinnerAdapter spinnerAdapter=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(getActivity(),name,images);
        facilities.setAdapter(spinnerAdapter);
        facilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(mActivity, ""+name, Toast.LENGTH_SHORT).show();
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void setViews(View view) {
        project=view.findViewById(R.id.project);
        project.setOnClickListener(this);
        start=view.findViewById(R.id.start);
        start.setOnClickListener(this);
        end=view.findViewById(R.id.end);
        end.setOnClickListener(this);
        facilities=view.findViewById(R.id.facilities);
        tvDate=view.findViewById(R.id.tvDate);
        tvDate.setOnClickListener(this);
        itinearySave=view.findViewById(R.id.itinearySave);
        itinearySave.setOnClickListener(this);
    /*    facilities=view.findViewById(R.id.facilities);
        facilities.setOnClickListener(this);*/
        mActivity=(FragmentMyTravelRequest) getActivity();

        mActivity.newTravelRequest.setVisibility(View.GONE);


       }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.project:
                final AlertDialog.Builder mBuider=new AlertDialog.Builder(getActivity());
                mBuider.setTitle("Projets List");
                mBuider.setMultiChoiceItems(listItems, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked){
                            if (!mUserItem.contains(position)){
                                mUserItem.add(position);
                            }else {
                                mUserItem.remove(position);
                            }
                        }

                    }
                });
                mBuider.setCancelable(false);
                mBuider.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        String item="";
                        for (int i=0;i<mUserItem.size();i++){
                            item=item+listItems[mUserItem.get(i)];
                            if (i!=mUserItem.size()-1){
                                item=item+",";

                            }
                        }
                        project.setText(item);

                    }
                });
                mBuider.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                mBuider.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i=0;i<checkedItem.length;i++){
                            checkedItem[i]=false;
                            mUserItem.clear();
                            project.setText("");
                        }

                    }
                });
                AlertDialog mDialog=mBuider.create();
                mDialog.show();
                break;
            case R.id.start:
                final AlertDialog.Builder mBuider1=new AlertDialog.Builder(getActivity());
                mBuider1.setTitle("Source Station");

                mBuider1.setMultiChoiceItems(startList, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked){
                            if (!startItem.contains(position)){
                                startItem.add(position);
                            }else {
                                startItem.remove(position);
                            }
                        }

                    }
                });
                mBuider1.setCancelable(false);
                mBuider1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        String item="";
                        for (int i=0;i<startItem.size();i++){
                            item=item+startList[startItem.get(i)];
/*
                            if (i!=startItem.size()-1){
                                item=item+",";

                            }
*/
                        }
                        start.setText(item);

                    }
                });
                mBuider1.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                mBuider1.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i=0;i<checkedItem.length;i++){
                            checkedItem[i]=false;
                            startItem.clear();
                            start.setText("");
                        }
                    }
                });
                AlertDialog mDialog1=mBuider1.create();
                mDialog1.show();
                break;

            case R.id.end:
                final AlertDialog.Builder mBuider2=new AlertDialog.Builder(getActivity());
                mBuider2.setTitle("Destination Station");
                mBuider2.setMultiChoiceItems(endList, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked){
                            if (!endItem.contains(position)){
                                endItem.add(position);
                            }else {
                                endItem.remove(position);
                            }
                        }

                    }
                });
                mBuider2.setCancelable(false);
                mBuider2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        String item="";
                        for (int i=0;i<endItem.size();i++){
                            item=item+endList[endItem.get(i)];
/*
                            if (i!=endItem.size()-1){
                                item=item+",";
                            }
*/
                        }
                        end.setText(item);

                    }
                });
                mBuider2.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                mBuider2.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i=0;i<checkedItem.length;i++){
                            checkedItem[i]=false;
                            endItem.clear();
                            end.setText("");
                        }
                    }
                });
                AlertDialog mDialog2=mBuider2.create();
                mDialog2.show();
               break;
            case R.id.tvDate:
                dateDialog();
                break;
            case R.id.itinearySave:
                itinearyDatabase=new ItinearyDatabase();
               // itinearyDatabase.setSame_day_return(sameDayReturn.getText().toString().trim());
                itinearyDatabase.setStart_journey(start.getText().toString().trim());
                itinearyDatabase.setEnd_journey(end.getText().toString().trim());
//                itinearyDatabase.setDestination(destination.getText().toString().trim());
                itinearyDatabase.setDate(tvDate.getText().toString().trim());
                //itinearyDatabase.setFacilities(facilities.get);

                passItinearyDatabase.add(itinearyDatabase);

        }
    }

    private void dateDialog() {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tvDate.setVisibility(View.VISIBLE);
                tvDate.setText(i2 + "-" + (i1 + 1) + "-" + i);
                date=tvDate.getText().toString();
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
                getCurentDate=sdf.format(calendar.getTime());
            }
        };

        DatePickerDialog datePickerDialog=new DatePickerDialog(mActivity,listener,day,month,year);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();

    }
}
