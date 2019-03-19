package iconnect.psi.com.iconnect.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.activity.BaseActivity;
import iconnect.psi.com.iconnect.adapter.NewTravelRequestAdapter;
import iconnect.psi.com.iconnect.adapter.SpinnerAdapter;
import iconnect.psi.com.iconnect.model.ItinearyDatabase;


public class FragmentCreateNewTravelRequest extends BaseActivity implements View.OnClickListener {

    private static final int CAMERA=1;
    private static final int FILE=2;

    private TabLayout tabLayoutNewRequest;
    private static ViewPager pagerNewRequest;

    private NewTravelRequestAdapter newTravelRequestAdapter;
    private TextView itineary,purpose,advance,summary;
    private String emp_name,Designation,CostCenter;
    private Button next,nex1,next2;
    private ViewFlipper flipper;
    private RecyclerView mRecyclerView;
    private ImageView upload,camera;
    private Button goNextPurpose,goNextItineary,goNextAdvance,cancle,ok;
    private TextView project,tvDate,tvDate1;
    private LinearLayout llReturn,llPlusMinus;
    private Button itinearySave;
    ItinearyDatabase itinearyDatabase;
    private ImageView official,official2,official3,official4,official5,official6,official7;
    private int day, month, year;
    private String date;
    private String getCurentDate;
    private String date1;
    private TextView start,end,destination,end1;
    private String[] listItems,startList,endList;


