package pl.writeonly.babel.daos

import java.math.BigInteger
import scala.collection.JavaConversions._
import scala.collection.JavaConversions._
import org.springframework.orm.jdo.JdoCallback
import org.springframework.orm.jdo.support.JdoDaoSupport
import com.weiglewilczek.slf4s.Logging
import javax.jdo.PersistenceManager
import pl.writeonly.babel.entities.AbstractValue
import pl.writeonly.babel.entities.DictionaryItem
import pl.writeonly.babel.entities.Entity
import pl.writeonly.babel.entities.Variable
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._
import scala.collection.convert.Wrappers._

class PersistenceGetter(pm: PersistenceManager) extends Logging {
  def get[T](entity: T) = {
    val clazz = entity.getClass
    val id = if (entity.isInstanceOf[Entity]) entity.asInstanceOf[Entity].id
    else if (entity.isInstanceOf[AbstractValue]) entity.asInstanceOf[AbstractValue].value
    else null
    val byid = pm.getObjectById(clazz, id).asInstanceOf[T]
    logger debug "byid => " + byid
    byid
  }
  def getAll[T](entities: List[T]) = entities.map(get(_))
}
//@org.springframework.stereotype.Repository
class DaoJdoCallback extends DaoJdo {
  type Idable = { def id: BigInteger }

  implicit def doInJdo[T](f: PersistenceManager => T) = new JdoCallback[T]() {
    def doInJdo(pm: PersistenceManager): T = f(pm)
  }

  implicit def toGetter(pm: PersistenceManager) = new PersistenceGetter(pm)
  private def reread[T](entity: T): T = getJdoTemplate.execute(
    (pm: PersistenceManager) => pm.get(entity), true)

  override def persist[T](entity: T): T = getJdoTemplate.execute(
    (pm: PersistenceManager) => pm.get(pm.makePersistent(entity)), true)

  override def delete[T](entity: T) = getJdoTemplate.execute(
    (pm: PersistenceManager) => pm.deletePersistent(pm.get(entity)), true)

  override def find[T](c: Class[T]) = {
    val entities = super.find(c)
    rereadAll(entities)
  }

  override def persistAll[T](entities: List[T]): List[T] = {
    logger debug "persistAll entities => " + entities
    val list: java.util.List[T] = ListBuffer(entities: _*)
    getJdoTemplate.execute(
      (pm: PersistenceManager) => pm.getAll(collectionAsScalaIterable(pm.makePersistentAll(list)).toList), true)
  }
  override def deleteAll[T](entities: List[T]) = getJdoTemplate.execute(
    (pm: PersistenceManager) => pm.deletePersistentAll(asJavaCollection(pm.getAll(entities))), true)

  def rereadAll[T](entities: List[T]) = getJdoTemplate.execute(
    (pm: PersistenceManager) => pm.getAll(entities), true)

}