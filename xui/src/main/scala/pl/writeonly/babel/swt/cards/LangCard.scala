package pl.writeonly.babel.swt.cards

import pl.writeonly.babel.swt.faces.LangFace
import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import org.eclipse.jface.viewers.TableViewer
import pl.writeonly.babel.entities.Lang
import com.coconut_palm_software.xscalawt.XScalaWT._
import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import pl.writeonly.xscalawt.YScalaWT._
import org.eclipse.swt.events.SelectionEvent
import pl.writeonly.scala.swt.layout.BorderData
//import com.coconut_palm_software.xscalawt.XScalaWT._
//import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
//import pl.writeonly.xscalawt.YScalaWT._

object LangCard {
  def apply(face: LangFace) = {
    val result = composite(
      tabItem("Lang"),
      tableViewerBuilderCheck[Lang](
        face.builder = _,
        _.createColumn(_.value, "Value").sortable.useAsDefaultSortColumn.build())(
          _.setContentProvider(face.tableProvider),
          _.setInput()
        ),
      composite (
        composite(
          text(face.findText = _),
          button("Find", { e: SelectionEvent => face.find }),
          fillLayout(horizontal)
        ),
        composite(
          text(face.insertText = _),
          button("Insert", { e: SelectionEvent => face.insert }),
          fillLayout(horizontal)
        ),
        composite(
          button("Delete", { e: SelectionEvent => face.delete }),
          fillLayout(vertical)
        ),
        layoutData = BorderData.SOUTH,
        fillLayout(vertical)
      ),
      borderLayoutScalars()
    )
    result
  }
}