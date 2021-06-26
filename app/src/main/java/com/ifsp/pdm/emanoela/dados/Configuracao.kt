package com.ifsp.pdm.emanoela.dados

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Configuracao(val numeroDados: Int = 1, val numberoFaces: Int = 6) : Parcelable
