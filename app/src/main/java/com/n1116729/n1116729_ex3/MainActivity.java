package com.n1116729.n1116729_ex3;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    protected EditText name;
    protected EditText age;
    protected CheckBox sport;
    protected CheckBox movie;
    protected CheckBox coding;
    protected CheckBox other;
    protected EditText otherText;
    protected RadioButton yes;
    protected RadioButton no;
    protected RadioGroup remind;
    protected MultiAutoCompleteTextView result;
    protected Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTextPersonName);
        age = (EditText) findViewById(R.id.editTextNumberSigned);
        sport = (CheckBox) findViewById(R.id.checkBox);
        movie = (CheckBox) findViewById(R.id.checkBox2);
        coding = (CheckBox) findViewById(R.id.checkBox3);
        other = (CheckBox) findViewById(R.id.checkBox5);
        otherText = (EditText) findViewById(R.id.editTextTextPersonName2);
        yes = (RadioButton) findViewById(R.id.radioButton6);
        no = (RadioButton) findViewById(R.id.radioButton8);
        remind = (RadioGroup) findViewById(R.id.radioGroup);
        result = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView2);
        vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // 姓名監聽器
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buildString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 年齡監聽器
        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buildString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sport.setOnCheckedChangeListener((compoundButton, b) -> buildString());

        movie.setOnCheckedChangeListener((compoundButton, b) -> buildString());

        coding.setOnCheckedChangeListener((compoundButton, b) -> buildString());

        // 其他監聽器
        other.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                otherText.setVisibility(View.VISIBLE);
            }
            else{
                otherText.setVisibility(View.INVISIBLE);
            }
            otherText.setText("");
            buildString();
        });

        otherText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buildString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        yes.setOnClickListener(view -> {
            vb.vibrate(5000);
        });

        no.setOnClickListener(view -> {
            vb.cancel();
        });
        
    }

    protected void buildString() {
        ArrayList<String> resultArray = new ArrayList<>();
        if (!name.getText().toString().matches("")){
            resultArray.add("我的姓名是");
            resultArray.add(String.valueOf(name.getText()));
            resultArray.add("\n");
        }
        if (!age.getText().toString().matches("")){
            resultArray.add("今年");
            resultArray.add(String.valueOf(age.getText()));
            resultArray.add("歲");
            resultArray.add("\n");
        }

        int resIndex = resultArray.size();

        HashMap<CheckBox,String> checkBoxMap = new HashMap<>();
        checkBoxMap.put(sport,sport.isChecked() ? "運動" : "");
        checkBoxMap.put(movie, movie.isChecked() ? "電影" : "");
        checkBoxMap.put(coding, coding.isChecked() ? "程式" : "");
        checkBoxMap.put(other, !otherText.getText().toString().matches("") ? String.valueOf(otherText.getText()): "");
        int checkBoxCounts = 0;
        for (CheckBox label : checkBoxMap.keySet()) {
            if (label.isChecked() && !Objects.requireNonNull(checkBoxMap.get(label)).matches("")) {
                checkBoxCounts++;
                if (checkBoxCounts > 1) {
                    resultArray.add(",");
                }
                resultArray.add(checkBoxMap.get(label));
            }
        }
        if (checkBoxCounts > 0) {
            resultArray.add(resIndex,"興趣是");
        }

        StringBuilder output = new StringBuilder();
        for (String data : resultArray) {
            output.append(data);
        }
        result.setText(output);
    }

}