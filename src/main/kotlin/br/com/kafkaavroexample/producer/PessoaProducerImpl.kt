package br.com.kafkaavroexample.producer

import br.com.kafkaavroexample.entity.Pessoa
import br.com.kafkaavroexample.entity.PessoaDTO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import java.util.*

@Component
class PessoaProducerImpl (
    private val pessoaTemplate: KafkaTemplate<String, PessoaDTO>
    ){

    val topicName = "Pessoa"

    fun persiste(messageId: String, payload: Pessoa){
        val dto = criaPessoaDto(payload)
        enviaMensagem(messageId, dto)
    }

    private fun enviaMensagem(messageId: String, dto: PessoaDTO) {
        val message = createMessageWithHeaders(messageId, dto, topicName)

        val future: ListenableFuture<SendResult<String, PessoaDTO>> = pessoaTemplate.send(message)

        future.addCallback(object: ListenableFutureCallback<SendResult<String, PessoaDTO>> {
            override fun onSuccess(result: SendResult<String, PessoaDTO>?) {
                println("Pessoa enviada. MessageId $messageId")
            }
            override fun onFailure(ex: Throwable) {
                println("Erro no envio. MessageId $messageId")
            }
        })
    }

    private fun criaPessoaDto(payload: Pessoa): PessoaDTO {
        return PessoaDTO.newBuilder()
            .setNome(payload.nome)
            .setSobrenome(payload.sobrenome)
            .build()
    }

    private fun createMessageWithHeaders(messageId: String, pessoaDTO: PessoaDTO, topic: String): Message<PessoaDTO> {
        return MessageBuilder.withPayload(pessoaDTO)
            .setHeader("flowId", UUID.randomUUID().toString())
            .setHeader("cid", messageId)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.MESSAGE_KEY, messageId)
            .build()
    }

}