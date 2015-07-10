package com.gvolpe.cluster

import akka.actor.{Actor, Props}

import scala.concurrent.duration._

object SimpleActor {
  case object Start
  case class FindGreeting(name: String)
  def props = Props[SimpleActor]
}

class SimpleActor extends Actor {

  import SimpleActor._
  import context.dispatcher

  def receive = {
    case Start =>
      val actorName = self.path.name
      context.system.scheduler.scheduleOnce(7 seconds, self, FindGreeting(s"Gabriel-$actorName"))
    case FindGreeting(name) =>
      val remoteActor = context.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2552/user/remoteActor")
      remoteActor ! RemoteActor.Greet(name)
    case greeting: String =>
      println(s"GREETING RECEIVED >> $greeting")
  }

}
