package pl.writeonly.babel.apps

import pl.writeonly.babel.mediators.AppLogging
import pl.writeonly.babel.mediators.AppContext
import pl.writeonly.babel.beans.RelationBean
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities._
import javax.jdo.JDOHelper

object VariableApp extends AppLogging {
  AppContext.main(args)
  val dao = AppContext.bean[DaoCrud]("daoImpl")

  //val v0 = new Variable("variable")
  val v0 = new Variable()
  val clazz = classOf[Variable]
  v0.value = "variable"
  logger info "v0 => " + v0

  val v1 = dao.persist(v0)
  logger info "v1 => " + v1
  val v2 = dao.get(clazz, v1.id)
  logger info "v2 => " + v2

  val v3 = dao.delete(v2)
  logger info "v3 => " + v3
  val v4 = dao.get(clazz, v2.id)
  logger info "v4 => " + v4
}