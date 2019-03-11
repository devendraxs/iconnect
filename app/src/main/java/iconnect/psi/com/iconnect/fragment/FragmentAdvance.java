package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import iconnect.psi.com.iconnect.R;

public class FragmentAdvance extends Fragment {
    private SeekBar seekbar;
    private TextView advanceAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_advance,container,false);
        seekbar=view.findViewById(R.id.seekbar);
        advanceAmount=view.findViewById(R.id.advanceAmount);

        final String[] percent = { "0", "10", "20", "30","40","50","60","70"};

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                advanceAmount.setText(""+percent[progress]+"%");

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
