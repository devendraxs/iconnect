package iconnect.psi.com.iconnect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import iconnect.psi.com.iconnect.fragment.FragmentSave;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentSave fragmentSave=new FragmentSave();
                return fragmentSave;

            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;


        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
