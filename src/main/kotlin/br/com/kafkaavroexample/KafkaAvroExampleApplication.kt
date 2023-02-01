package br.com.kafkaavroexample

import br.com.kafkaavroexample.entity.Pessoa
import br.com.kafkaavroexample.producer.PessoaProducerImpl
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaAvroExampleApplication (
	val pessoaProducerImpl: PessoaProducerImpl
): ApplicationRunner{
	override fun run(args: ApplicationArguments?) {
		val pessoa = Pessoa("Jocimar", "Neres")
		Thread.sleep(5000)
		pessoaProducerImpl.persist("12345", pessoa)
	}

}

fun main(args: Array<String>) {
	runApplication<KafkaAvroExampleApplication>(*args)
}
