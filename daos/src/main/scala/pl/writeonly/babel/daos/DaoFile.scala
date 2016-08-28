package pl.writeonly.babel.daos

trait DaoFile {
  def read[T](clazz: Class[T]): List[T]
  def write[T](list: List[T])
}