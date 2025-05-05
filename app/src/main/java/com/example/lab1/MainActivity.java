package com.example.lab1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private RadioGroup radioGroupOperations;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація елементів інтерфейсу
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        radioGroupOperations = findViewById(R.id.radioGroupOperations);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Обробник натискання кнопки
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        // Отримання введених даних
        String num1Str = editTextNumber1.getText().toString().trim();
        String num2Str = editTextNumber2.getText().toString().trim();

        // Перевірка, чи всі поля заповнені
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            showAlertDialog("Будь ласка, заповніть обидва числові поля");
            return;
        }

        // Перевірка, чи вибрана операція
        int selectedOperationId = radioGroupOperations.getCheckedRadioButtonId();
        if (selectedOperationId == -1) {
            showAlertDialog("Будь ласка, виберіть операцію");
            return;
        }

        try {
            // Парсинг чисел
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            // Визначення вибраної операції
            RadioButton selectedRadioButton = findViewById(selectedOperationId);
            String operation = selectedRadioButton.getText().toString();

            // Виконання обраної операції
            switch (operation) {
                case "Додавання":
                    result = num1 + num2;
                    break;
                case "Віднімання":
                    result = num1 - num2;
                    break;
                case "Множення":
                    result = num1 * num2;
                    break;
                case "Ділення":
                    if (num2 == 0) {
                        showAlertDialog("Ділення на нуль неможливе");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            // Виведення результату
            textViewResult.setText("Результат: " + result);

        } catch (NumberFormatException e) {
            showAlertDialog("Будь ласка, введіть коректні числа");
        }
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  // Убедитесь, что используете 'this' для Activity
        builder.setTitle("Увага")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}