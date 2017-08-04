package com.olegsagenadatrytwo.pictureandemi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class EMIActivity extends AppCompatActivity {


    //declare all the variables
    private SeekBar seekBar;
    private TextView rate;
    private EditText loanAmount;
    private EditText numberOfYears;
    private Button calculate;
    private TextView monthlyPayment;
    private TextView totalPayment;
    private double interestRate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);

        //initialize and link and the variables
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        rate    = (TextView)findViewById(R.id.tvInterestRate);
        calculate = (Button) findViewById(R.id.btnCalculate);
        loanAmount = (EditText) findViewById(R.id.loanAmount);
        numberOfYears = (EditText) findViewById(R.id.numberOfYears);
        monthlyPayment = (TextView) findViewById(R.id.tvMonthlyPayment);
        totalPayment = (TextView) findViewById(R.id.tvTotalPayment);

        //implement the OnSeekBarChangeListener for the seekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //onProgressChanged method will be called when the seekBar is moved by the user
            //that means we can get the value of the seekBar here
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double decimalValue = ((double)i / 10.0);
                rate.setText(decimalValue+"");
                interestRate = decimalValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //this onClick method will calculate the EMI and display it to the user
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCalculate:
                //get the user Input that the user have typed in or selected with the seekBar
                double loanAmountNumber = Double.parseDouble(loanAmount.getText().toString());
                double numberOfyears = Double.parseDouble(numberOfYears.getText().toString());
                double interestRatePerMonth = (interestRate/(12*100));

                numberOfyears = numberOfyears * 12;
                //calculate the EMI
                double EMI = (loanAmountNumber*interestRatePerMonth*Math.pow(1+interestRatePerMonth,numberOfyears))/(Math.pow(1+interestRatePerMonth,numberOfyears)-1);
                //Display it to the user
                monthlyPayment.setText(EMI + "");
                double t = EMI * numberOfyears;
                totalPayment.setText(t + "");
                break;
        }
    }
}
