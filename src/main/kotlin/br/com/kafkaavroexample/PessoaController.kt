package br.com.kafkaavroexample

import br.com.kafkaavroexample.entity.Pessoa
import br.com.kafkaavroexample.producer.PessoaProducerImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PessoaController(private val pessoaProducerImpl: PessoaProducerImpl) {

    @PostMapping("/pessoas")
    fun criaPessoa(@RequestBody pessoa: Pessoa): ResponseEntity<String> {
        pessoaProducerImpl.persist("12345", pessoa)
        //Thread.sleep(5000)
        return ResponseEntity.ok("Pessoa criada com sucesso")
    }
}

