package pl.writeonly.babel.swt

import org.eclipse.swt.widgets.Composite

import com.weiglewilczek.slf4s.Logging

import javax.annotation.Resource
import pl.writeonly.babel.remote.JmsDestination

trait Face extends Logging {
  @Resource var jmsDestination: JmsDestination = _
  def apply(): Composite => Composite
  def runtime(e: RuntimeException) {
    logger debug "runtime"
    logger error (e.getMessage, e);
    logger debug "runtime jmsDestination " + jmsDestination
    jmsDestination ! e.getMessage
  }
}