package iconnect.psi.com.iconnect.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.CityAdapter;
import iconnect.psi.com.iconnect.adapter.DateParadimRecyclerview;
import iconnect.psi.com.iconnect.adapter.SpinnerAdapter;
import iconnect.psi.com.iconnect.fragment.FragmentCity;
import iconnect.psi.com.iconnect.fragment.FragmentProject;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.CityResponse;
import iconnect.psi.com.iconnect.model.ItinearyDatabase;
import iconnect.psi.com.iconnect.model.ItinearyResponse;
import iconnect.psi.com.iconnect.model.MyTravelRequestBean;
import iconnect.psi.com.iconnect.model.Perdiam;
import iconnect.psi.com.iconnect.model.PurposeResponse;
import iconnect.psi.com.iconnect.model.TravelBooking;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import iconnect.psi.com.iconnect.utils.Utility;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateNewTravelRequestActivity extends BaseActivity implements View.OnClickListener,FragmentCity.MyDialogFragmentListener,FragmentProject.MyDialogFragmentListenerProject{
    private ImageView viaFlight,viaOfficial;
    LinearLayout llOtherAdv1,llOtherAdv2,llOtherAdv3;
    String facility1;
    DateParadimRecyclerview dateParadimRecyclerview;
    String breakfast,lunch,dinner,none,incedental,estd_perdiam;

    File destinationImage;
   public String data;
    String bookRes;
    String path = "",sameDayCheckStatus="0";
    private ArrayList<String> textviewdata=new ArrayList<>();
    private String pur,itry;
    Bitmap thumbnail;
    private  List<String> bitmaplist=new ArrayList<>();

    private TextView tvDateAdvance;
    private String advanceDate;

    private TextView  tv_flight,tv_personal,tv_personal1,tv_flight1,tv_flight2,tv_personal2;
    private CreateNewTravelRequestActivity context;
    final static  int SELECT_FILE=1;
    final static int REQUEST_CAMERA=2;
    final static int REQUEST_CAMERA3=3;
    final static int REQUEST_CAMERA4=4;
    private Dialog mDialog;
    CheckBox checkboxSameday;
    private LinearLayout startEnd;
    private EditText ed_purpose;
    private String encodedImage=null;
    private Bitmap bitmap;
    private SeekBar seekbar;
    private TextView advanceAmountPer,totalAmount;
    int stepSize=5;
    private TextView estmPdm;
    private   int advAmount;
    private CheckBox check1,check2,check3,check4,check5;
    private int amount=1200;
    private int  calCheck2,calcheck3,calcheck4,calcheck5;
    private volatile  int checkBoxAmount;
    private static final int CAMERA=1;
    private static final int FILE=2;
    private TextView itineary,purpose,advance,summary;
    private String emp_name,Designation,CostCenter;
    private Button next,next1,next2,next3,itinearyPrevious,advancePrevious,summaryPrevious;
    private ViewFlipper flipper;
    private RecyclerView mRecyclerView,mCityRecyclerView;
    private ImageView upload,camera;
    private TextView project,tvDate,tvDate1;
    private LinearLayout llReturn,llPlusMinus;
    private Button itinearySave;
    ItinearyDatabase itinearyDatabase;
    private ArrayList<String>dateList=new ArrayList<>();
    private ImageView official,official2,official3,official4,official5,official6,official7;
    private int day, month, year;
    private String date,newDate;
    private String getCurentDate,getDate;
    private String date1,date6,date7,date8,date9,date10,date11;
    private Date date4;
    private TextView start,end,destination,end1;
    private String[] listItems,startList,endList;
    private Date date2,date3;
    private ArrayList<String> list=new ArrayList<>();

    boolean[] checkedItem;
    ArrayList<Integer> mUserItem=new ArrayList<>();
    ArrayList<Integer> startItem=new ArrayList<>();
    ArrayList<Integer> endItem=new ArrayList<>();
    private Spinner facilities,facilities2,facilities3,facilities4,facilities5,facilities6,facilities7;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv_select_dest,tv_select_dest2,tv_select_dest3,tv_select_dest4,tv_select_dest5,tv_select_dest6,tv_select_dest7;
    private String[] name={" ", "Nothing", "Hotel", "Flight", "Flight & Hotel"};
    int[] images={R.drawable.facilities,R.drawable.new_none,R.drawable.new_hotel,R.drawable.new_flight,R.drawable.new_travel_fh};

   // private String[] nameChecked={" ", "Nothing","Flight"};
   // int[] imagesChecked={R.drawable.facilities,R.drawable.new_none,R.drawable.new_hotel,R.drawable.new_flight};

    private List<ItinearyDatabase> passItinearyDatabase;
    private ImageView plus,plus2,plus3,plus4,plus5,plus6,plus7;
    private ImageView minus,minus2,minus3,minus4,minus5,minus6,minus7;

    private ImageView advPlus1,advPlus2,advPlus3;
    private ImageView via,via2,via3,via4,via5,via6,via7;
    private ImageView plusPurpose,minusPurpose,upload1,camera1,plusPurpose1,minusPurpose1,upload2,camera2,plusPurpose2,minusPurpose2,upload3,camera3,llPlusMinusPurpose3,plusPurpose3,minusPurpose3;
    LinearLayout llPurpose,llPurpose1,llPurpose2,llPurpose3;

    private EditText editTextDialogUserInput;
    private RecyclerView dateParadime;
    private List<String> list_sou_des;
    private LinearLayout ll_1,ll_2,ll_3,ll_4,ll_5,ll_6,ll_7,ll_8,ll_9;
    private TextView tvDate_2,tvDate_3,tvDate_4,tvDate_5,tvDate_6,tvDate_7;
    boolean flag=true;
    boolean samedaychecked=false;
    String dest="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_newtravel_request_activity);
        perDiam();


/*
       Bundle dataintent= getIntent().getExtras();
        int datasize=dataintent.getInt("select_project");
        String dataname=dataintent.getString("projact_name");*/


    /*    Intent intent=getIntent();
        breakfast=intent.getStringExtra("breakfast");
        lunch=intent.getStringExtra("lunch");
        dinner=intent.getStringExtra("dinner");
        none=intent.getStringExtra("none");
        incedental=intent.getStringExtra("incedental");
        estd_perdiam=intent.getStringExtra("estd_perdiam");
        estd_perdiam=intent.getStringExtra("estd_perdiam");*/

        Intent intent=getIntent();
        emp_name=intent.getStringExtra("emp_name");
        Designation=intent.getStringExtra("Designation");
        CostCenter=intent.getStringExtra("CostCenter");
        ed_purpose=findViewById(R.id.ed_purpose);
        dateParadime=findViewById(R.id.dateParadim);
        llOtherAdv1=findViewById(R.id.llOtherAdv1);
        llOtherAdv2=findViewById(R.id.llOtherAdv2);
        //llOtherAdv3=findViewById(R.id.llOtherAdv3);

     //  advPlus1=findViewById(R.id.advPlus1);

        advPlus2=findViewById(R.id.advPlus2);
        //advPlus3=findViewById(R.id.advPlus3);


        tvDateAdvance=findViewById(R.id.tvDateAdvance);
        viaOfficial=findViewById(R.id.viaOfficial);
        viaFlight=findViewById(R.id.viaFlight);

        tv_personal=findViewById(R.id.tv_personal);
        tv_flight=findViewById(R.id.tv_flight);
        tv_personal1=findViewById(R.id.tv_personal1);
        tv_flight1=findViewById(R.id.tv_flight1);
        tv_flight2=findViewById(R.id.tv_flight2);
        tv_personal2=findViewById(R.id.tv_personal2);

        llPurpose=findViewById(R.id.llPurpose);

        llPurpose1=findViewById(R.id.llPurpose1);
        llPurpose2=findViewById(R.id.llPurpose2);
        plusPurpose=findViewById(R.id.plusPurpose);
        plusPurpose.setOnClickListener(this);

       /* minusPurpose=findViewById(R.id.minusPurpose);
        minusPurpose.setOnClickListener(this);*/
        upload1=findViewById(R.id.upload1);
        camera=findViewById(R.id.camera);
        camera.setOnClickListener(this);
        camera1=findViewById(R.id.camera1);
        camera1.setOnClickListener(this);
        camera2=findViewById(R.id.camera2);
        camera2.setOnClickListener(this);
        plusPurpose1=findViewById(R.id.plusPurpose1);
        plusPurpose1.setOnClickListener(this);
        minusPurpose1=findViewById(R.id.minusPurpose1);
        minusPurpose1.setOnClickListener(this);
        upload2=findViewById(R.id.upload2);
         plusPurpose2=findViewById(R.id.plusPurpose2);
        plusPurpose2.setOnClickListener(this);
        minusPurpose2=findViewById(R.id.minusPurpose2);
            minusPurpose2.setOnClickListener(this);
        camera3=findViewById(R.id.camera3);

        flipper=(ViewFlipper)findViewById(R.id.viewflipper) ;
        next = findViewById(R.id.goNextPurpose);
        next.setOnClickListener(this);
        next1= findViewById(R.id.itinearySave) ;
        next1.setOnClickListener(this);
        next2= findViewById(R.id.goNextAdvance);
        next3=findViewById(R.id.finalSubmit);
        next3.setOnClickListener(this);
        itinearyPrevious=findViewById(R.id.itinearyPrevious);
        itinearyPrevious.setOnClickListener(this);
        advancePrevious=findViewById(R.id.advancePrevious);
        advancePrevious.setOnClickListener(this);
        summaryPrevious=findViewById(R.id.summaryPrevious);
        summaryPrevious.setOnClickListener(this);
        upload= findViewById(R.id.upload);
        camera=  findViewById(R.id.camera);
        purpose= findViewById(R.id.purpose);
        startEnd=findViewById(R.id.startEnd);

        final MyTravelRequestBean myTravelRequestBean=new MyTravelRequestBean();

        mRecyclerView = findViewById(R.id.projectRecyclerview);
      //  mCityRecyclerView = findViewById(R.id.cityRecyclerview);
        passItinearyDatabase=new ArrayList<ItinearyDatabase>();

        listItems=getResources().getStringArray(R.array.poject_name);
        startList=getResources().getStringArray(R.array.source_station);
        endList=getResources().getStringArray(R.array.destination_station);
        checkedItem= new boolean[listItems.length];
        checkedItem=new boolean[startList.length];
        checkedItem=new boolean[endList.length];
        list_sou_des=new ArrayList<>();
        checkboxSameday= findViewById(R.id.checkboxSameday);
        llPlusMinus= findViewById(R.id.llPlusMinus);
      /* tvDate1= findViewById(R.id.tvDate1);
       end1= findViewById(R.id.end1);*/
        tv1= findViewById(R.id.tv_start);
        tv2= findViewById(R.id.tv_mid1);
        tv3= findViewById(R.id.tv_mid3);
        tv4= findViewById(R.id.tv_dest);
        tv5=findViewById(R.id.tv_mid2);
        tv6=findViewById(R.id.tv_dest_last);


        seekbar=findViewById(R.id.seekbar);
        advanceAmountPer=findViewById(R.id.advanceAmountPer);
        totalAmount=findViewById(R.id.totalAmount);

        estmPdm=findViewById(R.id.estmPdm);
        check1=findViewById(R.id.check1);
        check2=findViewById(R.id.check2);
        check3=findViewById(R.id.check3);
        check4=findViewById(R.id.check4);
        check5=findViewById(R.id.check5);
        project= findViewById(R.id.project);
        destination=findViewById(R.id.destination);

        start= findViewById(R.id.start);
        start.setOnClickListener(this);
        start.setText(CostCenter);
        end= findViewById(R.id.end);
        end.setOnClickListener(this);
        end.setText(CostCenter);

        facilities= findViewById(R.id.facilities);
        facilities2= findViewById(R.id.facilities2);
        facilities3= findViewById(R.id.facilities3);
        facilities4= findViewById(R.id.facilities4);
        facilities5= findViewById(R.id.facilities5);
        facilities6= findViewById(R.id.facilities6);
        facilities7= findViewById(R.id.facilities7);
        tvDate=findViewById(R.id.tvDate);

        itinearySave=findViewById(R.id.itinearySave);
          itinearySave.setOnClickListener(this);
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
       /* minus=findViewById(R.id.minus);*/
       /* minus.setOnClickListener(this);*/
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
       // llReturn=findViewById(R.id.llReturn);

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


/*
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitCityApi();
            }
        });
*/

        itineary=findViewById(R.id.itineary);
        purpose=findViewById(R.id.purpose);
        advance=findViewById(R.id.advance);
        summary=findViewById(R.id.summary);


        ed_purpose.addTextChangedListener(new TextWatcher() {
            // Before EditText text change
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText
            @Override
            public void afterTextChanged(Editable editable) {
                processButtonByTextLength();

            }
        });
        ed_purpose.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                // Get key action, up or down.
                int action=keyEvent.getAction();
                // Only process key up action, otherwise this listener will be triggered twice because of key down action.
                if (action==keyEvent.ACTION_UP){
                    processButtonByTextLength();
                }
                return false;
            }
        });
