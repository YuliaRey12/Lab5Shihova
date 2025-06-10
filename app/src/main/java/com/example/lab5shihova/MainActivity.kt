package com.example.lab5shihova

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.lab5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.okButton.setOnClickListener {
            val amountStr = binding.amountEditText.text.toString()
            val amount = amountStr.toDoubleOrNull() ?: 0.0

            val interestRate = when(binding.radioGroup.checkedRadioButtonId) {
                R.id.threeMonthsRadioButton -> 0.03
                R.id.sixMonthsRadioButton -> 0.05
                R.id.oneYearRadioButton -> 0.09
                else -> 0.0
            }

            val result = amount * (1 + interestRate)
            val resultText = "Итоговая сумма: %.2f рублей".format(result)

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("RESULT_TEXT", resultText)
            }
            startActivity(intent)
        }
    }
}