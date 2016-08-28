package pl.writeonly.babel.apps

import java.util.Date
import com.weiglewilczek.slf4s.Logging
import pl.writeonly.babel.beans.LernuBean
import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.entities.Lang.stringToLang
import pl.writeonly.babel.mediators.AppContext
import pl.writeonly.babel.mediators.AppLogging

class LernuAsynApp extends AppLogging {
  AppContext.main(args)
  val lernuService = AppContext.bean[LernuBean]("lernuBean")

  def searchAsynAll() = {
    logger.debug("LernuAsynApp searchAsynAll")
    //
    val begin = new Date
    logger debug "eo eo"
    lernuService.searchAsynAll(List("vorto"), "eo", List(new Lang("eo")))
    //
    logger debug "eo _"
    lernuService.searchAsynAll(List("vorto"), "eo", List(new Lang("de")))
    lernuService.searchAsynAll(List("vorto"), "eo", List(new Lang("en")))
    lernuService.searchAsynAll(List("vorto"), "eo", List(new Lang("pl")))
    //
    logger debug "_ eo"
    lernuService.searchAsynAll(List(""), "de", List(new Lang("eo")))
    lernuService.searchAsynAll(List("word"), "en", List(new Lang("eo")))
    lernuService.searchAsynAll(List("słowo"), "pl", List(new Lang("eo")))
    //
    logger debug "_ _"
    //
    lernuService.searchAsynAll(List(""), "de", List(new Lang("en")))
    lernuService.searchAsynAll(List(""), "de", List(new Lang("pl")))
    //
    lernuService.searchAsynAll(List("word"), "en", List(new Lang("de")))
    lernuService.searchAsynAll(List("word"), "en", List(new Lang("pl")))
    //
    lernuService.searchAsynAll(List("słowo"), "pl", List(new Lang("de")))
    lernuService.searchAsynAll(List("słowo"), "pl", List(new Lang("en")))
    val end = new Date
    //
    logger debug "" + (end.getTime() - begin.getTime())
  }
  searchAsynAll
}