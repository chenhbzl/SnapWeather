package org.kevin.snapweather.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import org.kevin.snapweather.R;

/**
 * Created by thinkfeed#gmail.com on 13-5-24.
 */
public class WeatherLocationFragment extends Fragment implements View.OnClickListener {
    private ImageView mBack;
    private ImageView mSearch;
    private LinearLayout mInputLayout;
    private LinearLayout mCityLayout;
    private LinearLayout mSearchResultLayout;
    private EditText mInput;
    private ListView mCityList;

    private boolean isAddMode = false;

    public static WeatherLocationFragment newIntence(){
        WeatherLocationFragment weatherLocationFragment = new WeatherLocationFragment();
        return weatherLocationFragment;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_location,container,false);
        mBack = (ImageView)view.findViewById(R.id.fr_back);
        mSearch = (ImageView)view.findViewById(R.id.fr_search);
        mInput = (EditText)view.findViewById(R.id.fr_input);
        mInputLayout = (LinearLayout)view.findViewById(R.id.fr_input_location_layout);
        mCityLayout = (LinearLayout)view.findViewById(R.id.fr_city_list_layout);
        mSearchResultLayout = (LinearLayout)view.findViewById(R.id.fr_search_result_layout);
        mCityList = (ListView)view.findViewById(R.id.fr_list_city);
        mBack.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    isAddMode = true;
                }else{
                    isAddMode = false;
                    hideInputMethod(mInput);
                }
                refreshViews();
            }
        });
        mInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                hideInputMethod(mSearch);
                return false;
            }
        });
        return view;
    }
    private void refreshViews(){
        if (isAddMode){
            mCityLayout.setVisibility(View.GONE);
            mSearchResultLayout.setVisibility(View.VISIBLE);
            mBack.setVisibility(View.VISIBLE);
        }else{
            mInput.setText("");
            mInput.clearFocus();
            mCityLayout.setVisibility(View.VISIBLE);
            mSearchResultLayout.setVisibility(View.GONE);
            mBack.setVisibility(View.GONE);
        }
    }
    private void hideInputMethod(View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fr_back:
                isAddMode = false;
                refreshViews();
                hideInputMethod(mBack);
                break;
            case R.id.fr_search:

                break;

        }
    }
}
