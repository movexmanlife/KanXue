package com.pediy.kanxue.ui.setting;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.pediy.kanxue.BaseFragment;
import com.pediy.kanxue.R;

import javax.inject.Inject;

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener{
    @Inject
    Context mContext;

    private ListPreference mPreferenceFontSize;  // 字体大小
    private ListPreference mPreferenceThreadSort;  // 阅读排序方式

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mPreferenceFontSize = (ListPreference) findPreference(getString(R.string.preference_key_font_size));
        mPreferenceFontSize.setOnPreferenceChangeListener(this);
        setListSetting(Integer.parseInt(prefs.getString(getString(R.string.preference_key_font_size), "4")), R.array.fontSize,
                mPreferenceFontSize);

        mPreferenceThreadSort = (ListPreference) findPreference(getString(R.string.preference_key_read));
        mPreferenceThreadSort.setOnPreferenceChangeListener(this);
        setListSetting(Integer.parseInt(prefs.getString(getString(R.string.preference_key_read), "0")), R.array.sortType,
                mPreferenceThreadSort);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (TextUtils.equals(getString(R.string.preference_key_font_size), preference.getKey())) {
            setListSetting(Integer.parseInt(newValue.toString()), R.array.fontSize, mPreferenceFontSize);
        } else if (TextUtils.equals(getString(R.string.preference_key_read), preference.getKey())) {
            setListSetting(Integer.parseInt(newValue.toString()), R.array.sortType, mPreferenceThreadSort);
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return true;
    }

    protected void setListSetting(int indexValue, int resArrayId, ListPreference listPreference) {
        String[] valueTitleArr = getResources().getStringArray(resArrayId);
        listPreference.setSummary(valueTitleArr[indexValue]);
    }
}
