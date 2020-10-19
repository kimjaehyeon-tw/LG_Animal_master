package com.example.lg_animal.settings;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lg_animal.R;
import com.example.lg_animal.activity.SettingMainActivity;
import com.example.lg_animal.data.PetInfoSharedPreference;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PetListPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public PetListPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PetListFragment.newInstance(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(PetInfoSharedPreference.getInt(mContext, "pet_number"));
    }

    @Override
    public int getCount() {
        return PetInfoSharedPreference.getInt(mContext, "pet_number");
    }
}