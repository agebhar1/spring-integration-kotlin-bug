# Spring Integration/Kotlin example for upgrade from Spring Boot 2.4.x to 2.5-RC1

# Spring Boot 2.4.x

pom.xml:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.4.5</version>
    <relativePath/>
</parent>
```

Test is successful.

# Spring Boot 2.5-RC1 (Kotlin 1.5-RC)

pom.xml:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.0-RC1</version>
    <relativePath/>
</parent>
```

Test fails with:
```
...
2021-04-29 18:26:02.410 ERROR 51452 --- [           main] o.s.i.handler.LambdaMessageProcessor     : Could not invoke the method 'public java.lang.Object io.github.agebhar1.SpringIntegrationKotlinTests$Configuration$$Lambda$598/171160803.handle(java.lang.Object,org.springframework.messaging.MessageHeaders)' due to a class cast exception, if using a lambda in the DSL, consider using an overloaded EIP method that takes a Class<?> argument to explicitly  specify the type. An e
xample of when this often occurs is if the lambda is configured to receive a Message<?> argument.
                                                                                                                       
java.lang.ClassCastException: java.lang.String cannot be cast to org.springframework.messaging.Message
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_292]
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_292]
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_292]
        at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_292]
        at org.springframework.integration.handler.LambdaMessageProcessor.processMessage(LambdaMessageProcessor.java:97) ~[spring-integration-core-5.5.0-RC1.jar:5.5.0-RC1]
        at org.springframework.integration.handler.ServiceActivatingHandler.handleRequestMessage(ServiceActivatingHandler.java:105) [spring-integration-core-5.5.0-RC1.jar:5.5.0-RC1]
        at org.springframework.integration.handler.AbstractReplyProducingMessageHandler.handleMessageInternal(AbstractReplyProducingMessageHandler.java:134) [spring-integration-core-5.5.0-RC1.jar:5.5.0-RC1]
...
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 1.22 s <<< FAILURE! - in io.github.agebhar1.SpringIntegrationKotlinTests
[ERROR] shouldTransformToUpperCase{MessagingTemplate, QueueChannel}  Time elapsed: 0.117 s  <<< ERROR!
org.springframework.messaging.MessageHandlingException: error occurred in message handler [ServiceActivator for [org.springframework.integration.handler.LambdaMessageProcessor@2c768ada] (flow.org.springframework.integration.config.ConsumerEndpointFactoryBean#0)]; nested exception is java.lang.ClassCastException: java.lang.String cannot be cast to org.springframework.messaging.Message
        at io.github.agebhar1.SpringIntegrationKotlinTests.shouldTransformToUpperCase(SpringIntegrationKotlinDebugApplicationTests.kt:26)
Caused by: java.lang.ClassCastException: java.lang.String cannot be cast to org.springframework.messaging.Message
        at io.github.agebhar1.SpringIntegrationKotlinTests.shouldTransformToUpperCase(SpringIntegrationKotlinDebugApplicationTests.kt:26)
```

# Spring Boot 2.5-RC1 explicit w/ Kotlin 1.4.32

pom.xml:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.0-RC1</version>
    <relativePath/>
</parent>

<properties>
    <java.version>1.8</java.version>
    <kotlin.version>1.4.32</kotlin.version>
</properties>
```

Test is successful.