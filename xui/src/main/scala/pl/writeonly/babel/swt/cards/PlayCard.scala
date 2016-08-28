package pl.writeonly.babel.swt.cards

import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Label

import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import com.coconut_palm_software.xscalawt.XScalaWT.button
import com.coconut_palm_software.xscalawt.XScalaWT.composite
import com.coconut_palm_software.xscalawt.XScalaWT.label
import com.coconut_palm_software.xscalawt.XScalaWT.list
import com.coconut_palm_software.xscalawt.XScalaWT.onSelectionImplicit
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import com.coconut_palm_software.xscalawt.XScalaWT.tabItem

import pl.writeonly.babel.beans.FacadeBean
import pl.writeonly.babel.swt.faces.PlayFace
import pl.writeonly.scala.swt.layout.BorderData
import pl.writeonly.xscalawt.YScalaWT.borderLayoutScalars

object PlayCard {
  def apply(playFace : PlayFace) = {
    var facade: FacadeBean = null
    //play
    var key: Label = null
    var values: org.eclipse.swt.widgets.List = null
    composite(
      tabItem("Play"),
      borderLayoutScalars(),
      label(key = _, layoutData = BorderData.NORTH),
      list(values = _, { e: SelectionEvent => { facade.checkValue(values.getSelectionIndex) } }),
      composite(
        borderLayoutScalars(),
        button("Next", layoutData = BorderData.EAST, { e: SelectionEvent => { facade.next; key.setText(facade.viewKey.toString) } }),
        button("Esc", layoutData = BorderData.WEST, { e: SelectionEvent => {} }),
        layoutData = BorderData.SOUTH))

  }
}