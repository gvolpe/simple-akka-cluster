package com.gvolpe.cluster

import akka.actor.ActorSystem
import com.typesafe.config.{Config, ConfigFactory}

object ClusterApp extends App {

  val system = ActorSystem("ClusterSystem", remoteConfig(2551))
  system.actorOf(ClusterListener.props, "clusterListener")

  val ports = Seq(2552, 0)

  ports foreach { port =>
    val system = ActorSystem("ClusterSystem", remoteConfig(port))
    system.actorOf(RemoteActor.props, "remoteActor")
  }

  val simple = system.actorOf(SimpleActor.props, "simpleActor")
  simple ! SimpleActor.Start

  def remoteConfig(port: Int): Config = {
    ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").
      withFallback(ConfigFactory.load())
  }
  
}