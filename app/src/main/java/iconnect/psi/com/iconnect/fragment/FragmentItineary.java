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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView official;
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
   private String[] name={" ", "Nothing", "Hotel", "Flight", "Flight and Hotel"};
    int[] images={R.drawable.fascilities,R.drawable.none,R.drawable.hotel,R.drawable.flight,R.drawable.flight_hotel};
    private CheckBox sameDayReturn;
    private List<ItinearyDatabase> passItinearyDatabase;
    private ImageView plus,minus,via;
    private String emp_name,Designation,CostCenter;
    private EditText editTextDialogUserInput;



   // private EditText result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_itineary,container,false);

        /*Bundle bundle=getArguments();
        emp_name=bundle.getString("emp_name");
        Designation=bundle.getString("Designation");
        CostCenter=bundle.getString("CostCenter");*/


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

        return view;    }

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
        official=view.findViewById(R.id.official);
        official.setOnClickListener(this);
        plus=view.findViewById(R.id.plus);
        plus.setOnClickListener(this);
        editTextDialogUserInput=view.findViewById(R.id.editTextDialogUserInput);
       // button =view.findViewById(R.id.editTextDialogUserInput);
      //  result =view.findViewById(R.id.editTextDialogUserInput);

        minus=view.findViewById(R.id.minus);
        minus.setOnClickListener(this);

        via=view.findViewById(R.id.via);
        via.setOnClickListener(this);
    /*    facilities=view.findViewById(R.id.facilities);
        facilities.setOnClickListener(this);*/
        mActivity=(FragmentMyTravelRequest) getActivity();

        mActivity.newTravelRequest.setVisibility(View.GONE);

       // start=view.findViewById(R.id.start);
        end=view.findViewById(R.id.end);
      /*  start.setText(CostCenter);
        end.setText(CostCenter);*/
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.project:
                //Toast.makeText(mActivity, "hkfkl", Toast.LENGTH_SHORT).show();

                FragmentProject dialog = FragmentProject.newInstance();

                dialog.show(mActivity.getSupportFragmentManager(), "MyDialogFragment");

             /* /  *//*FragmentProject dialog = new FragmentProject(getContext());
                //dialog.setTitle("Select project");
                dialog.setContentView(R.layout.dialog_layout);*//*
                RecyclerView recyclerView=view.findViewById(R.id.projectRecyclerview);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                dialog.show()*/;
             // openDialog(view);

              /*  final AlertDialog.Builder mBuider=new AlertDialog.Builder(getActivity());
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
                mDialog.show();*/
                break;
             case R.id.start:
                /*final AlertDialog.Builder mBuider1=new AlertDialog.Builder(getActivity());
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
*//*
                            if (i!=startItem.size()-1){
                                item=item+",";

                            }
*//*
                        }
                        start.setText("Start: "+item);

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
                mDialog1.show();*/

                 LayoutInflater li = LayoutInflater.from(mActivity);
                 View promptsView = li.inflate(R.layout.edittext_dialog, null);

                 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
                 alertDialogBuilder.setView(promptsView);

                 final EditText userInput = (EditText) promptsView
                         .findViewById(R.id.editTextDialogUserInput);

                 alertDialogBuilder
                         .setCancelable(false)
                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int id) {
                                 start.setText("Start: "+userInput.getText().toString().trim());

                             }
                         }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface diialog, int i) {
                         diialog.cancel();
                     }
                 });

                 AlertDialog alertDialog=alertDialogBuilder.create();
                 alertDialog.show();


                 break;

            case R.id.end:
                LayoutInflater lii = LayoutInflater.from(mActivity);
                View promptsView1 = lii.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(mActivity);
                alertDialogBuilder1.setView(promptsView1);

                final EditText userInput1 = (EditText) promptsView1
                        .findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder1
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                end.setText("End: "+userInput1.getText().toString().trim());

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface diialog, int i) {
                        diialog.cancel();
                    }
                });

                AlertDialog alertDialog1=alertDialogBuilder1.create();
                alertDialog1.show();




                break;
            case R.id.tvDate:
                dateDialog();
                break;
            case R.id.official:
                official.setImageResource(R.drawable.personal);
                Toast.makeText(mActivity, "This travel require special approval" +
                        "", Toast.LENGTH_SHORT).show();
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
/*
    public void openDialog(View view){
        final String[] items = {"Apple", "Banana", "Orange", "Grapes"};
        final ArrayList<Integer> selectedList = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("This is list choice dialog box");
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    selectedList.add(which);
                }else if(selectedList.contains(which)){
                    selectedList.remove(which);
                }

            }
        }); builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<String> selectedStrings = new ArrayList<>();

                for (int j = 0; j < selectedList.size(); j++) {
                    selectedStrings.add(items[selectedList.get(j)]);
                }

                Toast.makeText(getActivity(), "Items selected are: " + Arrays.toString(selectedStrings.toArray()), Toast.LENGTH_SHORT).show();


            }
        });

        builder.show();

    }
*/




       /* final ArrayList<Integer> alreadySelectedCountries = new ArrayList<>();
        alreadySelectedCountries.add(1);
        alreadySelectedCountries.add(3);
        alreadySelectedCountries.add(4);
        alreadySelectedCountries.add(7);

        //List of Countries with Name and Id
        ArrayList<MultiSelectModel> listOfCountries= new ArrayList<>();
        listOfCountries.add(new MultiSelectModel(1,"INDIA"));
        listOfCountries.add(new MultiSelectModel(2,"USA"));
        listOfCountries.add(new MultiSelectModel(3,"UK"));
        listOfCountries.add(new MultiSelectModel(4,"UAE"));
        listOfCountries.add(new MultiSelectModel(5,"JAPAN"));
        listOfCountries.add(new MultiSelectModel(6,"SINGAPORE"));
        listOfCountries.add(new MultiSelectModel(7,"CHINA"));
        listOfCountries.add(new MultiSelectModel(8,"RUSSIA"));
        listOfCountries.add(new MultiSelectModel(9,"BANGLADESH"));
        listOfCountries.add(new MultiSelectModel(10,"BELGIUM"));

        MultiSelectDialog multiSelectDialog = new MultiSelectDialog()
                .title(getResources().getString(R.string.multi_select_dialog_title)) //setting title for dialog
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .setMinSelectionLimit(1) //you can set minimum checkbox selection limit (Optional)
                .setMaxSelectionLimit(listOfCountries.size()) //you can set maximum checkbox selection limit (Optional)
                .preSelectIDsList(alreadySelectedCountries) //List of ids that you need to be selected
                .multiSelectList(listOfCountries) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        for (int i = 0; i < selectedIds.size(); i++) {
                            Toast.makeText(getActivity(), "Selected Ids : " + selectedIds.get(i) + "\n" +
                                    "Selected Names : " + selectedNames.get(i) + "\n" +
                                    "DataString : " + dataString, Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancel() {
                        Log.d("TAG","Dialog cancelled");
                    }


                });

        multiSelectDialog.show(getFragmentManager(), "multiSelectDialog");*/
    }


