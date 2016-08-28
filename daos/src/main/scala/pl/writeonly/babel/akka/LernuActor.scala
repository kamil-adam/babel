package pl.writeonly.babel.akka

import akka.event.LoggingReceive
import com.ning.http.client.{ AsyncCompletionHandler, AsyncHttpClient, Response }
import akka.actor._
import pl.writeonly.babel.entities._
import java.io.BufferedReader
import java.net.URL
import java.io.InputStreamReader
import scala.collection.mutable.ListBuffer
import pl.writeonly.babel.exception.MatchException
import pl.writeonly.babel.parsers.LernuParser


case class LernuQuery(modelo: String, delingvo: String = "eo", allingvo: String = "eo")
case class LernuQueries(modelo: String, delingvo: String = "eo", allingvo: List[Lang])
case class LernuResponse(relations: List[Relation])

//case class RandomOrgServerResponse(randomNumbers: List[Int])

class LernuActor extends Actor with ActorLogging {
  val serchi_pl = "http://en.lernu.net/cgi-bin/serchi.pl"

  val client = new AsyncHttpClient()
  val lernuParser = new LernuParser()

  override def postStop() {
    client.close()
  }

  implicit def block2completionHandler[T](block: Response => T) = new AsyncCompletionHandler[T]() {
    def onCompleted(response: Response) = block(response)
  }

  /**
   * stąd powinienem dostać listę Relacji
   */
  def receive = LoggingReceive {
    case LernuQuery(modelo, delingvo, allingvo) =>
      val this_sender = sender
      val lang = new Lang(allingvo)
      val url = serchi_pl +
        "?modelo=" + modelo +
        "&delingvo=" + delingvo +
        "&allingvo=" + allingvo +
        "&prioritato=0&starto=0&bobeloid=undefined&dishaki=true&hazardo=UXxqugKH"
      client.prepareGet(url).execute {
        response: Response => execute(response, this_sender, lang)
      }
  }

  def execute(response: Response, this_sender: ActorRef, lang: Lang) = {
    log debug "execute"
    var step = 0;
    var first: String = null

    val lambda = createf _
    val relations = response.getResponseBody.lines.foldLeft(new ListBuffer[Relation])(
      (list, line) => {
        step match {
          case 0 => first = line
          case 1 =>
            try {
              list ++= lernuParser.toRelations(first, line, lang)
              //logger debug "createf list size => " + list.size
            }
            catch { case e: Exception => log error (e.getMessage, e) }
          case 2 => {}
        }
        step = (step + 1) % 3
        list
      }
    )
    this_sender ! LernuResponse(relations.toList)
  }

  def lambda(list: ListBuffer[Relation], lang: Lang) {
    log debug "createf"
    val buffer = new StringBuilder
    var step = 0;
    var first: String = null
    val test = ((line: String) => {
      //logger debug "test"
    })
    //test
    //list

  }

  def createf(list: ListBuffer[Relation], lang: Lang) = {
    log debug "createf"
    val buffer = new StringBuilder
    var step = 0;
    var first: String = null
    val test = ((line: String) => {
      //logger debug "test"
      step match {
        case 0 => first = line
        case 1 => try {
          list ++= lernuParser.toRelations(first, line, lang)
          //logger debug "createf list size => " + list.size
        }
        catch { case e: Exception => log error (e.getMessage, e) }
        case 2 => {}
      }
      step = (step + 1) % 3
    })
    //test
    list
  }

  /**
   * szukanie wyrazu, zwraca listę tłumaczeń
   */
  //  def search(modelo: String, delingvo: String = "eo", allingvo: String = "eo") = {
  //    //logger debug "search"
  //    val list = new ListBuffer[Relation]
  //    val test = createf(list, delingvo)
  //    searchf(modelo, delingvo, allingvo, test)
  //    //logger debug "search list size => " + list.size
  //    list
  //  }

  def searchf(modelo: String, delingvo: String = "eo", allingvo: String = "eo", f: String => Unit) = {
    //logger debug "searchf"
    val url_string = serchi_pl +
      "?modelo=" + modelo +
      "&delingvo=" + delingvo +
      "&allingvo=" + allingvo +
      "&prioritato=0&starto=0&bobeloid=undefined&dishaki=true&hazardo=UXxqugKH"
    log debug "url_string => " + url_string
    val url = new URL(url_string);
    val in = new BufferedReader(new InputStreamReader(url.openStream()))
    var bool = true
    while (bool) {
      val line = in.readLine()
      bool = line != null
      if (bool) {
        f(line)
      }
    }
    in.close()
  }

}
