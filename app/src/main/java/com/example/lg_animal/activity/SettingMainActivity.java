package com.example.lg_animal.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.lg_animal.settings.SectionsPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.example.lg_animal.R;

public class SettingMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = SettingMainActivity.class.getName();
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);
        init();
        initViews();
        setToolbarSetting();
    }

    private void init(){
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons(tabs);
    }

    private void initViews() {
        mDrawerLayout = findViewById(R.id.dl_main_drawer);
        NavigationView navigationView = findViewById(R.id.nv_main_navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setToolbarSetting() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu) ;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.action_walk:
                break;
            case R.id.action_sync:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawers();
        return true;
    }

    private int[] mTabIconArray = {R.drawable.ic_tab_home, R.drawable.ic_tab_routine, R.drawable.ic_tab_community, R.drawable.ic_tab_shopping};
    private int[] mTabTextArray = {R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};

    private void setupTabIcons(TabLayout tabs) {
        for (int i = 0; i < mTabIconArray.length; i++) {
            View viewFirst = getLayoutInflater().inflate(R.layout.custom_tab, null);
            ImageView imgFirst = viewFirst.findViewById(R.id.img_tab);
            TextView txtFirst = viewFirst.findViewById(R.id.txt_tab);
            imgFirst.setImageResource(mTabIconArray[i]);
            txtFirst.setText(getString(mTabTextArray[i]));
            tabs.getTabAt(i).setCustomView(viewFirst);
        }
    }
}