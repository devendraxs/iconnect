package iconnect.psi.com.iconnect.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private TextView project,tvDate,tvDate1;
    private LinearLayout llReturn,llPlusMinus;
    private Button itinearySave;
    ItinearyDatabase itinearyDatabase;
    private ImageView official;
    private int day, month, year;
    private String date;
    private String getCurentDate;
    private String date1;
    private TextView start,end,destination,end1;
    private String[] listItems,startList,endList;
    private FragmentMyTravelRequest mActivity;
    boolean[] checkedItem;
    ArrayList<Integer> mUserItem=new ArrayList<>();
    ArrayList<Integer> startItem=new ArrayList<>();
    ArrayList<Integer> endItem=new ArrayList<>();
    private Spinner facilities;
    private TextView tv1,tv2,tv3,tv4,tv_select_dest,tv_select_dest2,tv_select_dest3,tv_select_dest4,tv_select_dest5,tv_select_dest6,tv_select_dest7;
   private String[] name={" ", "Nothing", "Hotel", "Flight", "Flight and Hotel"};
    int[] images={R.drawable.facilities,R.drawable.new_none,R.drawable.new_hotel,R.drawable.new_flight,R.drawable.new_travel_fh};
    private CheckBox sameDayReturn;
    private List<ItinearyDatabase> passItinearyDatabase;
    private ImageView plus,minus,via,plus2,plus3,plus4,plus5,plus6,plus7,plus8,plus9,plus10;
    private String emp_name,Designation,CostCenter;
    private EditText editTextDialogUserInput;
    private List<String> list_sou_des;
    private LinearLayout ll_1,ll_2,ll_3,ll_4,ll_5,ll_6,ll_7,ll_8,ll_9;
    private TextView tvDate_2,tvDate_3,tvDate_4,tvDate_5,tvDate_6,tvDate_7;

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
        list_sou_des=new ArrayList<>();
      //  list_sou_des.clear();

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

        final  CheckBox checkboxSameday=view.findViewById(R.id.checkboxSameday);
        llPlusMinus=view.findViewById(R.id.llPlusMinus);
        tvDate1=view.findViewById(R.id.tvDate1);
        end1=view.findViewById(R.id.end1);
        tv1=view.findViewById(R.id.tv_start);
        tv2=view.findViewById(R.id.tv_mid1);
        tv3=view.findViewById(R.id.tv_mid2);
        tv4=view.findViewById(R.id.tv_dest);

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
        plus2=view.findViewById(R.id.plus2);
        plus2.setOnClickListener(this);
        plus3=view.findViewById(R.id.plus3);
        plus3.setOnClickListener(this);
        plus4=view.findViewById(R.id.plus4);
        plus4.setOnClickListener(this);
        plus5=view.findViewById(R.id.plus5);
        plus5.setOnClickListener(this);
        plus6=view.findViewById(R.id.plus5);
        plus6.setOnClickListener(this);
        plus7=view.findViewById(R.id.plus7);
        plus7.setOnClickListener(this);

        plus8=view.findViewById(R.id.plus8);
        plus8.setOnClickListener(this);

        plus9=view.findViewById(R.id.plus9);
        plus9.setOnClickListener(this);

        plus10=view.findViewById(R.id.plus10);
        plus10.setOnClickListener(this);

        destination=view.findViewById(R.id.destination);
        destination.setOnClickListener(this);
        editTextDialogUserInput=view.findViewById(R.id.editTextDialogUserInput);
        llReturn=view.findViewById(R.id.llReturn);

        ll_1=view.findViewById(R.id.ll_1);
        ll_2=view.findViewById(R.id.ll_2);
        ll_3=view.findViewById(R.id.ll_3);
        ll_4=view.findViewById(R.id.ll_4);
        ll_5=view.findViewById(R.id.ll_5);
        ll_6=view.findViewById(R.id.ll_6);
        ll_7=view.findViewById(R.id.ll_7);
        ll_8=view.findViewById(R.id.ll_8);
        ll_9=view.findViewById(R.id.ll_9);

        tvDate_2=view.findViewById(R.id.tvDate2);
        tvDate_3=view.findViewById(R.id.tvDate3);
        tvDate_4=view.findViewById(R.id.tvDate4);
        tvDate_5=view.findViewById(R.id.tvDate5);
        tvDate_6=view.findViewById(R.id.tvDate6);
        tvDate_7=view.findViewById(R.id.tvDate7);

        tv_select_dest2=view.findViewById(R.id.des_selection2);
        tv_select_dest3=view.findViewById(R.id.des_selection3);
        tv_select_dest4=view.findViewById(R.id.des_selection4);
        tv_select_dest5=view.findViewById(R.id.des_selection5);
        tv_select_dest6=view.findViewById(R.id.des_selection6);
        tv_select_dest7=view.findViewById(R.id.des_selection7);

        tvDate_2.setOnClickListener(this);
        tvDate_3.setOnClickListener(this);
        tvDate_4.setOnClickListener(this);
        tvDate_5.setOnClickListener(this);
        tvDate_6.setOnClickListener(this);
        tvDate_7.setOnClickListener(this);

        tv_select_dest2.setOnClickListener(this);
        tv_select_dest3.setOnClickListener(this);
        tv_select_dest4.setOnClickListener(this);
        tv_select_dest5.setOnClickListener(this);
        tv_select_dest6.setOnClickListener(this);
        tv_select_dest7.setOnClickListener(this);


        checkboxSameday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()){
                    llPlusMinus.setEnabled(false);
                }else {
                    llPlusMinus.setEnabled(true);
                }
            }
        });
        checkboxSameday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()){
                    llReturn.setVisibility(view.VISIBLE);
                    end1.setText(start.getText().toString().trim());
                }else {
                    llReturn.setVisibility(view.GONE);
                }
            }
        });
      /*  checkboxSameday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()){
                    llPlusMinus.setVisibility(view.GONE);
                }else {
                    llPlusMinus.setVisibility(view.VISIBLE);
                }
            }
        });*/
