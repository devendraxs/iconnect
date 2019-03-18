package iconnect.psi.com.iconnect.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.NewTravelRequestAdapter;


public class FragmentCreateNewTravelRequest extends AppCompatActivity implements FragmentPurpose.OnButtonClickListener {

    private TabLayout tabLayoutNewRequest;
    private ViewPager pagerNewRequest;
    private FragmentMyTravelRequest mActivity;
    private NewTravelRequestAdapter newTravelRequestAdapter;
    private TextView itineary,purpose,advance,expence;
    private String emp_name,Designation,CostCenter;
    private Button goNextPurpose,goNextItineary,goNextAdvance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_newtravel_request);

        pagerNewRequest =findViewById(R.id.pagerNewRequest);
            //mActivity= (FragmentMyTravelRequest) getActivity();

        Intent intent=getIntent();
        emp_name=intent.getStringExtra("emp_name");
        Designation=intent.getStringExtra("Designation");
        CostCenter=intent.getStringExtra("CostCenter");

            itineary=findViewById(R.id.itineary);
            purpose=findViewById(R.id.purpose);
            advance=findViewById(R.id.advance);
            expence=findViewById(R.id.expence);

            // tabLayoutNewRequest = (TabLayout) view.findViewById(R.id.tabLayoutNewRequest);
            pagerNewRequest=(ViewPager)findViewById(R.id.pagerNewRequest);

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
            newTravelRequestAdapter=new  NewTravelRequestAdapter(getSupportFragmentManager());
            pagerNewRequest.setAdapter(newTravelRequestAdapter);
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


            pagerNewRequest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            });
            new setAdapterTask().execute();


           // return view;
        }

        private void onChangeTab(int position) {
            if (position==0){
                itineary.setTextSize(20);
                purpose.setTextSize(20);
                purpose.setTextColor(Color.RED);
                itineary.setTextColor(Color.WHITE);
                advance.setTextColor(Color.WHITE);
                expence.setTextColor(Color.WHITE);
                advance.setTextSize(20);
                expence.setTextSize(20);
            }
            if (position==1){

                itineary.setTextSize(20);
                itineary.setTextColor(Color.RED);
                purpose.setTextColor(Color.WHITE);
                advance.setTextColor(Color.WHITE);
                expence.setTextColor(Color.WHITE);
                purpose.setTextSize(20);
                advance.setTextSize(20);
                expence.setTextSize(20);
            }
            if (position==2){
                itineary.setTextSize(20);
                purpose.setTextSize(20);
                advance.setTextSize(20);
                expence.setTextSize(20);
                advance.setTextColor(Color.RED);
                itineary.setTextColor(Color.WHITE);
                purpose.setTextColor(Color.WHITE);
                expence.setTextColor(Color.WHITE);
            }if (position==3){
                itineary.setTextSize(20);
                purpose.setTextSize(20);
                advance.setTextSize(20);
                expence.setTextColor(Color.RED);
                itineary.setTextColor(Color.WHITE);
                advance.setTextColor(Color.WHITE);
                expence.setTextColor(Color.WHITE);

            }
        }



    @Override
    public void onButtonClicked(View view) {
        int currPos=pagerNewRequest.getCurrentItem();
        switch (view.getId()) {
            case R.id.goNextPurpose:
                //handle currPos is zero
                pagerNewRequest.setCurrentItem(currPos);
                break;

        }
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
