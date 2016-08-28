package pl.writeonly.babel.apps

import pl.writeonly.babel.mediators.AppLogging
import pl.writeonly.babel.mediators.AppContext
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.DictionaryItem
import pl.writeonly.babel.entities.Variable
import java.math.BigInteger

object DictionaryItemApp extends AppLogging {
  implicit def long2big(i: Long) = BigInteger.valueOf(i)
  implicit def big2long(big :BigInteger) = big.longValue() 
  AppContext.main(args)
  val dao = AppContext.bean[DaoCrud]("daoImpl")

  //val v0 = new Variable("variable")
  //val v0 = new DictionaryItem("item", 666)
  val v0 = new DictionaryItem("item")
  val clazz = classOf[DictionaryItem]
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