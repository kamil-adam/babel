package pl.writeonly.babel.entities

import java.math.BigInteger
import javax.jdo.annotations.DatastoreIdentity
import javax.jdo.annotations.Inheritance
import javax.jdo.annotations.PersistenceCapable
import javax.jdo.annotations.PrimaryKey
import javax.jdo.annotations._
import scala.beans.BeanProperty

//@DatastoreIdentity(strategy = IdGeneratorStrategy.NATIVE)
//@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION) 
//@PersistenceCapable(detachable = "true")
//@PersistenceCapable(detachable="true",identityType=IdentityType.APPLICATION)
@PersistenceCapable(detachable="true",identityType=IdentityType.APPLICATION)
//@Inheritance(strategy = InheritanceStrategy.NEW_TABLE) //@PrimaryKey(name = "id")
//, @PrimaryKey var id: Int)
class Variable() extends Serializable {
  //def this(value: String) = this (value, null)
	//def this () = this (null)
  @BeanProperty
  @Persistent var value: String = _
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
  var id: BigInteger = _

  override def toString() = "value " + value + " id " + id

}