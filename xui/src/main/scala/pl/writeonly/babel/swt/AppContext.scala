package pl.writeonly.babel.swt

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
object AppContext extends App {
  val context: ApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml")
  def appWindow = context.getBean("appWindow").asInstanceOf[AppWindow]
  def mainLoop = context.getBean("mainLoop").asInstanceOf[MainLoop]
}



