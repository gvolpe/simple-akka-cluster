package com.gvolpe.cluster

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object ClusterApp extends App {

  val ports = Seq("2551", "2552", "0")

  ports foreach { port =>
    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
      withFallback(ConfigFactory.load())

    val system = ActorSystem("ClusterSystem", config)
    system.actorOf(ClusterListener.props, name = "clusterListener")
  }

}