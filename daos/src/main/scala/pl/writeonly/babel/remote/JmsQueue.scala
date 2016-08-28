package pl.writeonly.babel.remote

import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Component

import javax.annotation.Resource
import javax.jms.ConnectionFactory
import javax.jms.Message
import javax.jms.Queue
import javax.jms.Session

@Component
class JmsQueue extends JmsSupport {

  @Resource var queue: Queue = _

  def simpleSend() {
    template.send(this.queue, new MessageCreator() {
      def createMessage(session: Session): Message = session.createTextMessage("hello queue world")
    });
  }
}