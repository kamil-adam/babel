package pl.writeonly.babel.entities

import java.util.Comparator
import pl.writeonly.scala.util.ToBoolean
import javax.jdo.annotations.Join
import javax.jdo.annotations.Persistent

trait Evaluator[T] {
  def evaluate(paramT1: T, paramT2: T): Int;

  def equals(paramObject: AnyRef): Boolean;
}

object Record extends Comparator[Record] with ToBoolean {
  var comparator: Comparator[Record] = _
  var evaluator: Evaluator[Record] = _

  def compare(key: Record, value: Record): Int = {
    if (comparator != null) {
      //return comparator(key, value)
      return 0;
    }
    else {
      return evaluate(key) - evaluate(value)
    }
  }
  def evaluate(record: Record): Int = {
    if (evaluator != null) {
      return 0
    }
    else {
      return record.corect - record.wrong
    }
  }
  def int(string: String) = new Integer(string) toInt
}

class Record(@Persistent val user: User, @Persistent val relation: Relation, var corect: Int, var wrong: Int)
  extends Entity {
  def this(user: User, relation: Relation) = this(user, relation, 0, 0)
  def this(strings: Array[String]) = this(new User(strings(0)), new Relation(strings(1)), Record.int(strings(2)), Record.int(strings(3)))
  def this(string: String) = this(string split ",")
  def ++ = corect + 1
  def -- = wrong + 1
  def eval = Record evaluate this
  override def toString = "" + user + "," + relation + "," + corect + "," + wrong

}