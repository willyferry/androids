package com.example.volumebalok

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtLebar: EditText
    private lateinit var edtTinggi: EditText
    private lateinit var edtPanjang: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLebar = findViewById(R.id.edt_lebar)
        edtTinggi = findViewById(R.id.edt_tinggi)
        edtPanjang = findViewById(R.id.edt_panjang)
        btnHitung = findViewById(R.id.btn_hitung)
        tvHasil = findViewById(R.id.tv_hasil)

        btnHitung.setOnClickListener(this)

        if(savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT);
            tvHasil.text = result;
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_hitung){
            val inputPanjang = edtPanjang.text.toString().trim()
            val inputLebar = edtLebar.text.toString().trim()
            val inputTinggi = edtTinggi.text.toString().trim()

            var isEmptyFields = false
            when {
                inputPanjang.isEmpty() -> {
                    isEmptyFields = true
                    edtPanjang.error = "Field ini tidak boleh kosong!"
                }
                inputLebar.isEmpty() -> {
                    isEmptyFields = true
                    edtLebar.error = "Field ini tidak boleh kosong!"
                }
                inputTinggi.isEmpty() -> {
                    isEmptyFields = true
                    edtTinggi.error = "Field ini tidak boleh kosong!"
                }

            }

            if(!isEmptyFields){
                val volume = inputPanjang.toDouble() * inputLebar.toDouble() * inputTinggi.toDouble()
                tvHasil.text = volume.toString()
            }
        }
    }

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvHasil.text.toString())
    }
}