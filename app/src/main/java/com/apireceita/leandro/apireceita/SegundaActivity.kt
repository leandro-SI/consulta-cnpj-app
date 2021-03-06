package com.apireceita.leandro.apireceita

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_segunda.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class SegundaActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val dado: Bundle = intent.extras
        val cnpjRecebido = dado.getString("cnpj")
        val endereco = "https://www.receitaws.com.br/v1/cnpj/" + cnpjRecebido

        val url = endereco

            AsyncTaskHandleJson().execute(url)
    }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>(){


        override fun doInBackground(vararg url: String?): String {

            var text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() }}
            }finally {
                connection.disconnect()

            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }

    }

    private fun handleJson(jsonString: String?){

        val jsonObject = JSONObject(jsonString)

        val status = jsonObject.getString("status")

        if(status == "OK"){

            val dataSituacao = jsonObject.getString("data_situacao")
            val nome = jsonObject.getString("nome")
            val uf = jsonObject.getString("uf")
            val telefone = jsonObject.getString("telefone")
            val situacao = jsonObject.getString("situacao")
            val bairro = jsonObject.getString("bairro")
            val logradouro = jsonObject.getString("logradouro")
            val numero = jsonObject.getString("numero")
            val cep = jsonObject.getString("cep")
            val municipio = jsonObject.getString("municipio")
            val abertura = jsonObject.getString("abertura")



            var atividadePrincipal = getAtividadePrincipal(jsonObject, jsonObject.getJSONArray("atividade_principal"))
            var atividadesSecundarias = getAtividadesSecundarias(jsonObject, jsonObject.getJSONArray("atividades_secundarias"))
            var quadroDeSocios = getQuadroSocios(jsonObject, jsonObject.getJSONArray("qsa"))


            var string = "- ATIVIDADE PRINCIPAL - \n\n"

            atividadePrincipal.forEach {
                string += "Descrição: ${it.text}.\n" +
                        "Código: ${it.code}."
            }

            string += "\n\n"

            string += "Data Situação: ${dataSituacao}.\n" +
                    "Nome: ${nome}.\n" +
                    "Uf: ${uf}.\n" +
                    "Telefone: ${telefone}."

            string += "\n\n"

            string += "- ATIVIDADES SECUNDÁRIAS -\n\n"

            atividadesSecundarias.forEach {
                string += "Descrição: ${it.text}.\n" +
                        "Código: ${it.code}.\n"

            }

            string += "\n\n"
            string += "- QUADRO DE SÓCIOS -\n\n"

            quadroDeSocios.forEach {
                string += "Qualificação: ${it.qual}.\n" +
                        "Nome: ${it.nome}.\n"
            }

            string += "\n\n"
            string += "- OUTROS -\n\n"

            string += "Situação: ${situacao}.\n" +
                    "Bairro: ${bairro}.\n" +
                    "Logradouro: ${logradouro}.\n" +
                    "Numero: ${numero}.\n" +
                    "Cep: ${cep}.\n" +
                    "Município: ${municipio}.\n" +
                    "Data de Abertura: ${abertura}.\n"

            textViewId.text = string

        }else{

            Toast.makeText(applicationContext, "CNPJ Não encontrado", Toast.LENGTH_LONG).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getAtividadesSecundarias(jsonObject: JSONObject, jsonArray: JSONArray): ArrayList<AtvSecundarias>{

        var atividades = jsonObject
        var arrayAtividades = jsonArray
        val atividadesSecundarias = ArrayList<AtvSecundarias>()

        var x = 0
        while(x < jsonArray.length()){
            val objeto = jsonArray.getJSONObject(x)
            atividadesSecundarias.add(AtvSecundarias(
                    objeto.getString("text"),
                    objeto.getString("code")
            ))
            x++
        }
        return atividadesSecundarias
    }


    private fun getAtividadePrincipal(jsonObject: JSONObject, jsonArray: JSONArray): ArrayList<AtvPrincipal>{

        var atividades = jsonObject
        var arrayAtividades = jsonArray
        val atividadePrincipal = ArrayList<AtvPrincipal>()

        var x = 0
        while(x < jsonArray.length()){
            val objeto = jsonArray.getJSONObject(x)
            atividadePrincipal.add(AtvPrincipal(
                    objeto.getString("text"),
                    objeto.getString("code")
            ))
            x++
        }
        return atividadePrincipal
    }

    private fun getQuadroSocios(jsonObject: JSONObject, jsonArray: JSONArray): ArrayList<QuadroSocios>{

        var quadros = jsonObject
        var arrayQuadros = jsonArray
        val quadrosDeSocios = ArrayList<QuadroSocios>()

        var x = 0
        while(x < jsonArray.length()){
            val objeto = jsonArray.getJSONObject(x)
            quadrosDeSocios.add(QuadroSocios(
                    objeto.getString("qual"),
                    objeto.getString("nome")
            ))
            x++
        }
        return quadrosDeSocios

    }

}
