package pl.writeonly.babel.beans

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import pl.writeonly.babel.mediators._
import pl.writeonly.babel.entities.Lang
import com.weiglewilczek.slf4s.Logging

class LernuSuite extends FunSuite with BeforeAndAfter with Logging {
  val lernuService = AppContext.bean[LernuBean]("lernuBean")

  before {
  }

  test("lernuService.searchs"){
    
    logger.debug("lernuService.searchs")
    lernuService.searchAsynAll(List("eo"), new Lang("eo"), List (new Lang("eo")))
    assert(1 == 2)
  }
}