package pl.writeonly.babel.swt.cards

import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Composite

import com.coconut_palm_software.xscalawt.XJFace.tableViewerBuilder
import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import com.coconut_palm_software.xscalawt.XScalaWT.button
import com.coconut_palm_software.xscalawt.XScalaWT.combo
import com.coconut_palm_software.xscalawt.XScalaWT.composite
import com.coconut_palm_software.xscalawt.XScalaWT.fillLayout
import com.coconut_palm_software.xscalawt.XScalaWT.horizontal
import com.coconut_palm_software.xscalawt.XScalaWT.onSelectionImplicit
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import com.coconut_palm_software.xscalawt.XScalaWT.tabItem
import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import com.weiglewilczek.slf4s.Logging

import pl.writeonly.babel.dtos.LernuQuery
import pl.writeonly.babel.swt.faces.UseFace
import pl.writeonly.scala.swt.layout.BorderData
import pl.writeonly.xscalawt.YScalaWT.borderLayoutScalars

object UseCard extends Logging {
  def apply(use: UseFace) = {
    use.lernuQueryTP.array = Array[LernuQuery](new LernuQuery("UseCard", "UseCard", "UseCard"))
    var self: Composite = null
    var lernuQueries: TableViewerBuilder[LernuQuery] = null

    try {
      composite(
        self = _,
        tabItem("Import"),
        borderLayoutScalars(),
        tableViewerBuilder[LernuQuery](
          lernuQueries = _,
          _.createColumn(_.modelo.toString, "modelo").sortable.useAsDefaultSortColumn.build(),
          _.createColumn(_.delingvo.toString, "delingvo").sortable.sortable.build(),
          _.createColumn(_.allingvo.toString, "allingvo").sortable.sortable.build())(
            _.setContentProvider(use.lernuQueryTP),
            _.setInput()
          ),
        composite (
          fillLayout(horizontal),
          layoutData = BorderData.SOUTH,
          fillLayout(horizontal),
          button("Open", { e: SelectionEvent => { use.open } }),
          button("Open", { e: SelectionEvent =>  use.addLernuQueries(lernuQueries.viewer) }),
          //combo(_.setItems(use.viewSideItem)),
          combo(),
          button("Import", { e: SelectionEvent => { use.use } })
        )
      )
    }
    catch {
      case e: Exception => {
        logger.error(e.getMessage, e)
        composite(tabItem("Import"))
      }
    }
  }
}