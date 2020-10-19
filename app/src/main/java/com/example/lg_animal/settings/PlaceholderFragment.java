package com.example.lg_animal.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.lg_animal.R;
import com.example.lg_animal.activity.RoutineSettingActivity;
import com.example.lg_animal.data.PetInfoSharedPreference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root;
        switch (pageViewModel.getIndex().getValue()){
            case 0:
                root = inflater.inflate(R.layout.fragment_main_1, container, false);
                initViewFragmentHome(root);
                break;
            case 1:
                root = inflater.inflate(R.layout.fragment_main_2, container, false);
                initViewFragmentRoutine(root);
                break;
            case 2:
                root = inflater.inflate(R.layout.fragment_main_3, container, false);
                break;
            case 3:
            default:
                root = inflater.inflate(R.layout.fragment_main_4, container, false);
                break;
        }
        return root;
    }

    private int[] mPetHealth = {0, 0, 1, 2, 0};

    private String petHealthCheck(int position) {
        String health = "";
        switch (position) {
            case 0:
                health = getString(R.string.pet_health_ok);
                break;
            case 1:
                health = getString(R.string.pet_health_caution);
                break;
            case 2:
                health = getString(R.string.pet_health_not);
                break;
        }
        return health;
    }

    private int setPetHealthIcon(int position) {
        int healthIcon;
        switch (position) {
            case 1:
                healthIcon = R.drawable.ic_health_caution;
                break;
            case 2:
                healthIcon = R.drawable.ic_health_not;
                break;
            case 0:
            default:
                healthIcon = R.drawable.ic_health_light;
                break;
        }
        return healthIcon;
    }

    private void initViewFragmentHome(View view) {
        int petPosition = PetInfoSharedPreference.getInt(getContext(), "pet_position") + 1;
        ImageView petImage = view.findViewById(R.id.pet_img_fragment);
        TextView petKind = view.findViewById(R.id.pet_kind_fragment);
        TextView petHealth = view.findViewById(R.id.pet_health_fragment);
        ImageView petHealthImg = view.findViewById(R.id.pet_health_img_fragment);

        StringBuffer kind = new StringBuffer();
        kind.append(getString(R.string.pet_kind));
        kind.append(" ");
        kind.append(PetInfoSharedPreference.getString(getActivity(), "pet_kind" + petPosition));
        StringBuffer health = new StringBuffer();
        health.append(getString(R.string.pet_health));
        health.append(" ");
        health.append(petHealthCheck(mPetHealth[petPosition]));

        petKind.setText(kind);
        petImage.setImageURI(Uri.parse(PetInfoSharedPreference.getString(getActivity(), "pet_picture" + petPosition)));
        petHealth.setText(health);
        petHealthImg.setImageResource(setPetHealthIcon(mPetHealth[petPosition - 1]));
    }

    private String[] routineSub1 = {"5kg, 3", "활동지수 100", "수량 120g", "수량 210ml"};
    private String[] routineSub2 = {"10kg, 7", "활동지수 60", "수량 150g", "수량 300ml"};
    private String[] routineSub3 = {"7kg, 5", "활동지수 90", "수량 140g", "수량 250ml"};
    private String[] routineSub4 = {"8kg, 6", "활동지수 70", "수량 130g", "수량 150ml"};
    private String[] routineSub5 = {"4kg, 3", "활동지수 100", "수량 110g", "수량 100ml"};

    private void initViewFragmentRoutine(View view) {
        GridView gvRoutine = view.findViewById(R.id.gv_routine_list);
        RoutineListAdapter routineAdapter = new RoutineListAdapter(this, getContext(), routineSubSetting());
        gvRoutine.setAdapter(routineAdapter);
    }

    private String[] routineSubSetting() {
        String[] strArrays;
        switch (PetInfoSharedPreference.getInt(getActivity(), "pet_position")) {
            case 1:
                strArrays = routineSub2;
                break;
            case 2:
                strArrays = routineSub3;
                break;
            case 3:
                strArrays = routineSub4;
                break;
            case 4:
                strArrays = routineSub5;
                break;
            case 0:
            default:
                strArrays = routineSub1;
                break;
        }
        return strArrays;
    }

        public void routineSettings(int position) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), RoutineSettingActivity.class);

        getActivity().startActivity(intent);
    }
}