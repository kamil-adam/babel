package pl.writeonly.babel.apps

import pl.writeonly.babel.beans.RelationBean
import pl.writeonly.babel.entities.Relation
import pl.writeonly.babel.entities.Word
import pl.writeonly.babel.mediators._

import pl.writeonly.babel.entities.Value._
import pl.writeonly.babel.entities.Part._

object RelationApp extends AppLogging {
  AppContext.main(args)
  val relationService = AppContext.bean[RelationBean]("relationBean")

  val r0 = new Relation(new Word("key", "o", "en"), new Word("value", "o", "en"))
  logger info "r0 => " + r0
  val r1 = relationService.persist(r0)
  logger info "r1 => " + r1
  val r2 = relationService.delete(r1)
  logger info "r2 => " + r2
  //val r3 = relationService.
}