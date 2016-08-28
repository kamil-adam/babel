package pl.writeonly.babel.entities

import java.math.BigInteger
import javax.jdo.annotations._
import scala.collection.mutable.MutableList
import com.google.gson.Gson

@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
class Dictionary(@Persistent var name: String) extends Entity {
  def this() = this(null)

  //@Persistent(mappedBy="account")
  var items = new MutableList[DictionaryItem]

  override def toString() = "name " + name + ", items " + items + ", id " + id
  //override def toString() = new Gson().toJson(this)

}