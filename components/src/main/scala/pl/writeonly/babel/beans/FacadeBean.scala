package pl.writeonly.babel.beans

import javax.annotation.Resource

import pl.writeonly.babel.entities.Record
import pl.writeonly.babel.entities.Relation
import pl.writeonly.babel.entities.User
import pl.writeonly.babel.entities.Word
import pl.writeonly.scala.util.Genie
import pl.writeonly.scala.util.OrElse
import javax.inject.Singleton
import javax.inject.Inject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import java.beans.ConstructorProperties
import com.weiglewilczek.slf4s.Logging

@org.springframework.stereotype.Service
class FacadeBean extends Logging with OrElse {
  @Resource(name = "userBean") var userService: UserBean = _
  @Resource(name = "wordBean") var wordService: WordBean =_
  @Resource(name = "relationBean") var relationService: RelationBean = _
  @Resource(name = "recordBean") var recordService: RecordBean = _ 
  
  var sideService: SideBean = _
  var user: User = _
  var heap: Heap = _
  var words: List[Word] = _
  var relations: List[Relation] = _
  var records: List[Record] = _
  var tuple: Tuple2[Word, Array[Word]] = _

  def changePassword(oldPassword: String, newPassword: String, confirm: String) = {
    userService.update(user, oldPassword, newPassword, confirm)
  }
  //metody dostepu do masowych danych
  def viewWords = {
    //    val iterable = wordService.read
    //    words = List(iterable: _*)
    words = wordService.find
    words
  }
  def addWord(word: Word) {
    val temp = wordService.persist(word)
    words ::= temp
    words
  }
  def editWord(word: Word) = wordService.update(word)

  def viewRelations = {
    //    relations = List(relationService.read.toArray: _*)
    relations = relationService.find
    relations
  }
  def addRelation(relation: Relation) = {
    val temp = relationService.persist(relation)
    relations ::= temp
    relations
  }
  def editRelation(relation: Relation) = relationService.merge(relation)

  def viewRecord = {
    //    records = List(relationService.read: _*)
    records = recordService.find(user)
    records
  }
  def addRecord(record: Record) = {
    val temp = recordService.persist(record)
    records ::= temp
    records
  }

  //metody logiki 
  def play = {
    //heap = 
  }

  def viewKey = {
    tuple = tuple orElse heap.get
    tuple._1
  }
  def viewValues = {
    tuple = tuple orElse heap.get
    tuple._2
  }
  def checkValue(index: Int) = {
    heap.checkValue(tuple._2(index))
  }

  def next() = {
    tuple = heap.get
    tuple
  }

  def checkValue(value: Word) = {
    heap.checkValue(value)
  }
  def lift = {
    heap.checkValue(null)
  }

  def viewSide = sideService.find
  def viewSideItem = sideService.findName.toArray
}