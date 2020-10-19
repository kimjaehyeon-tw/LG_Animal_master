package com.example.lg_animal.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lg_animal.R;
import com.example.lg_animal.data.PetInfoSharedPreference;

import java.util.ArrayList;

public class RoutineListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mRoutineSub;
    private PlaceholderFragment mFragment;

    private int[] mRoutineIcon = {R.drawable.ic_routine_weight, R.drawable.ic_routine_activity, R.drawable.ic_routine_food, R.drawable.ic_routine_water};
    private int[] mRoutineTitle = {R.string.routine_weight, R.string.routine_activity, R.string.routine_food, R.string.routine_water};

    public RoutineListAdapter(PlaceholderFragment fragment, Context context, String[] routineSub) {
        mFragment = fragment;
        mContext = context;
        mRoutineSub = routineSub;
    }

    @Override
    public int getCount() {
        return mRoutineIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gv_routine_item, null);
        }
        ImageView ivRoutineIcon = convertView.findViewById(R.id.iv_routine_icon_item);
        TextView tvRoutineTitle = convertView.findViewById(R.id.tv_routine_title);
        TextView tvRoutineSub = convertView.findViewById(R.id.tv_routine_sub);
        Button btnRoutineSetting = convertView.findViewById(R.id.btn_routine_item);

        ivRoutineIcon.setImageResource(mRoutineIcon[position]);
        tvRoutineTitle.setText(mContext.getString(mRoutineTitle[position]));
        tvRoutineSub.setText(mRoutineSub[position]);
        btnRoutineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.routineSettings(position);
            }
        });

        return convertView;
    }
}