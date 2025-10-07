package com.jams.consultarcep

// Usamos @SerializedName para mapear os nomes do JSON para os nomes das nossas variáveis,
// caso eles sejam diferentes. Neste caso, são iguais, mas é uma boa prática.
import com.google.gson.annotations.SerializedName

data class Endereco(
    @SerializedName("cep") val cep: String?,
    @SerializedName("logradouro") val logradouro: String?,
    @SerializedName("bairro") val bairro: String?,
    @SerializedName("localidade") val cidade: String?,
    @SerializedName("uf") val estado: String?,
    val erro: Boolean? = null
)