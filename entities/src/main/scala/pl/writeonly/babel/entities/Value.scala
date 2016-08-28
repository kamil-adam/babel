package pl.writeonly.babel.entities

import javax.jdo.annotations._
import java.lang.Comparable

//SUBCLASS_TABLE
//COMPLETE_TABLE
//@PersistenceCapable()
//@PrimaryKey(name = "value")
//@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
//abstract class Value(var value: String) {
//  override def toString () = value
//}

//trait Value {
//  var value: String = _
//  override def toString() = value
//}

//@PersistenceCapable()
//@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
//class Value () {}

//dla klas bez propertisowych działa

object Value {
  implicit def avToString(av: AbstractValue) = "" + av
}
@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@PrimaryKey(name = "value")
abstract class AbstractValue(v:String) extends Comparable[AbstractValue] with Serializable {
  @PrimaryKey var value: String = v
  
  override def toString() = value
  def compareTo(other: AbstractValue): Int = value.compareTo(other.value)
  override def hashCode() = value.hashCode
  override def equals(other: Any) = other.isInstanceOf[AbstractValue] && other.asInstanceOf[AbstractValue].value == value
}

//@PersistenceCapable(detachable="true",identityType=IdentityType.APPLICATION)
@PersistenceCapable(detachable = "true")
@PrimaryKey(name = "value")
class Lang(lang: String) extends AbstractValue(lang) {}

class LangRadio(lang: String, var radio: Boolean = false, var check: Boolean = false) extends Lang(lang) {
  //def this(lang: Lang, radio: Boolean = false, check: Boolean = false) = this (lang.value, radio, check)
  def this(lang: Lang) = this (lang.value)
}

object Lang {
  implicit def langToString(lang: Lang) = lang.toString
  implicit def stringToLang(lang: String) = new Lang(lang)

}

//@PersistenceCapable(detachable="true",identityType=IdentityType.APPLICATION)
@PersistenceCapable(detachable = "true")
@PrimaryKey(name = "value")
class Part(part: String) extends AbstractValue(part) {}

object Part {
  implicit def partToString(part: Part) = part.toString
  implicit def stringToPart(part: String) = new Part(part)
}
