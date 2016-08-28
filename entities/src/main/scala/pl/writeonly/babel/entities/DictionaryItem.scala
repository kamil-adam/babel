package pl.writeonly.babel.entities

import java.math.BigInteger
import javax.jdo.annotations._
import com.google.gson.Gson

@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
class DictionaryItem(@Persistent var value: String) extends Entity {
  //def this(value: String) = this(value, null)
  def this() = this(null)

  override def toString() = "{value : " + value + ", id : " + id + "}"
  //override def toString() = new Gson().toJson(this)
}