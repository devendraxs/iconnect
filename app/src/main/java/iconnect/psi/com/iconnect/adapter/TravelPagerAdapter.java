package iconnect.psi.com.iconnect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import iconnect.psi.com.iconnect.fragment.FragmentArrroved;
import iconnect.psi.com.iconnect.fragment.FragmentComplete;
import iconnect.psi.com.iconnect.fragment.FragmentReject;
import iconnect.psi.com.iconnect.fragment.FragmentSave;
import iconnect.psi.com.iconnect.fragment.FragmentSubmit;

public class TravelPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    public TravelPagerAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new FragmentSave();
                break;
            case 1:
                fragment=new FragmentSubmit();
                break;
            case 2:
                fragment=new FragmentReject();
                break;
            case 3:
                fragment=new FragmentArrroved();
                break;
            case 4:
                fragment=new FragmentComplete();
                break;
        }
         return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
