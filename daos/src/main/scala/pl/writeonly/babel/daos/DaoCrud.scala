package pl.writeonly.babel.daos;

import com.weiglewilczek.slf4s.Logging
import java.math.BigInteger
trait DaoCrud  {
  def persist[T](t: T): T
  def persistAll[T](t: List[T]): List[T]
  def find[T](c: Class[T]): List[T]
  def get[T](c: Class[T], id: Int): T
  def get[T](c: Class[T], id: BigInteger): T
  def find[T](c: Class[T], s: String): List[T]
  def findOne[T](c: Class[T], s: String): T
  def merge[T](t: T): T
  def mergeAll[T](t: List[T]): List[T]
  def delete[T](t: T): Unit
  def deleteAll[T](t: List[T]): Unit
  def remove[T](t: T): Unit
  def removeAll[T](t: List[T]): Unit

}