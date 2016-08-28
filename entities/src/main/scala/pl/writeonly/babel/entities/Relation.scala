package pl.writeonly.babel.entities

import javax.jdo.annotations._
import scala.beans.BeanProperty
/**
 * key klucz po którym szukamy
 * vartość którą odczytujemy
 */
object Relation {
  def parse(toParse: String) = {
    val parsed = toParse split "-"
    new Relation(parsed)
  }
}
@PersistenceCapable(detachable="true")
@PrimaryKey(name = "id") 
class Relation(@Persistent @BeanProperty val key: Word, @Persistent @BeanProperty val value: Word) extends Entity {
  def this(strings: Array[String]) = this (new Word(strings(0)), new Word(strings(1)))
  def this(string: String) = this (string split "-")
  def this() = this(null, null)
  //override def toString = "" + key + "-" + value
}