package pl.writeonly.xscalawt

import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import com.coconut_palm_software.xscalawt.XScalaWT.button
import com.coconut_palm_software.xscalawt.XScalaWT.runEventLoop
import com.coconut_palm_software.xscalawt.XScalaWT.shell
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import pl.writeonly.scala.swt.layout.BorderData
import pl.writeonly.xscalawt.YScalaWT.borderLayoutScalars
import pl.writeonly.xscalawt.YScalaWT.textArea

object BorderLoop extends App {
  new BorderLoop().run
}

class BorderLoop  {
  def run {
    val window = shell(
      "BorderLoop",
      borderLayoutScalars(),
      button("west", layoutData = BorderData.WEST),
      button("east", layoutData = BorderData.EAST),
      button("north", layoutData = BorderData.NORTH),
      button("south", layoutData = BorderData.SOUTH),
      textArea("center"))
    runEventLoop(window)
  }
}

//@Repository, @Service, @Controller
//card
//composite
//controler
//component
//dao
//entity