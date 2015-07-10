package com.felipekunzler.greencalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Felipe on 07/07/2015.
 */
public class TabResultFragment extends  Fragment{
    
    public static double energy = 0;
    public static double lgp = 0;
    public static double sewer = 0;
    public static double organicWaste = 0;
    public static double ngv = 0;
    public static double gasoline = 0;
    public static double ethanol = 0;
    public static double diesel = 0;

    Integer editTextsInputTab2[] = {
            R.id.editNGV, R.id.editTextGasoline, R.id.editTextEthanol, R.id.editTextDiesel};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_result,container,false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (Integer id : editTextsInputTab2){
            EditText editText = (EditText) getActivity().findViewById(id);
            editText.addTextChangedListener(new CustomTextWatcher(editText));
        }

        RadioButton radioButtonGuapuruvu = (RadioButton) getActivity().findViewById(R.id.radioButtonGuapuruvu);
        radioButtonGuapuruvu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RefreshNumberTrees(false);
            }
        });

        RefreshNumberTrees(true);
    }

    private class CustomTextWatcher implements TextWatcher {
        private EditText mEditText;

        public CustomTextWatcher(EditText e) {
            mEditText = e;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {
            RefreshNumberTrees(false);
        }
    }

    public void RefreshNumberTrees(boolean updateTab1){
        TextView textViewnumberTrees = null;
        try {
            textViewnumberTrees = (TextView) getActivity().findViewById(R.id.textViewNumberTrees);
        }
        catch (Exception e){

        }

        if (textViewnumberTrees != null) {
            if (updateTab1){
                energy = GetDoubleFromEditText(R.id.editTextEnergy);
                lgp = GetDoubleFromEditText(R.id.editTextLPG);
                sewer = GetDoubleFromEditText(R.id.editTextSewer);
                organicWaste = GetDoubleFromEditText(R.id.editTextOrganicWaste);
            }

            ngv = GetDoubleFromEditText(R.id.editNGV);
            gasoline = GetDoubleFromEditText(R.id.editTextGasoline);
            ethanol = GetDoubleFromEditText(R.id.editTextEthanol);
            diesel = GetDoubleFromEditText(R.id.editTextDiesel);

            double kgCo2Tree = 0;
            double numTrees = 0;

            RadioButton radioButtonGuapuruvu = (RadioButton) getActivity().findViewById(R.id.radioButtonGuapuruvu);
            if (radioButtonGuapuruvu.isChecked()) {
                kgCo2Tree = 149.42;
            } else {
                kgCo2Tree = 155.38;
            }

            numTrees += ((energy * 0.035833) / kgCo2Tree);
            numTrees += ((lgp * 2.93) / kgCo2Tree);
            numTrees += ((organicWaste * 0.6904) / kgCo2Tree);
            numTrees += ((sewer * 0.00283) / kgCo2Tree);
            numTrees += ((ngv * 2.06) / kgCo2Tree);
            numTrees += ((gasoline * 1.7809) / kgCo2Tree);
            numTrees += ((diesel * 2.606) / kgCo2Tree);
            numTrees += ((ethanol * 0.49) / kgCo2Tree);

            int roundNumTrees = (int) (numTrees + 0.5);

            if (roundNumTrees <= 999) {
                textViewnumberTrees.setText(Integer.toString(roundNumTrees));
            } else {
                textViewnumberTrees.setText("+999");
            }
        }
    }

    private double GetDoubleFromEditText(int id){
        EditText editText = (EditText) getActivity().findViewById(id);
        String text = editText.getText().toString();

        return "".equals(text) ? 0 : Double.parseDouble(text);
    }
}