/*
        checkboxSameday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()){
                    llReturn.setVisibility(view.VISIBLE);
                }else {
                    llReturn.setVisibility(view.GONE);
                }

            }
        });
*/
        ;
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
            case R.id.destination:
                LayoutInflater liii = LayoutInflater.from(mActivity);
                View promptsView2 = liii.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(mActivity);
                alertDialogBuilder2.setView(promptsView2);

                final EditText userInput2 = (EditText) promptsView2
                        .findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder2
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                destination.setText(" "+userInput2.getText().toString().trim());

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface diialog, int i) {
                        diialog.cancel();
                    }
                });

                AlertDialog alertDialog2=alertDialogBuilder2.create();
                alertDialog2.show();
                break;
            case R.id.tvDate:
                dateDialog();

                date=tvDate.getText().toString();
                 tvDate1.setText(date);
                break;

        /*    case R.id.tvDate1:
                date1=date;
                break;*/

            case R.id.official:
                official.setImageResource(R.drawable.personal);
                Toast.makeText(mActivity, "This travel require special approval" +
                        "", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.via:
                LayoutInflater lii2 = LayoutInflater.from(mActivity);
                View promptsVieww = lii2.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr = new AlertDialog.Builder(mActivity);
                alertDialogBuilderr.setView(promptsVieww);
                final EditText userInputt =  promptsVieww.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt.getText().toString().trim());
                        if(list_sou_des.size()==1){
                            tv2.setText(""+list_sou_des.get(0));
                        }else{
                            tv2.setText(""+list_sou_des.get(0));
                            tv3.setText(""+list_sou_des.get(1));
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface diialog, int i) {
                        diialog.cancel();
                    }
                });

                AlertDialog alertDialog11=alertDialogBuilderr.create();
                alertDialog11.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());
                // Log.e("List pro list",""+list_sou_des.size());
                break;



                // plus 1
            case R.id.plus:
//                travelCountingPojo.setDate(tvDate.getText().toString().trim());
//                travelCountingPojo.setDestination(tv_select_dest.getText().toString().trim());
//                travelCountingPojo.setSpValue(facilities.getSelectedItem().toString().trim());
//                travelCountingPojos.add(travelCountingPojo);
//                Log.e("Travel Pojo List",""+travelCountingPojos.size());
                ll_1.setVisibility(View.VISIBLE);
                break;

            case R.id.tvDate2:
                dateDialog2();
                break;
            case R.id.des_selection2:
                dialogDestination(tv_select_dest2);
                break;

            // plus 2

            case R.id.plus2:
                ll_2.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate3:
                dateDialog2();
                break;
            case R.id.des_selection3:
                dialogDestination(tv_select_dest3);
                break;


            // plus 3
            case R.id.plus3:
                ll_3.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate4:
                dateDialog2();
            break;
            case R.id.des_selection4:
                dialogDestination(tv_select_dest4);

                // plus 4

            case R.id.plus4:
                ll_4.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate5:
                dateDialog2();
                break;
            case R.id.des_selection5:
                dialogDestination(tv_select_dest5);

                // plus 5

            case R.id.plus5:
                ll_5.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate6:
                dateDialog2();
                break;
            case R.id.des_selection6:
                dialogDestination(tv_select_dest5);

                // / plus 6

            case R.id.plus6:
                ll_6.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate7:
                dateDialog2();
                break;
            case R.id.des_selection7:
                dialogDestination(tv_select_dest5);
                break;

        // plus 7
            case R.id.plus7:
                ll_7.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate8:
                dateDialog2();
                break;
            case R.id.des_selection8:
                dialogDestination(tv_select_dest6);
                break;

                // plus 8

            case R.id.plus8:
                ll_8.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate9:
                dateDialog2();
                break;
            case R.id.des_selection9:
                dialogDestination(tv_select_dest7);
                break;

            case R.id.plus9:
                ll_9.setVisibility(view.VISIBLE);
                break;
            case R.id.tvDate10:
                dateDialog2();
                break;
            case R.id.des_selection10:
                dialogDestination(tv_select_dest7);
                break;

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

    @Override
    public void onStart() {
        super.onStart();
        Bundle b=getArguments();
        if(b !=null) {
            int sel_pro = b.getInt("select_project");
            Log.e("Selected  Itineary ", "=================" + sel_pro);
            if(sel_pro == 1) {
                project.setText("Single");
            }else{
                project.setText("Mulptiple");
            }
        }
    }

    public void dialogDestination(TextView dest){

        LayoutInflater liidest2 = LayoutInflater.from(mActivity);
        View promptsViewdest2 = liidest2.inflate(R.layout.edittext_dialog, null);
        AlertDialog.Builder alertDialogBuilderdest2 = new AlertDialog.Builder(mActivity);
        alertDialogBuilderdest2.setView(promptsViewdest2);
        final EditText userInputdest2 =  promptsViewdest2.findViewById(R.id.editTextDialogUserInput);
        alertDialogBuilderdest2.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id)
            {
                tv_select_dest2.setText(userInputdest2.getText().toString().trim());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diialog, int i) {
                diialog.cancel();
            }
        });
        AlertDialog alertDialog1dest2=alertDialogBuilderdest2.create();
        alertDialog1dest2.show();

    }

    private void dateDialog2( )
    {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
            {
                FragmentItineary.this.tvDate_2.setVisibility(View.VISIBLE);
                FragmentItineary.this.tvDate_2.setText(i2 + "-" + (i1 + 1) + "-" + i);
                date= FragmentItineary.this.tvDate_2.getText().toString();
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