/*
        checkboxSameday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    start.setEnabled(false);
                }else {
                    start.setEnabled(true);
                }

            }
        });
*/
/*
        checkboxSameday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    end.setEnabled(false);
                }else {
                    end.setEnabled(true);
                }
            }
        });
*/





/*
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check1.isChecked()){
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                }else {
                    check2.setChecked(true);
                    check3.setChecked(true);
                    check4.setChecked(true);

                }
            }
        });
*/
/*
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    double b1=Double.valueOf(estd_perdiam);
                    double b2=Double.valueOf(breakfast);
                    calCheck2= (int) ((b1*b2)/100);
                }
            }
        });
*/
/*
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){

                    double l1=Double.valueOf(estd_perdiam);
                    double l2=Double.valueOf(lunch);
                    calcheck3=((int)(l1*l2)/100);
                }
            }
        });
*/
/*
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double l1=Double.valueOf(estd_perdiam);
                double l2=Double.valueOf(lunch);
                calCheck2= (int) ((l1*l2)/100);
                calcheck3=((int)(l1*l2)/100);

                calcheck3=(amount*25)/100;
            }
        });
*/
/*
        check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double d1=Double.valueOf(estd_perdiam);
                double d2=Double.valueOf(dinner);
                calcheck4= (int) ((d1*d2)/100);

            }
        });
*/
/*
        check5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double i1=Double.valueOf(estd_perdiam);
                double i2=Double.valueOf(incedental);
                calcheck5= (int) ((i1*i2)/100);
            }
        });
*/
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
               // checkBoxAmount= calCheck2+calcheck3+calcheck4+calcheck5;
                totalAmount.setText(""+checkBoxAmount);

                progress=(progress/stepSize)*stepSize;
                amount=((checkBoxAmount)*progress)/100;
                //advanceAmount.setText(""+percent[progress]+"%");
                advanceAmountPer.setText(""+progress+"%");
                bar.setMax(70);


                estmPdm.setText(""+amount);
            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {
                int value=bar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {

            }
        });



        checkboxSameday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked())
                {
                    plus.setEnabled(false);
                    official.setEnabled(false);
                    facilities.setEnabled(false);
                    sameDayCheckStatus="1";

                }else {
                    plus.setEnabled(true);
                    facilities.setEnabled(true);
                    plus.setEnabled(true);
                    sameDayCheckStatus="0";
                }
            }
        });


