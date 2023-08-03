package com.uniempresarial.practicaguiada1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        double amount = getIntent().getDoubleExtra("amount", 0.0);
        double interestRate = getIntent().getDoubleExtra("interestRate", 0.0);
        int days = getIntent().getIntExtra("days", 0);
        double result = getIntent().getDoubleExtra("result", 0.0);

        TextView tvAmount = findViewById(R.id.tv_amount);
        TextView tvInterestRate = findViewById(R.id.tv_interest_rate);
        TextView tvDays = findViewById(R.id.tv_days);
        TextView tvResult = findViewById(R.id.tv_result);

        tvAmount.setText("Cantidad de dinero: " + amount);
        tvInterestRate.setText("Tasa de interés: " + interestRate + "%");
        tvDays.setText("Número de días: " + days);
        tvResult.setText("El monto total con intereses es: " + result);
    }

    public void onBackButtonClick(View view) {
        finish();
    }
}
