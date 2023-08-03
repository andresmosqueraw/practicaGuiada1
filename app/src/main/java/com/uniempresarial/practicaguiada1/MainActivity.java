package com.uniempresarial.practicaguiada1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView tvInterestCalculation;
    private EditText etAmount;
    private EditText etInterestRate;
    private TextView tvDays;
    private SeekBar seekBarDays;
    private TextView tvSelectedDays;
    private Button btnCalculate;
    private Button btnClear;
    private Button btnPrint;
    private TextView total_payment;
    private double payment = 0.0;
    private double amount;
    private double interestRate;
    private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeVariables();

        tvDays.setText(seekBarDays.getProgress() + "/" + seekBarDays.getMax());

        seekBarDays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progressValue = (progressValue / 5) * 5;
                seekBarDays.setProgress(progressValue);
                tvDays.setText(progressValue + "/" + seekBarDays.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etInterestRate.setText("");
                seekBarDays.setProgress(0);
                payment = 0.0;
                total_payment.setText("");
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountString = etAmount.getText().toString();
                String interestRateString = etInterestRate.getText().toString();
                days = seekBarDays.getProgress();

                try {
                    amount = Double.parseDouble(amountString);
                    interestRate = Double.parseDouble(interestRateString);

                    double interest = (amount * interestRate * days) / (100 * 365);
                    payment = amount + interest;

                    total_payment.setText(String.format("Pago total: %.2f", payment));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese valores válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnPrint.setOnClickListener(v -> {
            if (payment != 0.0) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("amount", amount);
                intent.putExtra("interestRate", interestRate);
                intent.putExtra("days", days);
                intent.putExtra("result", payment);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "No se puede imprimir porque el pago total está vacío", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initializeVariables() {
        tvInterestCalculation = findViewById(R.id.tv_interest_calculation);
        etAmount = findViewById(R.id.et_amount);
        etInterestRate = findViewById(R.id.et_interest_rate);
        tvDays = findViewById(R.id.tv_days);
        seekBarDays = findViewById(R.id.seekBar_days);
        tvSelectedDays = findViewById(R.id.tv_selectedDays);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnClear = findViewById(R.id.btn_clear);
        btnPrint = findViewById(R.id.btn_print);
        total_payment = findViewById(R.id.tv_total_payment);
    }



}