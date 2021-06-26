package com.ifsp.pdm.emanoela.dados

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ifsp.pdm.emanoela.dados.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var activitySettingsBinding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)

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
            finish()
        }

    }
}