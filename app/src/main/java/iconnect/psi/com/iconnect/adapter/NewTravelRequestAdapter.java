package iconnect.psi.com.iconnect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import iconnect.psi.com.iconnect.fragment.FragmentAdvance;
import iconnect.psi.com.iconnect.fragment.FragmentSummary;
import iconnect.psi.com.iconnect.fragment.FragmentItineary;
import iconnect.psi.com.iconnect.fragment.FragmentPurpose;

public class NewTravelRequestAdapter extends FragmentPagerAdapter {

    int tabCount;
    public NewTravelRequestAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount= tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new FragmentPurpose();
                break;
            case 1:

                fragment=new FragmentItineary();
                break;
            case 2:
                fragment=new FragmentAdvance();
                break;
            case 3:
                fragment=new FragmentSummary();
        }
       /* if (position==0){
            return new FragmentItineary();
            //fragment=new FragmentItineary();
        }else if (position==1){
            return new FragmentPurpose();
           // fragment=new FragmentPurpose();
        }else if (position==2){
            return new FragmentPurpose();
            //fragment=new FragmentAdvance();
        }*/
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
