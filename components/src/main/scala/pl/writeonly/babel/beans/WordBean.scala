package pl.writeonly.babel.beans

import org.springframework.stereotype.Service
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.Word
import javax.annotation.Resource

@org.springframework.stereotype.Service
class WordBean(@Resource(name = "daoImpl") val dao: DaoCrud) {
  val clazz = classOf[Word]
  def persist(word: Word) = dao.persist(word)
  def persistAll(words: List[Word]) = dao.persist(words)
  def find = dao.find(clazz);
  def update(word: Word) = dao.merge(word);
}