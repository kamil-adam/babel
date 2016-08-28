package pl.writeonly.babel.beans

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.MutableList
import com.ning.http.client.AsyncCompletionHandler
import com.ning.http.client.AsyncHttpClient
import com.ning.http.client.Response
import com.weiglewilczek.slf4s.Logging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import javax.annotation.PostConstruct
import pl.writeonly.babel.akka.RelationActor
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.daos.DaoCsv
import pl.writeonly.babel.dtos.LernuQuery
import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.entities.Lang.langToString
import pl.writeonly.babel.entities.Lang.stringToLang
import pl.writeonly.babel.entities.Part.stringToPart
import pl.writeonly.babel.entities.Relation
import pl.writeonly.babel.entities.Word
import pl.writeonly.babel.parsers.LernuParser
import pl.writeonly.scala.util.Genie
import pl.writeonly.scala.util.ToBoolean
import pl.writeonly.babel.dtos.RelationsDto
import javax.jms.ConnectionFactory
import javax.annotation.Resource
import javax.jms.Session
import javax.jms.Topic
import javax.jms.DeliveryMode
import pl.writeonly.babel.parsers.LernuParser
import pl.writeonly.babel.daos.DaoCsv
import pl.writeonly.babel.remote.JmsQueue
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.remote.JmsDestination
import pl.writeonly.babel.util.Bean

/**
 * czyta plik cvs i zamienia go na listę dto
 * listę dto zamienia na listę list relacji
 */
@org.springframework.stereotype.Component
class LernuBean extends Bean with ToBoolean with Genie {
  implicit def block2completionHandler[T](block: Response => T) = new AsyncCompletionHandler[T]() {
    def onCompleted(response: Response) = block(response)
  }
//  @Resource var daoCsv: DaoCsv = _
//  @Resource var dao: DaoCrud = _
  @Resource var lernuParser: LernuParser = _

  val client = new AsyncHttpClient()

//  def read(string: String) = {
//    val arrays = daoCsv.read(classOf[LernuQuery], string)
//  }

  /*
 * trzeba stworzyć aktora żeby ładował relacje do kolekcji
 */

  private def searchAsyn(word: String, lang: Lang = "eo", it: Lang, langs: List[Lang] = null) {
    try {
      val url = lernuParser.urlBuild(word, lang, it)
      client.prepareGet(url).execute {
        response: Response =>
          //map(_.toInt).toList
          var step = 0
          var key = ""
          var value = ""
          val relations = response.getResponseBody.lines.foldLeft(new ListBuffer[Relation])(
            (buffer, line) => {
              step match {
                case 0 => key = line
                case 1 => value = line
                case 2 =>
                  try { buffer ++= lernuParser.toRelations(key, value, lang) }
                  catch { case e: Exception => logger error (key + " " + value + " " + lang, e) }

              }
              step = (step + 1) % 3
              buffer
            }
          )
          logger debug "relations => " + relations
          jmsDestination ! relations.toList.asInstanceOf[Serializable]

          if (langs) searchAsynAll(relations.map(_.value.core).toList, it, langs)
      }
    }
    catch { case e: Exception => logger error (e.getMessage, e) }
  }

  private def searchAsynIf(word: String, lang: Lang, langs: List[Lang]) {
    if (lang.value == "eo") langs.foreach(searchAsyn(word, lang, _))
    else searchAsyn(word, lang, "eo", langs)

  }

  def searchAsynAll(words: List[String], langFrom: Lang, langs: List[Lang]) {
    words.foreach(_.safeRun ({ searchAsynIf(_, langFrom, langs) }))
  }

  def toRelation(list: List[Array[String]]) = {

    val relations = list.foldLeft(new ListBuffer[Relation])((relations, array) => {
      val key = new Word
      val value = new Word
      val relation = new Relation(key, value)

      //TODO miejsce na przetworzenie

      relations += relation
    })
    relations
  }

}
