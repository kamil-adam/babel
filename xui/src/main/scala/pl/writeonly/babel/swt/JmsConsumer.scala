package pl.writeonly.babel.swt

import org.springframework.jms.core.JmsTemplate

import com.weiglewilczek.slf4s.Logging

import javax.annotation.Resource
import javax.jms.Connection
import javax.jms.Destination
import javax.jms.MessageConsumer
import javax.jms.Session
import pl.writeonly.scala.util.ToBoolean

@org.springframework.stereotype.Controller
class JmsConsumer extends Logging with ToBoolean {
  @Resource var listener: JmsListener = _
  @Resource(name = "consumerJmsTemplate") var template: JmsTemplate = _
  @Resource var destination: Destination = _
  var connection: Connection = _
  var session: Session = _
  var consumer: MessageConsumer = _
  var myId = "foo"

  def start() {
    //logger debug "start"
    val selector = "next = '" + myId + "'";
    connection = template.getConnectionFactory().createConnection ();
    synchronized { if (connection.getClientID ()) connection.setClientID (myId) }
    connection.start ();
    session = connection.createSession (true, Session.CLIENT_ACKNOWLEDGE);
    consumer = session.createConsumer (destination, selector, false);
    consumer.setMessageListener (listener);
  }

  def stop() {
    if (consumer) consumer.close ();
    if (session) session.close ();
    if (connection) connection.close ();
  }
}