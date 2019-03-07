package iconnect.psi.com.iconnect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        //Fragment fragment=null;
        if (position==0){
            return new FragmentItineary();
            //fragment=new FragmentItineary();
        }else if (position==1){
            return new FragmentPurpose();
           // fragment=new FragmentPurpose();
        }else if (position==2){
            return new FragmentPurpose();
            //fragment=new FragmentAdvance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