    boolean[] checkedItem;
    ArrayList<Integer> mUserItem=new ArrayList<>();
    ArrayList<Integer> startItem=new ArrayList<>();
    ArrayList<Integer> endItem=new ArrayList<>();
    private Spinner facilities,facilities2,facilities3,facilities4,facilities5,facilities6,facilities7;
    private TextView tv1,tv2,tv3,tv4,tv_select_dest,tv_select_dest2,tv_select_dest3,tv_select_dest4,tv_select_dest5,tv_select_dest6,tv_select_dest7;
    private String[] name={" ", "Nothing", "Hotel", "Flight", "Flight & Hotel"};
    int[] images={R.drawable.facilities,R.drawable.new_none,R.drawable.new_hotel,R.drawable.new_flight,R.drawable.new_travel_fh};
    private CheckBox sameDayReturn;
    private List<ItinearyDatabase> passItinearyDatabase;
    private ImageView plus,plus2,plus3,plus4,plus5,plus6,plus7;
    private ImageView minus,minus2,minus3,minus4,minus5,minus6,minus7;
    private ImageView via,via2,via3,via4,via5,via6,via7;
    /*  private String emp_name,Designation,CostCenter;*/
    private EditText editTextDialogUserInput;
    private List<String> list_sou_des;
    private LinearLayout ll_1,ll_2,ll_3,ll_4,ll_5,ll_6,ll_7,ll_8,ll_9;
    private TextView tvDate_2,tvDate_3,tvDate_4,tvDate_5,tvDate_6,tvDate_7;
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_newtravel_request);

        /* pagerNewRequest =findViewById(R.id.pagerNewRequest);*/
        flipper=(ViewFlipper)findViewById(R.id.viewflipper) ;
        next=(Button)findViewById(R.id.goNextPurpose);
        nex1=(Button)findViewById(R.id.itinearySave) ;
        next2=(Button)findViewById(R.id.goNextAdvance);
        upload=findViewById(R.id.upload);
        camera=findViewById(R.id.camera);
        purpose=findViewById(R.id.purpose);

        mRecyclerView = findViewById(R.id.projectRecyclerview);
        passItinearyDatabase=new ArrayList<ItinearyDatabase>();

        listItems=getResources().getStringArray(R.array.poject_name);
        startList=getResources().getStringArray(R.array.source_station);
        endList=getResources().getStringArray(R.array.destination_station);
        checkedItem= new boolean[listItems.length];
        checkedItem=new boolean[startList.length];
        checkedItem=new boolean[endList.length];
        list_sou_des=new ArrayList<>();
        final  CheckBox checkboxSameday=findViewById(R.id.checkboxSameday);
        llPlusMinus=findViewById(R.id.llPlusMinus);
        tvDate1=findViewById(R.id.tvDate1);
        end1=findViewById(R.id.end1);
        tv1=findViewById(R.id.tv_start);
        tv2=findViewById(R.id.tv_mid1);
        tv3=findViewById(R.id.tv_mid2);
        tv4=findViewById(R.id.tv_dest);

        project=findViewById(R.id.project);
        /*  project.setOnClickListener(this);*/
        start=findViewById(R.id.start);
        /*  start.setOnClickListener(this);*/
        end=findViewById(R.id.end);
        /*   end.setOnClickListener(this);*/
        facilities=findViewById(R.id.facilities);
        facilities2=findViewById(R.id.facilities2);
        facilities3=findViewById(R.id.facilities3);
        facilities4=findViewById(R.id.facilities4);
        facilities5=findViewById(R.id.facilities5);
        facilities6=findViewById(R.id.facilities6);
        facilities7=findViewById(R.id.facilities7);
        tvDate=findViewById(R.id.tvDate);

        itinearySave=findViewById(R.id.itinearySave);
        /*  itinearySave.setOnClickListener(this);*/
        official=findViewById(R.id.official);
          official.setOnClickListener(this);

        official2=findViewById(R.id.official2);
        /* official2.setOnClickListener(this);*/
        official3=findViewById(R.id.official3);
        /*   official3.setOnClickListener(this);*/
        official4=findViewById(R.id.official4);
        /*  official4.setOnClickListener(this);*/
        official5=findViewById(R.id.official5);
        /*  official5.setOnClickListener(this);*/
        official6=findViewById(R.id.official6);
        /*  official6.setOnClickListener(this);*/
        official7=findViewById(R.id.official7);
        /*  official7.setOnClickListener(this);*/

        tv_select_dest3=findViewById(R.id.des_selection3);
        tv_select_dest4=findViewById(R.id.des_selection4);
        tv_select_dest5=findViewById(R.id.des_selection5);
        tv_select_dest6=findViewById(R.id.des_selection6);
        tv_select_dest7=findViewById(R.id.des_selection7);

        plus=findViewById(R.id.plus);
          plus.setOnClickListener(this);

        plus2=findViewById(R.id.plus2);
        plus2.setOnClickListener(this);

        plus3=findViewById(R.id.plus3);
         plus3.setOnClickListener(this);

        plus4=findViewById(R.id.plus4);
            plus4.setOnClickListener(this);

        plus5=findViewById(R.id.plus5);
         plus5.setOnClickListener(this);

        plus6=findViewById(R.id.plus6);
          plus6.setOnClickListener(this);

        plus7=findViewById(R.id.plus7);
         plus7.setOnClickListener(this);
        minus=findViewById(R.id.minus);
           minus.setOnClickListener(this);
        minus2=findViewById(R.id.minus2);
           minus2.setOnClickListener(this);
        minus3=findViewById(R.id.minus3);
        minus3.setOnClickListener(this);
        minus4=findViewById(R.id.minus4);
         minus4.setOnClickListener(this);
        minus5=findViewById(R.id.minus5);
          minus5.setOnClickListener(this);
        minus6=findViewById(R.id.minus6);
            minus6.setOnClickListener(this);
        minus7=findViewById(R.id.minus7);
           minus7.setOnClickListener(this);
        destination=findViewById(R.id.destination);
         destination.setOnClickListener(this);
        editTextDialogUserInput=findViewById(R.id.editTextDialogUserInput);
        llReturn=findViewById(R.id.llReturn);

        ll_1=findViewById(R.id.ll_1);
        ll_2=findViewById(R.id.ll_2);
        ll_3=findViewById(R.id.ll_3);
        ll_4=findViewById(R.id.ll_4);
        ll_5=findViewById(R.id.ll_5);
        ll_6=findViewById(R.id.ll_6);
        ll_7=findViewById(R.id.ll_7);
        ll_8=findViewById(R.id.ll_8);
        ll_9=findViewById(R.id.ll_9);

        tvDate_2=findViewById(R.id.tvDate2);
        tvDate_3=findViewById(R.id.tvDate3);
        tvDate_4=findViewById(R.id.tvDate4);
        tvDate_5=findViewById(R.id.tvDate5);
        tvDate_6=findViewById(R.id.tvDate6);
        tvDate_7=findViewById(R.id.tvDate7);

        tv_select_dest2=findViewById(R.id.des_selection2);
        tv_select_dest3=findViewById(R.id.des_selection3);
        tv_select_dest4=findViewById(R.id.des_selection4);
        tv_select_dest5=findViewById(R.id.des_selection5);
        tv_select_dest6=findViewById(R.id.des_selection6);
        tv_select_dest7=findViewById(R.id.des_selection7);

        destination=findViewById(R.id.destination);
        cancle=findViewById(R.id.cancle);
        ok=findViewById(R.id.ok);
        /*ok.setOnClickListener();*/
        /*   mRecyclerView.setLayoutManager(new LinearLayoutManager(this));*/


        //mActivity= (FragmentMyTravelRequest) getActivity();

        Intent intent=getIntent();
        emp_name=intent.getStringExtra("emp_name");
        Designation=intent.getStringExtra("Designation");
        CostCenter=intent.getStringExtra("CostCenter");

        itineary=findViewById(R.id.itineary);
        purpose=findViewById(R.id.purpose);
        advance=findViewById(R.id.advance);
        summary=findViewById(R.id.summary);

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
        SpinnerAdapter spinnerAdapter=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
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
        SpinnerAdapter spinnerAdapter2=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
        facilities3.setAdapter(spinnerAdapter2);
        facilities3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SpinnerAdapter spinnerAdapter3=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
        facilities4.setAdapter(spinnerAdapter3);
        facilities4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SpinnerAdapter spinnerAdapter4=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
        facilities5.setAdapter(spinnerAdapter4);
        facilities5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SpinnerAdapter spinnerAdapter5=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
        facilities6.setAdapter(spinnerAdapter5);
        facilities6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SpinnerAdapter spinnerAdapter6=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
        facilities7.setAdapter(spinnerAdapter6);
        facilities7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] image=new String[]{"Camera","Gallery"};
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(FragmentCreateNewTravelRequest.this,android.R.layout.select_dialog_item,image);
                AlertDialog.Builder builder=new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                builder.setTitle("Select Camera or Gallery");
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int media) {
                        if (media==0)
                        {
                            //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            try
                            {
                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            dialog.cancel();
                        }else if (media==1){
                            Intent intent=new Intent();
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent,"media"),FILE);
                        }
                    }
                });
                final AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=new File(Environment.getExternalStorageDirectory(),"Agenda.pdf");
                Uri path=Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "*/pdf*");/**/

