package com.example.lg_animal.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lg_animal.R;

public class PetManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_manager);

        initView();
    }

    private void initView() {
        GridView gvPetList = findViewById(R.id.gv_pet_info_manage_list);
        PetListAdapter adapter = new PetListAdapter();
        gvPetList.setAdapter(adapter);
    }

    private class PetListAdapter extends android.widget.BaseAdapter {

        public void PetListAdapter(Context context) {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
