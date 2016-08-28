package pl.writeonly.babel.remote

import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Component
import com.weiglewilczek.slf4s.Logging
import javax.annotation.Resource
import javax.jms.Destination
import javax.jms.Message
import javax.jms.Session
@Component
class JmsSupport extends Logging {

  @Resource(name = "senderJmsTemplate") var template: JmsTemplate = _

  implicit def block2createMessage[T](session: Session, block: Session => Message) = new MessageCreator() {
    def createMessage(session: Session): Message = block(session)
  }
}