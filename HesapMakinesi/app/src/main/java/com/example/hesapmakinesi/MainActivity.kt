package com.example.hesapmakinesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.hesapmakinesi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {

            binding.button0.appendClick("0")
            binding.button1.appendClick("1")
            binding.button2.appendClick("2")
            binding.button3.appendClick("3")
            binding.button4.appendClick("4")
            binding.button5.appendClick("5")
            binding.button6.appendClick("6")
            binding.button7.appendClick("7")
            binding.button8.appendClick("8")
            binding.button9.appendClick("9")
            binding.buttonBolme.appendClick("/")
            binding.buttonCikarma.appendClick("-")
            binding.buttonToplama.appendClick("+")
            binding.buttonCarpma.appendClick("*")
            binding.buttonParantez.appendClick("(")
            binding.buttonParantez.setOnLongClickListener {
                binding.processEditText.append(")")
                true
            }
            binding.buttonNokta.appendClick(".")
            binding.buttonAC.setOnClickListener {
                binding.processEditText.text = null
                binding.resultText.text = null
            }

            binding.buttonSilme.setOnClickListener {
                val expression = processEditText.text.toString()
                if (expression.isNotEmpty()) {
                    processEditText.text = expression.substring(0, expression.length - 1)
                }
            }

            binding.buttonEsittir.setOnClickListener {
                try {
                    val expression =
                        ExpressionBuilder(binding.processEditText.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if (result == longResult.toDouble()) {
                        binding.resultText.text = longResult.toString()
                    } else {
                        binding.resultText.text = result.toString()
                    }
                } catch (e: Exception) {
                    Log.d("Exception", "Message: ${e.message}")
                }
            }
        }
    }


    fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processEditText.append(string)
        }
    }
}