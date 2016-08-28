package pl.writeonly.babel.beans
import javax.annotation.Resource
import org.springframework.stereotype.Service
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.Side
import scala.collection.mutable.MutableList

@org.springframework.stereotype.Service
class SideBean(@Resource(name = "daoImpl") val dao: DaoCrud) {
  def find() = dao.find(classOf[Side])
  def findName = find().foldLeft(new MutableList[String]())((l: MutableList[String], s: Side) => { l += s.name })
}