/*
                try {
                    startActivity(pdfOpenintent);
                }
                catch (ActivityNotFoundException e) {

                }
*/
                File file1= new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ "filename");
                Intent pdfViewIntent = new Intent(Intent.ACTION_VIEW);
                pdfViewIntent.setDataAndType(Uri.fromFile(file),"application/pdf");
                pdfViewIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(pdfViewIntent, "Open File");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }


            }
        });
        minus=findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_1.setVisibility(view.GONE);
            }
        });
        minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_2.setVisibility(view.GONE);
            }
        });
        minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_3.setVisibility(view.GONE);
            }
        });
        minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_4.setVisibility(view.GONE);
            }
        });
        minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_5.setVisibility(view.GONE);
            }
        });
        minus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_6.setVisibility(view.GONE);
            }
        });

        minus=findViewById(R.id.minus);
        /*   minus.setOnClickListener(this);*/
        via=findViewById(R.id.via);
        /*   via.setOnClickListener(this);*/
        via2=findViewById(R.id.via2);
        /* via2.setOnClickListener(this);*/

        via3=findViewById(R.id.via3);


        via4=findViewById(R.id.via4);

        via5=findViewById(R.id.via5);


        via6=findViewById(R.id.via6);


        via7=findViewById(R.id.via7);



    /*    facilities=view.findViewById(R.id.facilities);
        facilities.setOnClickListener(this);*/
        /*   mActivity=(FragmentCreateNewTravelRequest) getActivity();*/

        /*  mActivity.newTravelRequest.setVisibility(View.GONE);*/

        // start=view.findViewById(R.id.start);
        end=findViewById(R.id.end);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentProject dialog = FragmentProject.newInstance();

                dialog.show(getSupportFragmentManager(), "MyDialogFragment");

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsView = li.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
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

            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsView1 = lii.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
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
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater liii = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsView2 = liii.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
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
            }
        });
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateDialog();

                date=tvDate.getText().toString();
                tvDate1.setText(date);
            }
        });
        official.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official.setImageResource(R.drawable.official);
                    flag=true;
                }
            }
        });
        official2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official2.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official2.setImageResource(R.drawable.official);
                    flag=true;
                }
            }
        });
        official3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official3.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official3.setImageResource(R.drawable.official);
                    flag=true;
                }
            }
        });
        official4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official4.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official4.setImageResource(R.drawable.official);
                    flag=true;
                }
            }
        });
        official5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official5.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official5.setImageResource(R.drawable.official);
                    flag=true;
                }


            }
        });
        official6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official6.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official6.setImageResource(R.drawable.official);
                    flag=true;
                }
            }
        });
        official7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    official7.setImageResource(R.drawable.personal);
                    flag=false;
                }else {
                    official7.setImageResource(R.drawable.official);
                    flag=true;
                }
            }
        });
        itinearySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itinearyDatabase=new ItinearyDatabase();
                // itinearyDatabase.setSame_day_return(sameDayReturn.getText().toString().trim());
                itinearyDatabase.setStart_journey(start.getText().toString().trim());
                itinearyDatabase.setEnd_journey(end.getText().toString().trim());
