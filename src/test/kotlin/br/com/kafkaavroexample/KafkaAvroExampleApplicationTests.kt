package br.com.kafkaavroexample

import br.com.kafkaavroexample.entity.Pessoa
import br.com.kafkaavroexample.producer.PessoaProducerImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.boot.SpringBootConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootConfiguration
class KafkaAvroExampleApplicationTests {

	@Mock
	lateinit var pessoaProducerImpl: PessoaProducerImpl

	@Test
	fun testProducer() {
		val pessoa = Pessoa("Jocimar", "Neres")
		pessoaProducerImpl.persiste("12345", pessoa)
		assertEquals("Jocimar", pessoa.nome)
		assertEquals("Neres", pessoa.sobrenome)
	}
}
