package pl.writeonly.babel.daos

import au.com.bytecode.opencsv._
import au.com.bytecode.opencsv.bean._
import java.io._
import scala.collection.JavaConversions._
import scala.collection.mutable._
import com.weiglewilczek.slf4s.Logging
import pl.writeonly.babel.dtos._

//MappingStrategy
//HeaderColumnNameMappingStrategy
//HeaderColumnNameTranslateMappingStrategy
//ColumnPositionMappingStrategy

@org.springframework.stereotype.Repository
class DaoCsv extends DaoFile with Logging {

  def read[T](c: Class[T]): List[T] = List[T]()
  def write[T](list: List[T]) = List[T]()

  def readTuple(string: String) = {
    val reader = new CSVReader(new FileReader(string));
    val header = reader.readNext
    val readed = reader.readAll;
    val buffer: Buffer[Array[String]] = readed
    header -> buffer
  }

  //zwraca liste obiektów
  def read[T](clazz: Class[T], string: String) = {
    logger debug ("DaoCsv read clazz => " + clazz + " string " + string)
//    logger debug ("clazz fields size " + clazz.getFields.length)
//    logger debug ("clazz fields " + clazz.getFields.mkString(","))
//    logger debug ("clazz fields size " + clazz.getDeclaredFields.length)
    logger debug ("clazz fields " + clazz.getDeclaredFields.mkString(","))
    val (head, body) = readTuple(string)
    val lambda = (ll: List[T], array: Array[String]) => {
      val instance = clazz.newInstance
    		
      for (i <- 0 until head.size) {
        //logger debug head(i)
        val field = clazz.getDeclaredField(head(i))
        field.setAccessible(true)
        field.set(instance, array(i))
      }

      ll ::: List[T](instance)
    }
    val result = body.foldLeft(List[T]())(lambda)
    
    logger debug ("result => " + result)

    result
  }

  def position() {
    val strat = new ColumnPositionMappingStrategy[String]();
    val clazz = classOf[String]
    strat.setType(clazz);
    val columns = Array[String]("name", "orderNumber", "id"); // the fields to bind do in your JavaBean
    strat.setColumnMapping(columns);

    val csv = new CsvToBean();
    //val list = csv.parse(strat, yourReader);
  }
  def headerName() {
    val strat = new HeaderColumnNameMappingStrategy[String]();
    val clazz = classOf[String]
    strat.setType(clazz);
    val columns = Array[String]("name", "orderNumber", "id"); // the fields to bind do in your JavaBean
    //strat.

    val csv = new CsvToBean();
    //val list = csv.parse(strat, yourReader);
  }

  def headerNameTranslate() {
    val strat = new HeaderColumnNameTranslateMappingStrategy[String]();
    val clazz = classOf[String]
    strat.setType(clazz);
    val columns = Array[String]("name", "orderNumber", "id"); // the fields to bind do in your JavaBean
    //strat.

    val csv = new CsvToBean();
    //val list = csv.parse(strat, yourReader);
  }
}