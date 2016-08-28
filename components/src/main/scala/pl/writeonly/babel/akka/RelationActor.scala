package pl.writeonly.babel.akka

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.event.LoggingReceive
import pl.writeonly.babel.entities.Relation
import pl.writeonly.babel.dtos.RelationsDto

//aktor do odbierania komunikatów od 
class RelationActor extends Actor  with ActorLogging{
  def receive = LoggingReceive {
    case RelationsDto(relations) => 
      log debug "relations => " + relations
      
  }
}