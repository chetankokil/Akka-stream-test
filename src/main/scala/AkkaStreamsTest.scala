package com.test.akkastreams

import akka.actor.ActorSystem
import akka.actor.TypedActor.context
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.kafka.scaladsl.{Committer, Consumer}
import akka.kafka.{CommitterSettings, ConsumerSettings, KafkaConsumerActor, Subscriptions}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}


object AkkaStreamsTest extends App {

  implicit val system = ActorSystem("Akka-Streams-Test")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher


  val config = ConfigFactory.load();


  val consumerSettings = ConsumerSettings(system, new StringDeserializer, new StringDeserializer)
    .withBootstrapServers(config.getString("stream.source.kafka-source.bootstrapServers"))
    .withGroupId(config.getString("stream.source.kafka-source.group"))
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getString("stream.source.kafka-source.reset"))

  val source = Consumer.committableSource(consumerSettings, Subscriptions.topics(config.getString("stream.source.kafka-source.topic")))
    .map {
      msg => println(msg.record.value())
        msg.committableOffset.commitScaladsl()
    }
    .run()

}
