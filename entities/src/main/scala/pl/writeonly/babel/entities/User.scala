package pl.writeonly.babel.entities


@javax.jdo.annotations.PersistenceCapable
class User(var name: String, var password: String) extends Entity {
	def this (strings:Array[String]) = this(strings(0),strings(1)) 
	def this (string:String) = this(string split "\\")
	override def toString = name +"\\"+ password
 }

trait ConvertPassword {
  
}