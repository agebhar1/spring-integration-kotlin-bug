package io.github.agebhar1

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.dsl.IntegrationFlows.from
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.support.MessageBuilder
import org.springframework.integration.test.matcher.PayloadMatcher.hasPayload
import org.springframework.integration.transformer.GenericTransformer
import org.springframework.messaging.Message

@SpringBootTest
class SpringIntegrationKotlinTests {

    @Test
    fun shouldTransformToUpperCase(@Autowired template: MessagingTemplate, @Autowired output: QueueChannel) {

        template.send(MessageBuilder.withPayload("hello").build())

        val response = template.receive(output, 2500)
        assertThat(response, hasPayload("HELLO"))
    }

    @TestConfiguration
    @EnableIntegration
    class Configuration {

        @Bean
        fun input() = DirectChannel()

        @Bean
        fun output() = QueueChannel()

        @Bean
        fun template() = MessagingTemplate(input())

        @Bean
        fun flow(): StandardIntegrationFlow =
                from(input())
                        .handle { message: Message<String>, _ -> message.also { println(it.payload) } }
                        .transform(
                                GenericTransformer<Message<String>, Message<String>> { message ->
                                    MessageBuilder.withPayload(message.payload.toUpperCase())
                                            .copyHeaders(message.headers)
                                            .build()
                                })
                        .channel(output())
                        .get()
    }
}
