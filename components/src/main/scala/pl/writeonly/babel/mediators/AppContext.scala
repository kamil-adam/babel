package pl.writeonly.babel.mediators

import org.springframework.stereotype.Component
import pl.writeonly.babel.beans.FacadeBean
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import com.weiglewilczek.slf4s.Logging

object AppContext extends AppLogging {
  val context: ApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml")
  val facadeSevice = context.getBean("facadeBean").asInstanceOf[FacadeBean]
  def bean[T](string:String) = context.getBean(string).asInstanceOf[T]
}
