package iconnect.psi.com.iconnect.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import iconnect.psi.com.iconnect.R;

public class FragmentApprovedTravelRequest extends Fragment {
    private EditText comment;
    private RadioButton accept,reject;
    private RadioGroup rgGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_approved_travel_request,container,false);
        comment=view.findViewById(R.id.comment);
        accept=view.findViewById(R.id.accept);
        reject=view.findViewById(R.id.reject);
        rgGroup=view.findViewById(R.id.rgGroup);

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId==R.id.reject){
                    comment.setVisibility(view.VISIBLE);
                }else if (checkedId==R.id.accept){
                    comment.setVisibility(view.INVISIBLE);
                }
            }
        });
         return view;
    }
}
