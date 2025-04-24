package com.example.minecraft123000

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val duracaoMateriais = mapOf (
        "Madeira" to 100,
        "Ouro" to 50,
        "Diamante" to 25
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.mine_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.mine_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }

        val btn_CalcularTempo = findViewById<Button>(R.id.btn_CalcularTempo)
        btn_CalcularTempo.setOnClickListener{
          calcular()
        }

    }

    fun calcular() {
        val tv_resultado = findViewById<TextView>(R.id.tv_resul)
        val material = findViewById<Spinner>(R.id.mine_spinner)
        val m = material.selectedItem.toString()
        val numero = duracaoMateriais[m] ?: 0
        val construtor = findViewById<EditText>(R.id.edt_numero).text.toString()

            if (construtor.isNotEmpty()) {
                val construtor = construtor.toInt()
                tv_resultado.text = ""

                if (construtor == 1) {
                    val total = numero / construtor
                    tv_resultado.text = "$m e $total"
                }
                else{
                    var total = numero / 2;
                    for (i in 1 until (construtor-1)) {
                        total = total / 2
                    }
                    tv_resultado.text = "Tempo estimado de construção: $total horas."
                }

            } else {
                tv_resultado.text = "Por favor, insira um número."
            }
        }
    }

