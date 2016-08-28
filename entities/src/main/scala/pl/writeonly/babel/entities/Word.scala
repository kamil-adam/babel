package pl.writeonly.babel.entities

import pl.writeonly.babel.entities.Value._
import pl.writeonly.babel.entities.Part._
import javax.jdo.annotations._
import scala.beans.BeanProperty

object Word extends Entity {
  def parse(toParse: String) = {
    val splinters = toParse.split("'")
    val word = new Word(splinters)
  }
}

@PersistenceCapable(detachable = "true")
@PrimaryKey(name = "id")
class Word(
  @Persistent @BeanProperty var pre: String,
  @Persistent @BeanProperty var core: String,
  @Persistent @BeanProperty var suf: String,
  @Persistent @BeanProperty var part: Part,
  @Persistent @BeanProperty var lang: Lang) 
  extends Entity {
  var parent: Word = _
  var list: List[Word] = _
  //def this(pre: String, core: String, suf: String, part: String, lang: String) = this(pre, core, suf, new Part(part), new Lang(lang))
  def this() = this("", "", "", "", "")
  def this(strings: Array[String]) = this(strings(0), strings(1), strings(2), strings(3), strings(4))
  def this(string: String) = this(string split "'")
  def this(core: String, part: Part, lang: Lang) = this ("", core, "", part, lang)

  def toCompare = pre + core + suf + part
  def compareTo(that: Word) = toCompare.compareTo(that.toCompare);
  override def toString = pre + " " + core + " " + suf + " " + part + " " + lang;

}