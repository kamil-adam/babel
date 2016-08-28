//package pl.writeonly.babel.akka.clients
//
//import akka.actor.ActorLogging
//import akka.event.LoggingReceive
//import akka.actor.Actor
//import pl.writeonly.babel.dtos.RelationsDto
//
//class RelationClient  extends Actor  with ActorLogging{
//  def receive = LoggingReceive {
//    case RelationsDto(relations) => 
//      log debug "relations => " + relations
//      
//  }
//}