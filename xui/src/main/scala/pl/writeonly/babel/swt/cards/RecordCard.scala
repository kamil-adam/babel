package pl.writeonly.babel.swt.cards

import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import com.coconut_palm_software.xscalawt.XScalaWT._
import com.coconut_palm_software.xscalawt.XJFace.tableViewerBuilder
import com.coconut_palm_software.xscalawt.XScalaWT.composite
import com.coconut_palm_software.xscalawt.XScalaWT.fillLayout
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import com.coconut_palm_software.xscalawt.XScalaWT.tabItem
import com.coconut_palm_software.xscalawt.XScalaWT.vertical
import com.coconut_palm_software.xscalawt.ProviderImplicits
import org.eclipse.jface.viewers.IStructuredContentProvider
import org.eclipse.jface.viewers.Viewer
import pl.writeonly.babel.entities._
import pl.writeonly.babel.swt.faces.RecordFace
import pl.writeonly.scala.swt.layout.BorderData
import pl.writeonly.xscalawt.YScalaWT._
import org.eclipse.swt.events.SelectionEvent


object RecordCard {
  def apply(face: RecordFace) = {
    composite(
      tabItem("Record"),
      borderLayoutScalars(),
      tableViewerBuilder[Record](
        _.createColumn(_.relation.key.toString, "Key").sortable.useAsDefaultSortColumn.build(),
        _.createColumn(_.relation.value.toString, "Value").sortable.sortable.build(),
        _.createColumn(_.corect, "Corect").sortable.sortable.build(),
        _.createColumn(_.wrong, "Wrong").sortable.sortable.build())(
          _.setContentProvider(face.tableProvider),
          _.setInput()
        ),
      composite (
        button("Persist",
          //layoutData = BorderData.SOUTH,
          { e: SelectionEvent => face.insert }
        ),
        button("Find",
          //layoutData = BorderData.SOUTH,
          { e: SelectionEvent => face.find }
        ),
        fillLayout(horizontal),
        layoutData = BorderData.SOUTH
      )
    )
  }
}