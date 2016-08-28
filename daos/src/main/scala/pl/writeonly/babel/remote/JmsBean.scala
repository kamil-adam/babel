//package pl.writeonly.babel.remote
//
//import org.apache.activemq.broker.BrokerService
//import org.apache.activemq.broker.BrokerService
//import javax.annotation.PostConstruct
//import org.apache.activemq.broker.BrokerService
//
//@org.springframework.stereotype.Service
//class JmsBean {
//  val broker = new BrokerService();
//  @PostConstruct
//  def init {
//    broker.addConnector("tcp://localhost:61616");
//    broker.start();
//  }
//}