//                itinearyDatabase.setDestination(destination.getText().toString().trim());
                itinearyDatabase.setDate(tvDate.getText().toString().trim());
                //itinearyDatabase.setFacilities(facilities.get);
                passItinearyDatabase.add(itinearyDatabase);
            }
        });
        via.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii2 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww = lii2.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
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
            }
        });
        via2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii3 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww2 = lii3.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr2 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                alertDialogBuilderr2.setView(promptsVieww2);
                final EditText userInputt2 =  promptsVieww2.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr2.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt2.getText().toString().trim());
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

                AlertDialog alertDialog112=alertDialogBuilderr2.create();
                alertDialog112.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());
            }
        });
        via3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii4 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww3 = lii4.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr3 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                alertDialogBuilderr3.setView(promptsVieww3);
                final EditText userInputt3 =  promptsVieww3.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr3.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt3.getText().toString().trim());
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
                AlertDialog alertDialog113=alertDialogBuilderr3.create();
                alertDialog113.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());
            }
        });
        via4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii5 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww4 = lii5.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr4 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                alertDialogBuilderr4.setView(promptsVieww4);
                final EditText userInputt4 =  promptsVieww4.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr4.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt4.getText().toString().trim());
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

                AlertDialog alertDialog114=alertDialogBuilderr4.create();
                alertDialog114.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());
            }
        });
        via5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii6 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww5 = lii6.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr5 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                alertDialogBuilderr5.setView(promptsVieww5);
                final EditText userInputt5 =  promptsVieww5.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr5.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt5.getText().toString().trim());
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

                AlertDialog alertDialog115=alertDialogBuilderr5.create();
                alertDialog115.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());
            }
        });
        via6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii7 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww6 = lii7.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr6 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                alertDialogBuilderr6.setView(promptsVieww6);
                final EditText userInputt6 =  promptsVieww6.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr6.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt6.getText().toString().trim());
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

                AlertDialog alertDialog116=alertDialogBuilderr6.create();
                alertDialog116.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());
            }
        });
        via7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii8 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
                View promptsVieww7 = lii8.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr7 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
                alertDialogBuilderr7.setView(promptsVieww7);
                final EditText userInputt7 =  promptsVieww7.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr7.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {

                        list_sou_des.add(""+userInputt7.getText().toString().trim());
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

                AlertDialog alertDialog117=alertDialogBuilderr7.create();
                alertDialog117.show();

                tv1.setText(""+start.getText().toString().trim());
                tv4.setText(""+end.getText().toString().trim());

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvDate==null){
                    try {
                        String date=tvDate.getText().toString().trim();
                        String[] item=date.split("-");
                        String d1=item[0];
                        String d2=item[1];
                        String d3=item[2];

                        int d = Integer.parseInt(d1);
                        int m = Integer.parseInt(d2);
                        int y = Integer.parseInt(d3);
                        // tvDate_2.setText(""+dt);
                        tvDate_2.setText(d+1+"-"+m+"-"+y);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                   // Toast.makeText(FragmentCreateNewTravelRequest.this, "Please enter date", Toast.LENGTH_SHORT).show();
                }
                ll_1.setVisibility(View.VISIBLE);
            }
        });

        tv_select_dest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDestination(tv_select_dest2);
            }
        });
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDate_3.setText((tvDate_2).getText().toString().trim());
                ll_2.setVisibility(View.VISIBLE);

            }
        });
        tv_select_dest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDestination(tv_select_dest3);
            }
        });
       plus3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tvDate_4.setText(tvDate_3.getText().toString().trim());
               ll_3.setVisibility(view.VISIBLE);
           }
       });
       tv_select_dest4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest4);
           }
       });
       plus4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tvDate_5.setText(tvDate_4.getText().toString().trim());
               ll_4.setVisibility(view.VISIBLE);
           }
       });
       tv_select_dest5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest5);
           }
       });
       plus5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tvDate_6.setText(tvDate_5.getText().toString().trim());
               ll_5.setVisibility(view.VISIBLE);
           }
       });
       tv_select_dest6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest5);
           }
       });
       plus6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tvDate_7.setText(tvDate_6.getText().toString().trim());
               ll_6.setVisibility(view.VISIBLE);
           }
       });
       tv_select_dest7.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest6);
           }
       });
        getProjectData();

        // tabLayoutNewRequest = (TabLayout) view.findViewById(R.id.tabLayoutNewRequest);
        /*  pagerNewRequest=(ViewPager)findViewById(R.id.pagerNewRequest);*/

       /* tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Itineary"));
        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Purpose"));
        tabLayoutNewRequest.addTab(tabLayoutNewRequest.newTab().setText("Advance"));*/


