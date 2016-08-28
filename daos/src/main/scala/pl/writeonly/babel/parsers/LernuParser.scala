package pl.writeonly.babel.parsers

import pl.writeonly.babel.exception.MatchException
import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.entities.Word
import pl.writeonly.babel.entities.Relation
import pl.writeonly.babel.entities.Part
import scala.collection.mutable.ListBuffer
import com.weiglewilczek.slf4s.Logging

//@org.springframework.stereotype.Repository
@org.springframework.stereotype.Component
class LernuParser extends Logging {

  val serchi_pl = "http://en.lernu.net/cgi-bin/serchi.pl"
  val url_string_ = "http://en.lernu.net/cgi-bin/serchi.pl?modelo=ed&delingvo=eo&allingvo=en&prioritato=0&starto=0&bobeloid=undefined&dishaki=true&hazardo=UXxqugKH"

  def urlBuild(modelo: String, delingvo: String, allingvo: String) = {
    serchi_pl +
      "?modelo=" + modelo +
      "&delingvo=" + delingvo +
      "&allingvo=" + allingvo +
      "&prioritato=0&starto=0&bobeloid=undefined&dishaki=true&hazardo=UXxqugKH"
  }

  def toRelationsIf (key: String, value: String) = {
    val keyArray = key.split("\t")
    val valueArray = value.split("\t")

    val keyList = keyArray(2).split("/")
    val keyParent = keyArray(2).split("·")

    val part = if (5 < keyArray.size) {
      keyArray(5) match {
        case "n" => new Part(keyList(keyList.size - 1)) // polski
        case "v" => new Part(keyList(keyList.size - 1)) // angielski
        case "f" => new Part(keyList(keyList.size - 1))
        case "s" => null
        case _   => throw new MatchException(keyArray(5) +"|"+ key)
      }
    }
    else null
    //logger debug "toRelations part => " + part

    val keyWord = new Word("", keyArray(1), "", part, "eo")
    //keyWord.list = keyArray(2)
    //keyWord.parent = keyArray(3)
    //val part = if ("n".equals(valueArray(5))) keyList(keyList.size - 1) else ""

    val valueCoreArray = (valueArray(3)).split(",")
    val valueLang = valueArray(1)

    val result = (valueCoreArray).foldLeft(new ListBuffer[Relation])((list, core) => {
      val valueWord = new Word("", core, "", part, valueLang)
      val relation = new Relation(keyWord, valueWord)
      logger debug "relation => " + relation
      list += relation
      //logger debug "list size => " + list.size
      list
    }).toList
    //logger debug "toRelation result size => " + result.size
    result
   
  }
  def toRelationsElse (key: String, value: String, lang: Lang) = {
    val result = List[Relation]()
    result
  }
  
  def toRelations(key: String, value: String, lang: Lang): List[Relation] = {
    logger debug "toRelation | lang => "+ lang + " | key => " + key + " | value => " + value 
    if (lang.value == "eo") toRelationsIf(key,value) else toRelationsElse(key,value,lang)
   }

  def createFoldLeft() = {}

}