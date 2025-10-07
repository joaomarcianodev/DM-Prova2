package com.jams.consultarcep

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jams.consultarcep.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // Declaração do ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Criação da instância do Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/") // URL base da API
            .addConverterFactory(GsonConverterFactory.create()) // Conversor JSON
            .build()
    }

    // Criação do serviço da API a partir da instância do Retrofit
    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Infla o layout usando o ViewBinding e define como o conteúdo da tela
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o listener de clique para o botão
        setupButton()
    }

    private fun setupButton() {
        binding.btnBuscar.setOnClickListener {
            val cep = binding.etCep.text.toString()
            if (isValidCep(cep)) {
                buscarEndereco(cep)
            } else {
                Toast.makeText(this, "CEP inválido. Digite 8 números.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buscarEndereco(cep: String) {
        // Lança uma Coroutine no escopo do ciclo de vida da Activity
        lifecycleScope.launch {
            try {
                // Chama a função da API (que é 'suspend')
                val endereco = apiService.getEndereco(cep)

                // Checa se a resposta indicou um erro ou se a cidade é nula
                if (endereco.erro == true || endereco.cidade == null) {
                    // Lança uma exceção para cair no bloco catch e tratar como erro
                    throw Exception("CEP não encontrado.")
                }

                // Se a verificação passar, atualiza a UI com o resultado
                val resultado = """
                Logradouro: ${endereco.logradouro}
                Bairro: ${endereco.bairro}
                Cidade: ${endereco.cidade}
                Estado: ${endereco.estado}
            """.trimIndent()

                binding.tvResultado.text = resultado

            } catch (e: Exception) {
                // Em caso de erro (ex: sem internet, CEP não encontrado ou cidade nula)
                binding.tvResultado.text = "Não foi possível encontrar o endereço."
                Toast.makeText(this@MainActivity, "Erro: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isValidCep(cep: String): Boolean {
        // Validação simples: verifica se o CEP tem 8 dígitos numéricos
        return cep.length == 8 && cep.all { it.isDigit() }
    }
}