//        tabLayoutNewRequest.setTabGravity(TabLayout.GRAVITY_FILL);

    /*
            tabLayoutNewRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pagerNewRequest.setAdapter(newTravelRequestAdapter);
                }
            });
    */
        /*    newTravelRequestAdapter=new  NewTravelRequestAdapter(getSupportFragmentManager());
            pagerNewRequest.setAdapter(newTravelRequestAdapter);*/
        final int positition=  flipper.getDisplayedChild();
        onChangeTab(positition);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
                onChangeTab(1);
            }
        });
        nex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
                onChangeTab(2);

            }
        });
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
                onChangeTab(3);

            }
        });




/*
            purpose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pagerNewRequest.setCurrentItem(0,false);

                }
            });
*/
/*
            itineary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pagerNewRequest.setCurrentItem(1,false);

                }
            });
*/

/*
            advance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pagerNewRequest.setCurrentItem(2,false);

                }
            });
*/
/*
            expence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pagerNewRequest.setCurrentItem(3,false);


                }
            });
*/


           /* pagerNewRequest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int ositionOffsetpixels) {


                }

                @Override
                public void onPageSelected(int position) {
                    onChangeTab(position);

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });*/
        /*new setAdapterTask().execute();*/


        // return view;
    }

    private void dialogDestination(final TextView tv_select_dest2) {
        LayoutInflater liidest2 = LayoutInflater.from(FragmentCreateNewTravelRequest.this);
        View promptsViewdest2 = liidest2.inflate(R.layout.edittext_dialog, null);
        AlertDialog.Builder alertDialogBuilderdest2 = new AlertDialog.Builder(FragmentCreateNewTravelRequest.this);
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

        DatePickerDialog datePickerDialog=new DatePickerDialog(FragmentCreateNewTravelRequest.this,listener,day,month,year);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();

    }



    private void getProjectData() {


    }
    private void onChangeTab(int position) {
        if (position==0){
            purpose.setTextColor(Color.RED);
            itineary.setTextColor(Color.WHITE);
            advance.setTextColor(Color.WHITE);
            summary.setTextColor(Color.WHITE);

        }
        if (position==1){
            itineary.setTextColor(Color.RED);
            purpose.setTextColor(Color.WHITE);
            advance.setTextColor(Color.WHITE);
            summary.setTextColor(Color.WHITE);

        }
        if (position==2){

            advance.setTextColor(Color.RED);
            itineary.setTextColor(Color.WHITE);
            purpose.setTextColor(Color.WHITE);
            summary.setTextColor(Color.WHITE);
        }
        if (position==3){
            summary.setTextColor(Color.RED);
            itineary.setTextColor(Color.WHITE);
            advance.setTextColor(Color.WHITE);
            summary.setTextColor(Color.WHITE);
        }
    }
    @Override
    public void onClick(View view) {
    }

    class setAdapterTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pagerNewRequest.setAdapter(newTravelRequestAdapter);
        }
    }
}
