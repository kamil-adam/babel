package pl.writeonly.babel.daos

//import org.springframework.jdbc.core.JdbcTemplate
import collection.JavaConversions._
import org.springframework.orm.jdo.support.JdoDaoSupport
import pl.writeonly.babel.entities.Entity
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import com.weiglewilczek.slf4s.Logging
import org.springframework.orm.jdo.JdoCallback
import javax.jdo.PersistenceManager
import javax.jdo.JDOHelper
import pl.writeonly.babel.entities.Variable
import java.math.BigInteger
import pl.writeonly.babel.entities.DictionaryItem

//@org.springframework.stereotype.Repository
class DaoJdo extends JdoDaoSupport with DaoCrud {

  def persist[T](entity: T): T = getJdoTemplate.makePersistent(entity)

  def persistAll[T](t: List[T]): List[T] = {
    getJdoTemplate.makePersistentAll(t)
    t
  }
  def get[T](clazz: Class[T], id: Int): T = getJdoTemplate.getObjectById(clazz, id)
  def get[T](clazz: Class[T], id: BigInteger): T = getJdoTemplate.getObjectById(clazz, id)
  //  def find[T](c: Class[T]) =  List(getJdoTemplate.find(c).toArray : _*).asInstanceOf[List[T]]
  def find[T](c: Class[T]) = getJdoTemplate.find(c).toArray.map(as[T](_)).toList
  def as[T](entity: Object) =   entity.asInstanceOf[T]
  def find[T](c: Class[T], s: String) = List(getJdoTemplate.find(c, s).toArray: _*).asInstanceOf[List[T]]
  def findOne[T](c: Class[T], s: String): T = getJdoTemplate.find(c, s).iterator.next
  def merge[T](t: T): T = persist(t)
  def mergeAll[T](t: List[T]): List[T] = persistAll(t)
  //
  def delete[T](entity: T) =  getJdoTemplate.deletePersistent(entity)
  def deleteAll[T](entities: List[T]) = getJdoTemplate.deletePersistentAll(ListBuffer(entities: _*))
  def remove[T](t: T) = getJdoTemplate.deletePersistent(t)
  def removeAll[T](t: List[T]) = getJdoTemplate.deletePersistentAll(t)

}