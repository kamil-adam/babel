package pl.writeonly.babel.entities

import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import com.google.gson.Gson
import javax.jdo.annotations._
import pl.writeonly.scala.util.ToBoolean
import java.math.BigInteger
import javax.jdo.annotations.Persistent

//import org.springframework.spring._

object Entity {
  type Idable = { def id: BigInteger; def getClass: Class[_] }

}

@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
@PrimaryKey(name = "id") //@Inheritance(strategy=InheritanceStrategy.COMPLETE_TABLE)
class Entity extends Serializable with ToBoolean {
  //def this() = this(0)
  type Idable = { def id: BigInteger; def getClass: Class[_] }
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
  var id: BigInteger = _

  //  @Version
  //  var version : Int = _

  //  override def equals(o: AnyRef) = o && (
  //    o.isInstanceOf[Int] && o.asInstanceOf[Int].compareTo(id)
  //    || o.isInstanceOf[Idable] && o.asInstanceOf[Idable].id && o.asInstanceOf[Idable].id.compare(id)
  //    || o.isInstanceOf[AnyRef] && o.asInstanceOf[AnyRef].hashCode.equals(this));

  def reflectionToString = ReflectionToStringBuilder.toString(this)
  def toJson = new Gson().toJson(this)
  override def toString = toJson
  override def hashCode() = if (id) id.hashCode else 0
  //override def equals(other: Any) = other.isInstanceOf[Entity] && other.asInstanceOf[Entity].id == id
  override def equals(other: Any) = {
    if (other && other.isInstanceOf[Entity]) {
      val that = other.asInstanceOf[Entity]
      (that.id) && that.id == id
    }
    false
  }
}