/*
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
*/
            SpinnerAdapter spinnerAdapter=new iconnect.psi.com.iconnect.adapter.SpinnerAdapter(this,name,images);
            facilities.setAdapter(spinnerAdapter);

        facilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(mActivity, ""+name, Toast.LENGTH_SHORT).show();
                facility1=facilities.getItemAtPosition(i).toString();
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
/*
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] image=new String[]{"Camera","Gallery"};
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(CreateNewTravelRequestActivity.this,android.R.layout.select_dialog_item,image);
                AlertDialog.Builder builder=new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
                builder.setTitle("Select Camera or Gallery");
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int media) {
                        if (media==0) {
                            //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            try {
                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            dialog.cancel();
                        }else if (media==1){
                            try {
                                Intent intent=new Intent();
                                //intent.addCategory(Intent.CATEGORY_OPENABLE);
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"media"),FILE    );
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                });
                final AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
*/
/*
        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] image=new String[]{"Camera","Gallery"};
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(CreateNewTravelRequestActivity.this,android.R.layout.select_dialog_item,image);
                AlertDialog.Builder builder=new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
                builder.setTitle("Select Camera or Gallery");
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int media) {
                        if (media==0) {
                            //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            try {
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
*/

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=new File(Environment.getExternalStorageDirectory(),"Agenda.pdf");
                Uri path=Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "*/pdf*");/**/

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
       /* minus=findViewById(R.id.minus);*/
        minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_1.setVisibility(view.GONE);
            }
        });
        minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_2.setVisibility(view.GONE);
            }
        });
        minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_3.setVisibility(view.GONE);
            }
        });
        minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_4.setVisibility(view.GONE);
            }
        });
        minus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_5.setVisibility(view.GONE);
            }
        });
        minus7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_6.setVisibility(view.GONE);
            }
        });
/*
        advPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llOtherAdv1.setVisibility(view.VISIBLE);
            }
        });
*/
/*
        advPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llOtherAdv2.setVisibility(view.VISIBLE);
            }
        });
*/
/*
        advPlus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llOtherAdv3.setVisibility(view.VISIBLE);
            }
        });
*/

      /*  minus=findViewById(R.id.minus);*/
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

        end=findViewById(R.id.end);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentProject dialog=new FragmentProject();
