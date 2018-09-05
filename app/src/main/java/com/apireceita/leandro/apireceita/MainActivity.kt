package com.apireceita.leandro.apireceita

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonId.setOnClickListener {

            val cnpjDigitado = editTextId.text.toString()

            if(cnpjDigitado == null || cnpjDigitado.equals("")){
                Toast.makeText(this, "Informe o CNPJ", Toast.LENGTH_SHORT).show()
            }else if(cnpjDigitado.length < 14 || cnpjDigitado.length > 14){
                Toast.makeText(this, "Tamanho Incorreto", Toast.LENGTH_SHORT).show()
            }else{

                val intent = Intent(this, SegundaActivity::class.java)
                intent.putExtra("cnpj", cnpjDigitado)
                startActivity(intent)
            }
        }
    }
}
