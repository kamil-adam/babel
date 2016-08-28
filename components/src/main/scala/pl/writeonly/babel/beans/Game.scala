package pl.writeonly.babel.beans

import pl.writeonly.babel.entities.User
import pl.writeonly.babel.entities.Record
import pl.writeonly.babel.entities.Word

import scala.collection.mutable.MutableList
import scala.collection.mutable.HashMap
/**
 * dla danego użytkownika z danej listy rekordów tworzy grę
 */

/**
 * wybiera
 */
//@org.springframework.stereotype.Component
class Game(val drawn: Array[Record]) {
  val priv = drawn(0)
  val key = priv.relation.key
  val map= new HashMap[Word, Record] //tra zgadnąć co tam dać
  val lambda = (ml: MutableList[Word], r: Record) => {
    val word = r.relation.value
    this.map + (word -> r)
    ml += word
  }
  val values = drawn.foldLeft(new MutableList[Word])(lambda).toArray
  val tuple = key -> values

}