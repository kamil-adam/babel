package pl.writeonly.babel.beans

import com.weiglewilczek.slf4s.Logging
import javax.annotation.Resource
import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.entities.Relation
import scala.collection.mutable.ListBuffer

@org.springframework.stereotype.Service
class TranslateBean extends Logging {
  @Resource var lernuService: LernuBean = _

  def translateToEo() = {}
  def translateFromEo() = {}

  /*

   * z j1 na eo, z eo na j2, z j2 na oe
   */

  def translateAsyn(words: List[String], lang: Lang, langs: List[Lang]) {
    //words.foreach(lernuService.searchAsynAll(_,langFrom,langs))
    lernuService.searchAsynAll(words, lang, langs)
  }

}