package com.example.lg_animal.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.lg_animal.R;


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
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root;
        switch (pageViewModel.getIndex().getValue()){
            case 0:
                root = inflater.inflate(R.layout.fragment_main_1, container, false);
                final TextView textView = root.findViewById(R.id.section_label);
                pageViewModel.getText().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        textView.setText(s);
                    }
                });
                break;
            case 1:
                root = inflater.inflate(R.layout.fragment_main_2, container, false);

                break;
            case 2:

                root = inflater.inflate(R.layout.fragment_main_3, container, false);
                break;
            case 3:
                root = inflater.inflate(R.layout.fragment_main_4, container, false);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new SettingsFragment())
                        .commit();
                break;
            default:
                root = inflater.inflate(R.layout.fragment_main_1, container, false);
                break;
        }
        return root;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            EditTextPreference signaturePreference = findPreference(getString(R.string.etpf_key_title1));
            if (signaturePreference != null) {
                signaturePreference.setVisible(true);
                Log.d("", "signaturePreference.getText() " + signaturePreference.getText());
            }


            ListPreference listPreference = findPreference(getString(R.string.list_key_1));
            if (signaturePreference != null) {
                listPreference.setVisible(true);
                Log.d("", "listPreference.getValue() " + listPreference.getValue());
            }
        }
    }
}