/*
                FragmentProject dialog = new FragmentProject(getApplicationContext(), new CallBackResult() {
                    @Override
                    public void getResult(String data) {

                        Log.d("data",data.toString());
                    }
                });*/

                dialog.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        });

        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,1);
                fragmentCity.show();
            }
        });
        tv_select_dest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity1=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,4);
                fragmentCity1.show();

            }
        });


        tv_select_dest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity2=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,5);
                fragmentCity2.show();

            }
        });
        tv_select_dest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity3=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,6);
                fragmentCity3.show();

            }
        });
        tv_select_dest5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity4=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,7);
                fragmentCity4.show();
            }
        });
        tv_select_dest6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity5=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,8);
                fragmentCity5.show();
            }
        });
        tv_select_dest7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCity fragmentCity6=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,9);
                fragmentCity6.show();
            }
        });
/*
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsView = li.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                start.setText("Start: "+userInput.getText().toString().trim());

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface diialog, int i) {
                                diialog.cancel();
                            }
                });

                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
            }
        });
*/
/*
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsView1 = lii.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
                alertDialogBuilder1.setView(promptsView1);

                final EditText userInput1 = (EditText) promptsView1
                        .findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder1
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                end.setText("End: "+userInput1.getText().toString().trim());
                                if (samedaychecked){
                                    dest=end.getText().toString().trim().substring(4);
                                    destination.setText(dest);

                                }


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
*/
/*
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater liii = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsView2 = liii.inflate(R.layout.edittext_dialog, null);

                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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
*/

          /*  destination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openCityDialog();

                }
            });*/
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog();

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
/*
        itinearySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itinearyDatabase=new ItinearyDatabase();
                itinearyDatabase.setStart_journey(start.getText().toString().trim());
                itinearyDatabase.setEnd_journey(end.getText().toString().trim());
                itinearyDatabase.setDate(tvDate.getText().toString().trim());
                passItinearyDatabase.add(itinearyDatabase);
            }
        });
*/
        via.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii2 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww = lii2.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
                alertDialogBuilderr.setView(promptsVieww);
                final EditText userInputt =  promptsVieww.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {
                        list_sou_des.add(""+userInputt.getText().toString().trim());
                        if(list_sou_des.size()==1){
                            tv2.setText(""+list_sou_des.get(0));
                            tv_personal.setText("P");

                        }else if (list_sou_des.size()==2){
                            tv3.setText(tv2.getText().toString().trim());
                            tv4.setText(""+list_sou_des.get(1));
                            tv_personal1.setText("P");

                            tv5.setText(tv4.getText().toString().trim());
                            tv_personal2.setText("P");
                        }
                        /*else if (list_sou_des.size()==3){
                            tv5.setText(tv4.getText().toString().trim());
                            tv_personal2.setText("P");
                        }*/
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
               // tv4.setText(""+destination.getText().toString().trim());
                tv6.setText(""+destination.getText().toString().trim());
            }
        });

        checkboxSameday.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               //samedaychecked=true;
                dest=end.getText().toString().trim().substring(4);
                destination.setText(dest);
                if (end.getText().toString().trim().substring(4).equalsIgnoreCase(dest))
                {

                   // itinearySave.setEnabled(true);
/*
                    nex1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            flipper.showNext();
                            onChangeTab(2);
                        }
                    });
*/

                }else {
                    /*nex1.setEnabled(false);*/

                    //nex1.setEnabled(true);
                    // itinearySave.setEnabled(false);
                }

            }
        });

        via2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LayoutInflater lii3 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww2 = lii3.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr2 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
                alertDialogBuilderr2.setView(promptsVieww2);
                final EditText userInputt2 =  promptsVieww2.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilderr2.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id)
                    {
                        list_sou_des.add(""+userInputt2.getText().toString().trim());
                        if(list_sou_des.size()==1){
                            tv2.setText(""+list_sou_des.get(0));

                        }else if (list_sou_des.size()==2){
                            tv2.setText(""+list_sou_des.get(0));
                            tv3.setText(""+list_sou_des.get(1));

                        }
                        else{
                            tv3.setText(""+list_sou_des.get(1));
                            tv5.setText(""+list_sou_des.get(1));
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
                tv6.setText("End:"+end.getText().toString().trim());
            }
        });

        via3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lii4 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww3 = lii4.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr3 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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
                LayoutInflater lii5 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww4 = lii5.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr4 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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
                LayoutInflater lii6 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww5 = lii6.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr5 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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
                LayoutInflater lii7 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww6 = lii7.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr6 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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
                LayoutInflater lii8 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
                View promptsVieww7 = lii8.inflate(R.layout.edittext_dialog, null);
                AlertDialog.Builder alertDialogBuilderr7 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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


                       date1=incrementDateByOne(date2);

                        // tvDate_2.setText(""+dt);
                        tvDate_2.setText(date1);
                        dateList.add(date1);



                ll_1.setVisibility(View.VISIBLE);
            }
        });

/*
        tv_select_dest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDestination(tv_select_dest2);
                dialogDestination(tv_select_dest2);
            }
        });
*/
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              // Date db =addDays(date2,2);

              date6=incrementDateByTwo(date2);
              tvDate_3.setText(date6);
              dateList.add(date6);

                ll_2.setVisibility(View.VISIBLE);
                }
        });
/*
        tv_select_dest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDestination(tv_select_dest3);
            }
        });
*/
       plus3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               date7=incrementDateByThree(date2);
               tvDate_4.setText(date7);
               dateList.add(date7);

               ll_3.setVisibility(view.VISIBLE);
           }
       });
/*
       tv_select_dest4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest4);
           }
       });
*/
       plus4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               date8=incrementDateByFour(date2);
               tvDate_5.setText(date8);
               dateList.add(date8);
                ll_4.setVisibility(view.VISIBLE);
           }
       });
