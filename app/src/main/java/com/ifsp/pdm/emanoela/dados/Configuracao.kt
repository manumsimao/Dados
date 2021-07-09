package com.ifsp.pdm.emanoela.dados

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Configuracao(var numeroDados: Int = 1, var numberoFaces: Int = 6) : Parcelable
