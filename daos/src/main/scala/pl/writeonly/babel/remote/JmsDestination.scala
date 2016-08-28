/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.writeonly.babel.remote

import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Component

import com.weiglewilczek.slf4s.Logging

import javax.annotation.Resource
import javax.jms.Destination
import javax.jms.Message
import javax.jms.Session
@Component
class JmsDestination extends JmsSupport with Logging {

  @Resource var destination: Destination = _

  private def send(f: Session => Message) {
    template.send (destination, new MessageCreator() {
      //def createMessage(session: Session): Message = f(session)
      def createMessage(session: Session): Message = { val m = f(session); m.setStringProperty("next", "foo"); m }
    });
  }

  def !(text: String) { send(_.createTextMessage(text)); logger debug "send text => " + text }
  def !(e: RuntimeException) { send(_.createTextMessage(e.getMessage())); logger error ("message => " + e.getMessage(), e) }
  def !(serializable: Serializable) { send(_.createObjectMessage(serializable)); logger debug "send serializable => " + serializable }

}
