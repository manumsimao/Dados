package com.ifsp.pdm.emanoela.dados

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ifsp.pdm.emanoela.dados.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var activitySettingsBinding: ActivitySettingsBinding

    private lateinit var configuracoesSharedPreferences: SharedPreferences
    var sharedPreferences = SharedPreferencesConstantes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)

        configuracoesSharedPreferences = getSharedPreferences(sharedPreferences.CONFIGURACOES_ARQUIVO, MODE_PRIVATE)
        carregaConfiguracoes()

        activitySettingsBinding.salvarBt.setOnClickListener {
            val numeroDados: Int =
                (activitySettingsBinding.numeroDadosSp.selectedView as TextView).text.toString()
                    .toInt()
            val facesTexto = activitySettingsBinding.numeroFacesEt.text.toString()
            val numeroFaces: Int = if(facesTexto == ""){
                6
            } else {
                facesTexto.toInt()
            }
            val configuracao: Configuracao = Configuracao(numeroDados, numeroFaces)
            val retornoIntent: Intent = Intent()
            retornoIntent.putExtra(Intent.EXTRA_USER, configuracao)
            setResult(RESULT_OK, retornoIntent)

            salvaConfiguracoes(numeroDados, numeroFaces)

            finish()
        }
    }
    private fun carregaConfiguracoes() {
        val numeroDados : Int = configuracoesSharedPreferences.getInt(sharedPreferences.NUMERO_DADOS_ATRIBUTO, sharedPreferences.VALOR_NAO_ENCONTRADO)
        val numeroFaces : Int = configuracoesSharedPreferences.getInt(sharedPreferences.NUMERO_FACES_ATRIBUTO, sharedPreferences.VALOR_NAO_ENCONTRADO)
        if(numeroDados != sharedPreferences.VALOR_NAO_ENCONTRADO){
            activitySettingsBinding.numeroDadosSp.setSelection(numeroDados - 1)
        }
        if(numeroFaces != sharedPreferences.VALOR_NAO_ENCONTRADO) {
            activitySettingsBinding.numeroFacesEt.setText(numeroFaces.toString())
        }
    }

    private fun salvaConfiguracoes(numeroDados : Int, numeroFaces: Int) {
        val editorSharedPreferences = configuracoesSharedPreferences.edit()
        editorSharedPreferences.putInt(sharedPreferences.NUMERO_DADOS_ATRIBUTO, numeroDados)
        editorSharedPreferences.putInt(sharedPreferences.NUMERO_FACES_ATRIBUTO, numeroFaces)
        editorSharedPreferences.commit()
    }
}