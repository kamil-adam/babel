package pl.writeonly.babel.remote

import javax.annotation.Resource

trait Safed {
  @Resource var jmsDestination: JmsDestination = _
  def safe[T](f: Unit => Unit) { try { f() } catch { case e: RuntimeException => jmsDestination ! e } }

  implicit def any2Safed[T](a: T) = new AnyRef {
    def safeRun(f: T => Unit) { try { f(a) } catch { case e: RuntimeException => jmsDestination ! e } }
  }

}