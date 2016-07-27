package com.example.maxim.p0731preferencesenable;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class PrefActivity extends PreferenceActivity {

    CheckBoxPreference chb3;
    PreferenceCategory categ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.xml);

        chb3 = (CheckBoxPreference) findPreference("chb3");
        categ2  = (PreferenceCategory) findPreference("categ2");
        categ2.setEnabled(chb3.isChecked());

        chb3.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(chb3.isChecked());
                return false;
            }
        });
    }
}