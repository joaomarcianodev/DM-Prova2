package com.jams.consultarcep

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // A anotação @GET define o "caminho" do endpoint que queremos acessar
    // O {cep} no caminho será substituído pelo valor da variável 'cep' anotada com @Path
    @GET("ws/{cep}/json/")
    suspend fun getEndereco(@Path("cep") cep: String): Endereco
}
