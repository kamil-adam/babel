package pl.writeonly.babel.beans

import pl.writeonly.babel.entities.Word
import pl.writeonly.babel.entities.Record
import scala.collection.mutable.LinkedList
import scala.collection.mutable.MutableList
import pl.writeonly.scala.util.Genie
import pl.writeonly.scala.util.ToBoolean
import scala.collection.mutable.WrappedArray.ofRef
/**
 * zajmuje się mieszaniem i sprawdzaniem słów
 */
//@org.springframework.stereotype.Component
class Heap(val data: Array[Record]) extends Genie with ToBoolean {
  //opakowane dane
  val wrapped = new ofRef[Record](data)
  //dane wylosowane
  var game: Game = _

  //kopcuj!
  def init {
    buildHeap
  }
  /**
   * losuje dane początkowe
   */
  private def draw: Array[Record] = {

    return new Array[Record](0)
  }

  def play = {
    game = new Game(draw)
    game
  }

  def get = game.tuple

  /**
   * sprawdza słowo
   * @return true jeśli poprawne
   */
  def checkValue(value: Word): Boolean = {
    val check = value.equals(game.priv.relation.key)
    if (check) {
      game.priv ++;
      //shiftDown(wrapped.findIndexOf(Record => Record = _ == priv))
      shiftDown(wrapped.indexOf(game.priv))
      //TODO przemyśleć czy nie da się inaczej
      game = null
    } else {
      game.priv --;
      shiftDown(wrapped.indexOf((r: Record) => r == game.priv))
      val get = game.map.get(value).get;
      get --;
      shiftDown(wrapped.indexOf((r: Record) => r == get))
    }
    return check
  }
  //  def swap(value: AnyRef, key: AnyRef) {
  //    val temp = key
  //    key = value
  //    value = temp
  //  }

  /**
   * przesuwa w dół błędne słowo
   */
  private def shiftDown(i: Int) {
    val n = data.length
    var k = i
    var j = k
    do {
      var j = k
      if (2 * j <= n && data(k).eval < data(2 * j).eval) {
        k = 2 * j;
      }
      if (2 * j + 1 <= n && data(k).eval < data(2 * j + 1).eval && data(2 * j).eval < data(2 * j + 1).eval) {
        val t = data(j) -> data(k)
        data(k) = t._1
        data(j) = t._2
      }
    } while (j != k)
  }
  /**
   * buduje kopiec
   */
  private def buildHeap {
    var i = data.length / 2
    while (0 <= i) {
      shiftDown(i)
      i -= 1
    }
  }
}

