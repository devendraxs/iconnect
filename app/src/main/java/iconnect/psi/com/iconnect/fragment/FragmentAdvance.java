package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import iconnect.psi.com.iconnect.R;

public class FragmentAdvance extends Fragment {
    private SeekBar seekbar;
    private TextView advanceAmountPer,totalAmount;
    int stepSize=5;
    private TextView estmPdm;
    private   int advAmount;
    private CheckBox check1,check2,check3,check4,check5;
    private int amount=1200;
    private int  calCheck2,calcheck3,calcheck4,calcheck5;
    private   int checkBoxAmount;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_advance,container,false);
        seekbar=view.findViewById(R.id.seekbar);
        advanceAmountPer=view.findViewById(R.id.advanceAmountPer);
        totalAmount=view.findViewById(R.id.totalAmount);

        estmPdm= view.findViewById(R.id.estmPdm);
        check1=view.findViewById(R.id.check1);
        check2=view.findViewById(R.id.check2);
        check3=view.findViewById(R.id.check3);
        check4=view.findViewById(R.id.check4);
        check5=view.findViewById(R.id.check5);

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
        check2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             calCheck2=(amount*15)/100;

        }
    });
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               calcheck3=(amount*25)/100;
            }
        });
        check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcheck4=(amount*40)/100;

            }
        });
        check5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 calcheck5=(1200*20)/100;
            }
        });
        checkBoxAmount= calCheck2+calcheck3+calcheck4+calcheck5;
        //checkBoxAmount=calCheck2+calcheck3+calcheck4+calcheck5;
        final String[] percent = { "0", "10", "20", "30","40","50","60","70"};

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
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

        return view;
    }
}
