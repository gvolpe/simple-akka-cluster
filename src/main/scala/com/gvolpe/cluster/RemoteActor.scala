package com.gvolpe.cluster

import akka.actor.{Props, Actor}

object RemoteActor {
  case class Greet(name: String)
  def props = Props[RemoteActor]
}

class RemoteActor extends Actor {

  import RemoteActor._

  def receive = {
    case Greet(name) =>
      sender ! s"Hello $name"
  }

}
