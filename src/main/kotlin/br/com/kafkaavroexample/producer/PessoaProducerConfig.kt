package br.com.kafkaavroexample.producer

import br.com.kafkaavroexample.entity.PessoaDTO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class PessoaProducerConfig {

    @Bean
    fun pessoaDTOTemplate(factory: ProducerFactory<String, PessoaDTO>): KafkaTemplate<String, PessoaDTO> = KafkaTemplate(factory)

}