/*
       tv_select_dest5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest5);
           }
       });
*/
       plus5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               date9=incrementDateByFive(date2);
               tvDate_6.setText(date9);
               dateList.add(date9);
               ll_5.setVisibility(view.VISIBLE);
           }
       });
/*
       tv_select_dest6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest5);
           }
       });
*/
       plus6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               date10=incrementDateBySix(date2);
               tvDate_7.setText(date10);
                ll_6.setVisibility(view.VISIBLE);
           }
       });
/*
       tv_select_dest7.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogDestination(tv_select_dest6);
           }
       });
*/
        //getProjectData();

        final int positition=  flipper.getDisplayedChild();

        onChangeTab(positition);

/*
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
                if(ed_purpose.getText().toString().trim() != null) {
                    myTravelRequestBean.setPurpose(ed_purpose.getText().toString().trim());
                }else{
                    Toast.makeText(CreateNewTravelRequestActivity.this, "Plz insert some purpose", Toast.LENGTH_SHORT).show();
                }
                onChangeTab(1);
            }
        });
*/

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
                onChangeTab(3);
            }
        });

    }

    private void perDiam() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        ApiInterface apiInterface= ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.perDiam(hashMap).enqueue(new Callback<Perdiam>() {
            @Override
            public void onResponse(Call<Perdiam> call, Response<Perdiam> response) {
                if (response.isSuccessful()){
                  //  for(int i=0;i<response.body().getData().size();i++) {
                         breakfast = response.body().getData().get(0).getBreakFast();
                         Log.e("breakfast","====="+breakfast);
                         lunch=response.body().getData().get(0).getLunch();
                         dinner=response.body().getData().get(0).getDinner();
                         none= response.body().getData().get(0).getNones();
                         incedental=response.body().getData().get(0).getIncedental();
                        estd_perdiam=response.body().getData().get(0).getEstimatedPerdiam();

                        dateParadimRecyclerview=new DateParadimRecyclerview(CreateNewTravelRequestActivity.
                                this,dateList,breakfast,lunch,dinner,none,incedental,estd_perdiam);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        dateParadime.setLayoutManager(mLayoutManager);
                        dateParadime.setItemAnimator(new DefaultItemAnimator());
                        dateParadime.setAdapter(dateParadimRecyclerview);

                 //   }

                }

            }

            @Override
            public void onFailure(Call<Perdiam> call, Throwable t) {

            }
        });


    }



    private void openCityDialog(final ArrayList<CityResponse> city) {
        mDialog=new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_list);
        mDialog.hide();
        final ListView lvCity=mDialog.findViewById(R.id.rv_city);
        final CityAdapter cityAdapter=new CityAdapter(this,R.layout.row_city,city);
        lvCity.setAdapter(cityAdapter);
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city_name=city.get(i).getData().get(i).getCityName();


            }
        });

    }

    // Enable or disable and change button text by EditText text length.
    private void processButtonByTextLength() {
        String inputText=ed_purpose.getText().toString();
        if (inputText.length()>=10){
            next.setText("GO NEXT");
            next.setEnabled(true);
            }else {
            next.setEnabled(false);
        }
    }
    public static Date addDays(Date date, int days)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }

    public String incrementDateByOne(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        int month=c.get(Calendar.MONTH)+1;
        String formattedDate=c.get(Calendar.DATE)+"-"+month+"-"+c.get(Calendar.YEAR);
        return formattedDate;

    }
    public String incrementDateByTwo(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 2);
        int month=c.get(Calendar.MONTH)+1;
        String formattedDate=c.get(Calendar.DATE)+"-"+month+"-"+c.get(Calendar.YEAR);
        return formattedDate;
    }
    public String incrementDateByThree(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 3);
        int month=c.get(Calendar.MONTH)+1;
        String formattedDate=c.get(Calendar.DATE)+"-"+month+"-"+c.get(Calendar.YEAR);
        return formattedDate;
    }
    public String incrementDateByFour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 4);
        int month=c.get(Calendar.MONTH)+1;
        String formattedDate=c.get(Calendar.DATE)+"-"+month+"-"+c.get(Calendar.YEAR);
        return formattedDate;
    }
    public String incrementDateByFive(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 5);
        int month=c.get(Calendar.MONTH)+1;
        String formattedDate=c.get(Calendar.DATE)+"-"+month+"-"+c.get(Calendar.YEAR);
        return formattedDate;
    }
    public String incrementDateBySix(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 6);
        int month=c.get(Calendar.MONTH)+1;
        String formattedDate=c.get(Calendar.DATE)+"-"+month+"-"+c.get(Calendar.YEAR);
        return formattedDate;
    }


    private void dialogDestination(final TextView tv_select_dest2) {
        LayoutInflater liidest2 = LayoutInflater.from(CreateNewTravelRequestActivity.this);
        View promptsViewdest2 = liidest2.inflate(R.layout.edittext_dialog, null);
        AlertDialog.Builder alertDialogBuilderdest2 = new AlertDialog.Builder(CreateNewTravelRequestActivity.this);
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
                dateList.add(date);




                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
                getCurentDate=sdf.format(calendar.getTime());
                date2 = new GregorianCalendar(i, i1, i2).getTime();
                if (tvDate.getText().toString().contains(""+date) && destination.getText().toString().contains("")){
                    itinearySave.setEnabled(true);
/*
                    nex1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            flipper.showNext();
                            //onChangeTab(2);
                        }
                    });
*/
                }
                 else if
                  (tvDate.getText().toString().contains(""+date) && destination.getText().equals("Destination")){
                        itinearySave.setEnabled(false);
/*
                        nex1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flipper.showNext();
                                //onChangeTab(2);
                            }
                        });
*/
                    }else {
                    itinearySave.setEnabled(false);
                }
                    /*
                    if (tvDate.getText().toString().contains(""+date) && destination.getText().toString().contains("")){
                        nex1.setEnabled(false);
                        nex1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flipper.showNext();
                                onChangeTab(2);
                            }
                        });
                    }
*/
                }

        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(CreateNewTravelRequestActivity.this,listener,day,month,year);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

        datePickerDialog.show();
    }

    private void onChangeTab(int position) {
        Log.e("Position for Selecion","====="+position);

        switch (position)
        {
            case 0:
                purpose.setTextColor(Color.RED);
                itineary.setTextColor(Color.WHITE);
                advance.setTextColor(Color.WHITE);
                summary.setTextColor(Color.WHITE);
                break;
            case 1:
                itineary.setTextColor(Color.RED);
                purpose.setTextColor(Color.WHITE);
                advance.setTextColor(Color.WHITE);
                summary.setTextColor(Color.WHITE);
                break;
            case 2:
                advance.setTextColor(Color.RED);
                itineary.setTextColor(Color.WHITE);
                purpose.setTextColor(Color.WHITE);
                summary.setTextColor(Color.WHITE);
                break;
            case 3:
                summary.setTextColor(Color.RED);
                itineary.setTextColor(Color.WHITE);
                advance.setTextColor(Color.WHITE);
                purpose.setTextColor(Color.WHITE);
                break;        }

    }

    private void onTravelDataSend() {
        HashMap<String,String> hashMap=new HashMap();
        textviewdata.add(tv2.getText().toString());
        textviewdata.add(tv4.getText().toString().trim());
        StringBuilder commaSepValueBuilder = new StringBuilder();
        for ( int i = 0; i< textviewdata.size(); i++){
            //append the value into the builder
            commaSepValueBuilder.append(textviewdata.get(i));

            //if the value is not the last element of the list
            //then append the comma(,) as well
            if ( i != textviewdata.size()-1){
                commaSepValueBuilder.append(", ");
            }
        }
        String data=commaSepValueBuilder.toString();

       /* String csv =tv2.getText().toString().trim().concat(tv4.getText().toString().trim());*/
        Log.e("via",commaSepValueBuilder.toString());
       /* String[] numbers = {tv2.getText().toString(), tv4.getText().toString()};
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            if (i > 0) {
                buffer.append(',');
            }
            buffer.append(numbers[i]);
        }
        data= Arrays.stream(numbers).collect(Collectors.joining(","));*/
        hashMap.put("API_KEY","72729a5129c69fc3b53ddf8d2790a5b0");
       hashMap.put("purpose",""+ed_purpose.getText().toString().trim());
        hashMap.put("start_location", start.getText().toString().trim());
        hashMap.put("end_location", end.getText().toString().trim());
        hashMap.put("start_date", tvDate.getText().toString().trim());
        hashMap.put("project_id",project.getText().toString().trim());
        hashMap.put("destination",destination.getText().toString().trim());
        hashMap.put("via",data);

        hashMap.put("emp_code","PSI/0010");
        hashMap.put("travel_type","p");
        ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendTravelBookingResponse(hashMap).enqueue(new Callback<TravelBooking>() {
            @Override
            public void onResponse(Call<TravelBooking> call, Response<TravelBooking> response) {
                if (response.body().getErrorCode()==1){
                  bookRes=response.body().getMessage();
                  String bookId=response.body().getLastid();
                    Toast.makeText(CreateNewTravelRequestActivity.this,  ""+bookRes, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CreateNewTravelRequestActivity.this,MyTravelRequestActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(CreateNewTravelRequestActivity.this, "" + bookRes, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TravelBooking> call, Throwable t) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.goNextPurpose:
                travePurposeDataSend();
                break;
            case R.id.itinearySave:
                traveItinearyDataSend();
                dateParadimRecyclerview.notifyDataSetChanged();
                break;

            case R.id.finalSubmit:
                onTravelDataSend();
                Intent intent=new Intent(CreateNewTravelRequestActivity.this,MyTravelRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.itinearyPrevious:
                flipper.showPrevious();
                onChangeTab(0);
                break;
            case R.id.advancePrevious:
                flipper.showPrevious();
                onChangeTab(1);
                break;
            case R.id.summaryPrevious:
                flipper.showPrevious();
                onChangeTab(2);
                break;
            case R.id.plusPurpose:
                llPurpose.setVisibility(view.VISIBLE);
                break;
            case R.id.minusPurpose1:
                llPurpose.setVisibility(view.GONE);
                break;
            case R.id.plusPurpose1:
                llPurpose1.setVisibility(view.VISIBLE);
                break;
            case R.id.minusPurpose2:
                llPurpose1.setVisibility(view.GONE);
                break;
            case R.id.destination:

                FragmentCity fragmentCity=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,1);
                fragmentCity.show();
                    //hitCityApi();
               /* FragmentCity fragmentCity=new FragmentCity(CreateNewTravelRequestActivity.this);
                fragmentCity.show(getSupportFragmentManager(),"City dialog");*/

            case R.id.end:

                FragmentCity fragmentCityEnd=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,2);
                fragmentCityEnd.show();
               /* FragmentCity fragmentCity=new FragmentCity(CreateNewTravelRequestActivity.this);
                fragmentCity.show(getSupportFragmentManager(), "MyDialogFragment");*/
                break;
            case R.id.start:
                FragmentCity fragmentCityStart=new FragmentCity(CreateNewTravelRequestActivity.this,CreateNewTravelRequestActivity.this,3);
                fragmentCityStart.show();
                break;
            case R.id.camera:
                selectImage(REQUEST_CAMERA);
                break;
            case R.id.camera1:
                selectImage(REQUEST_CAMERA3);
                break;
            case R.id.camera2:
                selectImage(REQUEST_CAMERA4);
                break;
        }
    }

    private void travePurposeDataSend() {
        File imagepath = new File(path);

        // Map<String, ArrayList<StaticDataBeans>> addressMapList = new HashMap<>();
        RequestBody empPurpose = RequestBody.create(MediaType.parse("text/plain"), ed_purpose.getText().toString().trim());
     //   RequestBody image = RequestBody.create(MediaType.parse("text/plain"), thumbnail.toString());
       /* File file = new File(destinationImage.getAbsolutePath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("Multipart/form-data"), file);
        Log.e("Image.....", "" + file.getName());
        MultipartBody.Part fileData = MultipartBody.Part.createFormData("attach_doc", file.getName(), requestFile);*/
        List<MultipartBody.Part> filesDataList = new ArrayList<>();
        for (String filePath : bitmaplist) {
            File file = new File(filePath);
            RequestBody requestFile = RequestBody.create(MediaType.parse("Multipart/form-data"), file);
            MultipartBody.Part fileData = MultipartBody.Part.createFormData("attach_doc[]", file.getName(), requestFile);
            filesDataList.add(fileData);
        }


        RequestBody apiKey1 = RequestBody.create(MediaType.parse("text/plain"), "72729a5129c69fc3b53ddf8d2790a5b0");

        ApiInterface apiInterface = ApiClient.getClientCI().create(ApiInterface.class);

        Call<PurposeResponse> purposeResponseCall = apiInterface.sendPurposeResponse(apiKey1, empPurpose, filesDataList);

        purposeResponseCall.enqueue(new Callback<PurposeResponse>() {
            @Override
            public void onResponse(Call<PurposeResponse> call, Response<PurposeResponse> response) {
                if (response.isSuccessful()){
                    Log.e("Request Url","==="+call.request().url());
                    String pur=response.body().getMessage();
                    Toast.makeText(CreateNewTravelRequestActivity.this, ""+pur, Toast.LENGTH_SHORT).show();
                    flipper.showNext();
                    onChangeTab(1);
                }
            }

            @Override
            public void onFailure(Call<PurposeResponse> call, Throwable t) {

            }
        });
    }

  /*  String input = "123456789";     //input string
    String firstFourChars = "";     //subsctring containing first 4 characters

if (input.length() > 4)
    {
        firstFourChars = input.substring(0, 4);
    }
else
    {
        firstFourChars = input;
    }

System.out.println(firstFourChars);*/

   // String endLoc= end.getText().toString().trim().substring(0,4);
   // Log.e("sub string",""+endLoc);
    private void traveItinearyDataSend() {

        textviewdata.add(tv2.getText().toString());
        textviewdata.add(tv4.getText().toString().trim());
        StringBuilder commaSepValueBuilder = new StringBuilder();
        for ( int i = 0; i< textviewdata.size(); i++)
        {
            commaSepValueBuilder.append(textviewdata.get(i));
            if ( i != textviewdata.size()-1){
                commaSepValueBuilder.append(", ");
            }
        }
        String data=commaSepValueBuilder.toString();
        Log.e("via",commaSepValueBuilder.toString());
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("same_day", sameDayCheckStatus);
       /* if(start.getText().toString().trim().equalsIgnoreCase("")) {
            hashMap.put("start_location", start.getText().toString().trim());
        }else{
            //Toast.makeText("")
        }*/
        hashMap.put("end_location", end.getText().toString());
        hashMap.put("project_id",project.getText().toString().trim());
        hashMap.put("project_percent",project.getText().toString().trim());
        hashMap.put("dates",tvDate.getText().toString().trim());
        hashMap.put("facility",facilities.getSelectedItem().toString().trim());
        hashMap.put("destination",destination.getText().toString().trim());
        hashMap.put("via",data);
        hashMap.put("type","p");
        hashMap.put("user_id","PSI/0010");
        Log.e("Travel Req Json",new Gson().toJson(hashMap));
        ApiInterface apiInterface= ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendItinearyResponse(hashMap).enqueue(new Callback<ItinearyResponse>() {
            @Override
            public void onResponse(Call<ItinearyResponse> call, Response<ItinearyResponse> response) {
                if (response.isSuccessful()){

                   // Log.e("Itineart URL","==="+call.request().url());
                    itry=response.body().getMessage();
                    flipper.showNext();
                    onChangeTab(2);
                    Toast.makeText(CreateNewTravelRequestActivity.this, ""+itry, Toast.LENGTH_SHORT).show();

                    if (response.body().equals("Itneary Complete! Go Next"))
                    {
                        advanceDate = tvDate.getText().toString();
                        tvDateAdvance.setText(advanceDate);


                    }
                    else {

                    }

                }else {
                    Toast.makeText(CreateNewTravelRequestActivity.this, ""+itry, Toast.LENGTH_SHORT).show();
                   /* flipper.showNext();
                    onChangeTab(2);*/
                }
            }

            @Override
            public void onFailure(Call<ItinearyResponse> call, Throwable t) {
            }

         });

    }

/*
    private void travePurposeDataSend() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        hashMap.put("purpose",ed_purpose.getText().toString().trim());
        ApiInterface apiInterface= ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendPurposeResponse(hashMap).enqueue(new Callback<PurposeResponse>() {
            @Override
            public void onResponse(Call<PurposeResponse> call, Response<PurposeResponse> response) {
         if (response.body().getErrorCode()==1){
             pur=response.body().getMessage();
             Toast.makeText(CreateNewTravelRequestActivity.this, ""+pur, Toast.LENGTH_SHORT).show();
             flipper.showNext();
             onChangeTab(1);

         }else {
             Toast.makeText(CreateNewTravelRequestActivity.this, ""+pur, Toast.LENGTH_SHORT).show();
         }
            }
            @Override
            public void onFailure(Call<PurposeResponse> call, Throwable t) {

            }
        });
    }
*/
/*
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 1  && resultCode == this.RESULT_OK) {
                if (requestCode == FILE  && resultCode == RESULT_OK && null != data ) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    camera.setImageBitmap(BitmapFactory.decodeFile(picturePath));
case
                }                try {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        camera.setImageBitmap(bitmap);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

        }catch (Exception e){
            e.printStackTrace();
        }
*/
/*
         try {
             if (requestCode == 2  && resultCode == this.RESULT_OK) {
                 if (requestCode == FILE) {
                     try {
                         Uri selectedImage = data.getData();
                         InputStream imageStream = this.getContentResolver().openInputStream(selectedImage);
                         bitmap = BitmapFactory.decodeStream(imageStream);
                         ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
                         bitmap.compress(Bitmap.CompressFormat.PNG, 0,
                                 byteArrayBitmapStream);
                         byte[] b = byteArrayBitmapStream.toByteArray();
                         camera.setImageBitmap(bitmap);
                         encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                     } catch (FileNotFoundException e) {
                         e.printStackTrace();
                     }

                 }else {
                     try {
                         bitmap = (Bitmap) data.getExtras().get("data");
                         camera.setImageBitmap(bitmap);
                         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                         bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                         byte[] byteArray = byteArrayOutputStream.toByteArray();
                         encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                     }catch (Exception e){
                         e.printStackTrace();
                     }
                 }
             }
         }catch (Exception e){
             e.printStackTrace();
         }
*//*


     }
*/


    private void hitAsyncCityApi() {

        class GetState extends AsyncTask<Void, Void, Integer> {

            @Override
            protected Integer doInBackground(Void... voids) {

              //  hitCityApi();
                return 0;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
               /* if (integer == 0) {
                    Log.i("State count", integer + "");
                   // addState();
                } else {
                    Toast.makeText(mActivity, mActivity.getResources().getString(R.string.server_problem), Toast.LENGTH_SHORT).show();
                }*/

            }
        }
        GetState getCount = new GetState();
        getCount.execute();
    }

    private void hitCityApi() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");
        ApiInterface apiInterface=ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendCityResponse(hashMap).enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                Log.e("Sign in Url", "" + call.request().url());
                for (int i=0;i<response.body().getData().size();i++) {
                    String city=response.body().getData().get(i).getCityName();
                    list.add(city);
                    Dialog dialog=new Dialog(CreateNewTravelRequestActivity.this);
                    LayoutInflater li = (LayoutInflater) CreateNewTravelRequestActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view=li.inflate(R.layout.row_city,null,false);
                    dialog.setContentView(view);
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.e("Login Failed", "" + t.getMessage());
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
       /* int datasize=intent.getInt("select_project");
        String dataname=dataintent.getString("projact_name");*/
        //String size=intent.getStringExtra("select_project");
        String name=intent.getStringExtra("projact_name");
       project.setText(name);

    }
    private void selectImage(final int requestCamera){
    final CharSequence[] items = { "Take Photo", "Choose from Library",
            "Cancel" };
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Add Photo!");
    builder.setItems(items, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int item) {
            boolean result= Utility.checkPermission(CreateNewTravelRequestActivity.this);
            String userChoosenTask;
            if (items[item].equals("Take Photo")) {
                userChoosenTask="Take Photo";
                if(result)
                    cameraIntent(requestCamera);
            } else if (items[item].equals("Choose from Library")) {
                userChoosenTask="Choose from Library";
                if(result)
                    galleryIntent();
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        }
    });
    builder.show();
}
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent(int requestCamera) {
        if(requestCamera==REQUEST_CAMERA) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        }else if(requestCamera==REQUEST_CAMERA3){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA3);
        }else if(requestCamera==REQUEST_CAMERA4){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA4);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }else if(requestCode == REQUEST_CAMERA3){
                onCaptureImageResult(data);
            }else if(requestCode == REQUEST_CAMERA4){
                onCaptureImageResult(data);
            }

        }
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        destinationImage = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Log.e("Capture Imagee Url","=="+destinationImage.getAbsolutePath());
        bitmaplist.add(destinationImage.getAbsolutePath());
        Log.e("bitmap List Size","==============="+bitmaplist.size());
        FileOutputStream fo;
        try {
            destinationImage.createNewFile();
            fo = new FileOutputStream(destinationImage);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.setImageBitmap(thumbnail);
       // camera1.setImageBitmap(thumbnail);
      //  camera2.setImageBitmap(thumbnail);


    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        camera.setImageBitmap(bm);
        camera1.setImageBitmap(bm);
        camera2.setImageBitmap(bm);
    }

    @Override
    public void onReturnValue(String foo, int postions) {
        if(postions== 1){
            destination.setText(foo);
         }
         else if (postions==4){
            tv_select_dest2.setText(foo);
        }else if (postions==5){
            tv_select_dest3.setText(foo);
        }else if (postions==6){
            tv_select_dest4.setText(foo);
        }else if (postions==7){
            tv_select_dest5.setText(foo);

        }else if (postions==8){
            tv_select_dest6.setText(foo);
        }
        else if(postions==2){
            end.setText(foo);
        }
        else if(postions==3){
            start.setText(foo);
        }
    }


    @Override/*
    @Override
    public void onReturnValueProject(String fooProject) {
        project.setText(fooProject);

    }
*/

    public void onReturnValueProject(String fooProject, String fooProjectCode) {
        project.setText(fooProject);

    }

 /*
    @Override
    public void onReturnValue(String foo) {
        if(start.getText().toString().contains("")){
            start.setText(foo);
        }

        else if(end.getText().toString().contains("")) {

            end.setText(foo);
        }
       else if(destination.getText().toString().contains("")) {
            destination.setText(foo);
        }

    }*/
     public  void seekBarCalculation(int calCheck2){
         checkBoxAmount=calCheck2;

     }

    public  void seekBarCalculation(int calCheck2,int calCheck3){
        checkBoxAmount=calCheck2+calCheck3;
    }
    public  void seekBarCalculation(int calCheck2,int calCheck3,int calCheck4){
        checkBoxAmount=calCheck2+calCheck3+calCheck4;
    }
    public  void seekBarCalculation(int calCheck2,int calCheck3,int calCheck4,int calCheck5){
        checkBoxAmount=calCheck2+calCheck3+calCheck4+calCheck5;
    }
}

