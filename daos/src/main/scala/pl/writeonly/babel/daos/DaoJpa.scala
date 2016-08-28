//package pl.writeonly.babel.daos
//
//import org.springframework.orm.jpa.support.JpaDaoSupport
//
//abstract class DaoJpa extends JpaDaoSupport with DaoCrud{
//  def persist[T](t: T): T = {
//    //getJpaTemplate().persist(t)
//    t
//  }
//  def find[T](t: T): T 
//  def find[T](id: Int): T
//  def update[T](t: T): T
//  def delete[T](t: T): T
//
//}