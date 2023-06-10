package com.electricity.bill_calculate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUnitsUsed;
    private EditText editTextRebatePercentage;
    private TextView textViewResult;

    private Button aboutButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUnitsUsed = findViewById(R.id.editTextUnitsUsed);
        editTextRebatePercentage = findViewById(R.id.editTextRebatePercentage);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        aboutButton = findViewById(R.id.aboutButton);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });
        aboutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewPageActivity.class);
            startActivity(intent);
        });
    }

    private void calculateBill() {
        String unitsUsedString = editTextUnitsUsed.getText().toString();
        String rebatePercentageString = editTextRebatePercentage.getText().toString();

        if (unitsUsedString.isEmpty() || rebatePercentageString.isEmpty()) {
            textViewResult.setText("Please enter valid values.");
            return;
        }

        double unitsUsed = Double.parseDouble(unitsUsedString);
        double rebatePercentage = Double.parseDouble(rebatePercentageString);

        double totalCharges = calculateCharges(unitsUsed);
        double finalCost = totalCharges - (totalCharges * rebatePercentage);

        @SuppressLint("DefaultLocale") String result = String.format("Final Cost: RM %.2f", finalCost);
        textViewResult.setText(result);
    }

    private double calculateCharges(double unitsUsed) {
        double totalCharges = 0.0;

        if (unitsUsed <= 200) {
            totalCharges = unitsUsed * 0.218;
        } else if (unitsUsed <= 300) {
            totalCharges = 200 * 0.218 + (unitsUsed - 200) * 0.334;
        } else if (unitsUsed <= 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + (unitsUsed - 300) * 0.516;
        } else if (unitsUsed > 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (unitsUsed - 600) * 0.546;
        }

        return totalCharges;
    }



    //public void openNewPage(View view) {
    //  Intent intent = new Intent(this, NewPageActivity.class);
    //   startActivity(intent);
    //  }

}
