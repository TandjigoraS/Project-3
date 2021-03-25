package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    final int mTabs;

    public ListNeighbourPagerAdapter(FragmentManager fm, int Tabs) {
        super(fm);
        this.mTabs = Tabs;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * {@inheritDoc}
     * @param  position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NeighbourFragment.newInstance();
        }else{
            return FavoriteListFragment.newInstance();
        }
    }

    /**
     * get the number of pages
     * @return mTabs
     */
    @Override
    public int getCount() {
        return mTabs;
    }
}