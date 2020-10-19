package com.example.lg_animal.settings;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lg_animal.R;
import com.example.lg_animal.data.PetInfoSharedPreference;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PetListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private ImageView btnLeftArrow, btnRightArrow;

    public static PetListFragment newInstance(int index) {
        PetListFragment fragment = new PetListFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int petNum = pageViewModel.getIndex().getValue() + 1;

        StringBuffer name = new StringBuffer();
        name.append(getString(R.string.pet_name));
        name.append(" ");
        name.append(PetInfoSharedPreference.getString(getActivity(), "pet_name" + petNum));
        StringBuffer age = new StringBuffer();
        age.append(getString(R.string.pet_age));
        age.append(" ");
        age.append(PetInfoSharedPreference.getString(getActivity(), "pet_age" + petNum));
        StringBuffer gender = new StringBuffer();
        gender.append(getString(R.string.pet_gender));
        gender.append(" ");
        gender.append(PetInfoSharedPreference.getString(getActivity(), "pet_gender" + petNum));
        StringBuffer kind = new StringBuffer();
        kind.append(getString(R.string.pet_kind));
        kind.append(" ");
        kind.append(PetInfoSharedPreference.getString(getActivity(), "pet_kind" + petNum));
        StringBuffer weight = new StringBuffer();
        weight.append(getString(R.string.pet_weight));
        weight.append(" ");
        weight.append(PetInfoSharedPreference.getString(getActivity(), "pet_weight" + petNum));
        weight.append(getString(R.string.pet_weight_unit));

        View root = inflater.inflate(R.layout.fragment_pet_list, container, false);
        btnLeftArrow = root.findViewById(R.id.iv_arrow_left);
        btnRightArrow = root.findViewById(R.id.iv_arrow_right);
        if (pageViewModel.getIndex().getValue() == 0) {
            btnLeftArrow.setVisibility(GONE);
            btnRightArrow.setVisibility(VISIBLE);
        }
        if (PetInfoSharedPreference.getInt(getActivity(), "pet_number") -1 == pageViewModel.getIndex().getValue()) {
            btnRightArrow.setVisibility(GONE);
        } else {
            btnLeftArrow.setVisibility(VISIBLE);
            btnRightArrow.setVisibility(VISIBLE);
        }

        if (pageViewModel.getIndex().getValue() != null) {
            ((ImageView) root.findViewById(R.id.iv_pet_image)).
                    setImageURI(Uri.parse(PetInfoSharedPreference.getString(getActivity(), "pet_picture" + petNum)));
            ((TextView) root.findViewById(R.id.tv_pet_name)).setText(name);
            ((TextView) root.findViewById(R.id.tv_pet_age)).setText(age);
            ((TextView) root.findViewById(R.id.tv_pet_gender)).setText(gender);
            ((TextView) root.findViewById(R.id.tv_pet_kind)).setText(kind);
            ((TextView) root.findViewById(R.id.tv_pet_weiget)).setText(weight);
        }
        return root;
    }
}