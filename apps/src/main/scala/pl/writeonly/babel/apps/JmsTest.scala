//package pl.writeonly.babel.apps
//
//import javax.jms.Destination
//import javax.jms.JMSException
//import javax.jms.TextMessage
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.jms.core.JmsTemplate
//import org.springframework.test.context.ContextConfiguration
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
//import pl.writeonly.babel.remote.JmsDestination
//import javax.annotation.Resource
//
//@RunWith(classOf[SpringJUnit4ClassRunner])
//@ContextConfiguration(locations = Array("classpath:test-common-context.xml"))
//class JmsTest {
//
//  @Resource(name = "senderJmsTemplate") var jmsTemplate: JmsTemplate = _
//  @Resource var destination: Destination = _
//  @Resource var jmsDestination: JmsDestination = _
//
//  @Test
//  def testJMSSender() {
//    jmsDestination ! "test"
//    val msg = jmsTemplate.receive(destination).asInstanceOf[TextMessage];
//    println("***********************");
//    try { println(msg.getText()) }
//    catch { case e: JMSException => e.printStackTrace() }
//    println("***********************");
//  }
//}  