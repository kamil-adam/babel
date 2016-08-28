package pl.writeonly.babel.beans

import pl.writeonly.babel.daos.DaoCrud
import javax.annotation.Resource
import pl.writeonly.babel.entities.AbstractValue
import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.entities.Part

@org.springframework.stereotype.Service
class ValueBean(@Resource(name = "daoImpl") val dao: DaoCrud) {

  //
  def langs = dao.find(classOf[Lang])
  def persistLang(lang : Lang) = dao.persist(lang)
  def persistLangs(langs: List[Lang]) = dao.persistAll(langs)
  def deleteLangs(langs: List[Lang]) = dao.deleteAll(langs)
  def removeLangs(langs: List[Lang]) = dao.removeAll(langs)
  //
  def parts = dao.find(classOf[Part])
  def persistParts(parts: List[Part]) = dao.persistAll(parts)
  def deleteParts(parts: List[Part]) = dao.deleteAll(parts)
  def removeParts(parts: List[Part]) = dao.removeAll(parts)
}