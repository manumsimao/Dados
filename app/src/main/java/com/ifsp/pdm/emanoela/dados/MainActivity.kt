package com.ifsp.pdm.emanoela.dados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ifsp.pdm.emanoela.dados.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var geradorRandomico: Random
    private var config: Configuracao = Configuracao()

    private lateinit var settingsActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        geradorRandomico = Random(System.currentTimeMillis())

        activityMainBinding.jogarDadoBt.setOnClickListener {
            val numeroDados = config.numeroDados
            val numeroFaces = config.numberoFaces

            if (numeroDados == 2) {

                val resultado1: Int = geradorRandomico.nextInt(1..numeroFaces)
                val resultado2: Int = geradorRandomico.nextInt(1..numeroFaces)

                if (numeroFaces < 7) {
                    activityMainBinding.resultado2Iv.visibility = VISIBLE

                    val nomeImagem1 = "dice_${resultado1}"
                    val nomeImagem2 = "dice_${resultado2}"

                    activityMainBinding.resultado1Iv.setImageResource(
                        resources.getIdentifier(nomeImagem1, "mipmap", packageName)
                    )

                    activityMainBinding.resultado2Iv.setImageResource(
                        resources.getIdentifier(nomeImagem2, "mipmap", packageName)
                    )
                } else {
                    activityMainBinding.resultado1Iv.visibility = GONE
                }
                "As faces sorteada foram $resultado1 e $resultado2".also {
                    activityMainBinding.resultadoTv.text = it
                }
            } else {
                activityMainBinding.resultado2Iv.visibility = GONE
                val resultado: Int = geradorRandomico.nextInt(1..numeroFaces)

                "A face sorteada foi $resultado".also { activityMainBinding.resultadoTv.text = it }

                if (numeroFaces < 7) {
                    activityMainBinding.resultado1Iv.visibility = VISIBLE

                    val nomeImagem = "dice_${resultado}"

                    activityMainBinding.resultado1Iv.setImageResource(
                        resources.getIdentifier(nomeImagem, "mipmap", packageName)
                    )
                } else {
                    activityMainBinding.resultado1Iv.visibility = GONE
                }
            }
        }

        settingsActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    if (result.data != null) {
                        val configuracao: Configuracao? =
                            result.data?.getParcelableExtra<Configuracao>(Intent.EXTRA_USER)
                        if (configuracao != null) {
                            config = configuracao
                        }
                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingMi) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsActivityLauncher.launch(settingsIntent)
            return true
        }
        return false
    }
}