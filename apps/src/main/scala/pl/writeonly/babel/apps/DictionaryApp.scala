package pl.writeonly.babel.apps

import pl.writeonly.babel.mediators.AppLogging
import pl.writeonly.babel.mediators.AppContext
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.DictionaryItem
import pl.writeonly.babel.entities.Variable
import java.math.BigInteger
import pl.writeonly.babel.entities._

object DictionaryApp extends AppLogging {
  implicit def int2big(i: Int) = new BigInteger("" + i)
  AppContext.main(args)
  val dao = AppContext.bean[DaoCrud]("daoImpl")

  //val v0 = new Variable("variable")
  
  
  
  val maked = new Dictionary("dict")
  val clazz = classOf[Dictionary]
  logger info "maked => " + maked
  
  maked.items += new DictionaryItem ("item0") 
  maked.items += new DictionaryItem ("item1") 
  //maked.items += new DictionaryItem ("item2") 
  logger info "maked.items " + maked.items

  val persisted = dao.persist(maked)
  logger info "persisted => " + persisted
  val finded = dao.get(clazz, persisted.id)
  logger info "finded => " + finded

  val deleted = dao.delete(finded)
  logger info "deleted => " + deleted
  val v4 = dao.get(clazz, finded.id)
  logger info "v4 => " + v4
}