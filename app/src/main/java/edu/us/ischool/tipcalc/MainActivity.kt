package edu.us.ischool.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editAmount = findViewById<EditText>(R.id.amount)
        val radioGroup = findViewById<RadioGroup>(R.id.opts_tip)
        val btnTip = findViewById<Button>(R.id.btn_tip)

        var tipRate = 0.15

        editAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    btnTip.isEnabled = true
                    val str = editAmount.text.toString()
                    val dotIndex = str.indexOf(".")
                    if (dotIndex != -1 && str.length - 3 > dotIndex) {
                        editAmount.setText(str.substring(0, dotIndex + 3))
                        editAmount.setSelection(editAmount.length())
                    }
                } else {
                    btnTip.isEnabled = false
                }
            }
        })

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radio = findViewById<RadioButton>(checkedId)
            tipRate = radio.text.toString().substring(0, radio.text.length - 1).toDouble() / 100
        }

        btnTip.setOnClickListener {
            val amount = editAmount.text.toString().toDouble()
            val tip = round(amount * tipRate * 100) / 100
            Toast.makeText(applicationContext, "$$tip", Toast.LENGTH_SHORT).show()
        }
    }
}