package pl.writeonly.babel.beans
import javax.annotation.Resource
import java.io.BufferedReader
import org.springframework.stereotype.Service
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.Record
import pl.writeonly.babel.entities.User
import pl.writeonly.scala.util.ToBoolean
import scala.collection.mutable.MutableList
import pl.writeonly.babel.entities.Relation

@org.springframework.stereotype.Service
class RecordBean(@Resource(name = "daoImpl") val dao: DaoCrud) extends ToBoolean {
  val clazz = classOf[Record]
  def persist(record: Record) = dao.persist(record)
  def persistAll(records: List[Record]) = dao.persistAll(records)
  def find(user: User) = dao.find(clazz);
  def find() = dao.find(clazz)
  def merge(record: Record) = dao.merge(record);

  def parse(reader: BufferedReader): MutableList[Record] = {
    val list = new MutableList[Record]
    do {
      val line: String = reader.readLine
      if (!line) return list
      list += new Record(line)
      var i: Int = new Integer(line).toInt
    } while (true)
    return null
  }
  
  def toRecordAll(relations : List[Relation]) = {
    
  }
}