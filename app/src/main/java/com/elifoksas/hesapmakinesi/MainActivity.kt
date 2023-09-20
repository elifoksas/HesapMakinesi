package com.elifoksas.hesapmakinesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elifoksas.hesapmakinesi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentInput = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonClickListeners()
    }

    private fun setButtonClickListeners(){
        val buttons = arrayListOf(binding.button0,binding.button1,binding.button2,binding.button3,
            binding.button4,binding.button5,binding.button6,binding.button7,binding.button8,
            binding.button9,binding.buttonArti,binding.buttonEsittir,binding.buttonC)

        for(button in buttons){
            button.setOnClickListener {
                val buttonText = button.text.toString()
                handleButtonClick(buttonText)
            }
        }
        binding.buttonC.setOnClickListener {
            currentInput = ""
            operator = ""
            updateEditText()
        }
        binding.buttonDelete.setOnClickListener {
            if(currentInput.isNotEmpty()){
                currentInput = currentInput.dropLast(1)
                updateEditText()
            }
        }

        binding.buttonArti.setOnClickListener {
            currentInput += "+"
            operator = "+"
            updateEditText()
        }

        binding.buttonEsittir.setOnClickListener {
            if (currentInput.isEmpty() || !currentInput[0].isDigit()) {
                return@setOnClickListener
            }
            if (currentInput.isNotEmpty() && currentInput.last() == '+') {
                return@setOnClickListener
            }
            if (currentInput.isNotEmpty() && operator == "+") {
                val numbers = currentInput.split("+")
                var result = 0
                for (num in numbers){
                    result += num.toInt()
                }
                currentInput = result.toString()
                operator = ""
                updateEditText()
            }

        }


    }
    private fun handleButtonClick(buttonText: String) {
        currentInput += buttonText
        updateEditText()
    }
    private fun updateEditText() {
        binding.editTextText.setText(currentInput)
    }




}