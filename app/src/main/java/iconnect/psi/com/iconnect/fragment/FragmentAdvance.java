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
    private TextView advanceAmountPer,advanceAmount;
    int stepSize=5;
    private TextView estmPdm;
    private   int advAmount;
    private CheckBox check1,check2,check3,check4,check5;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_advance,container,false);
        seekbar=view.findViewById(R.id.seekbar);
        advanceAmountPer=view.findViewById(R.id.advanceAmountPer);


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


        final String[] percent = { "0", "10", "20", "30","40","50","60","70"};

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                progress=(progress/stepSize)*stepSize;
                //advanceAmount.setText(""+percent[progress]+"%");
                 advanceAmountPer.setText(""+progress+"%");
                bar.setMax(70);
                int amount=(1200*progress)/100;
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
