package pl.writeonly.babel.swt.cards

import org.eclipse.jface.viewers.CellEditor
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Combo
import org.eclipse.swt.widgets.Text

import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import com.coconut_palm_software.xscalawt.XScalaWT.button
import com.coconut_palm_software.xscalawt.XScalaWT.combo
import com.coconut_palm_software.xscalawt.XScalaWT.composite
import com.coconut_palm_software.xscalawt.XScalaWT.fillLayout
import com.coconut_palm_software.xscalawt.XScalaWT.onSelectionImplicit
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import com.coconut_palm_software.xscalawt.XScalaWT.tabItem
import com.coconut_palm_software.xscalawt.XScalaWT.vertical
import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import com.weiglewilczek.slf4s.Logging

import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.swt.faces.TranslateFace
import pl.writeonly.scala.swt.layout.BorderData
import pl.writeonly.xscalawt.YScalaWT.borderLayoutScalars
import pl.writeonly.xscalawt.YScalaWT.sashHorizontal
import pl.writeonly.xscalawt.YScalaWT.tableViewerBuilderCheck
import pl.writeonly.xscalawt.YScalaWT.textArea
import pl.writeonly.xscalawt.YScalaWT.textToString

object TranslateCard extends Logging {
  def apply(face: TranslateFace) = {
    var words: Text = null
    var lang: Combo = null
    var langs: TableViewerBuilder[Lang] = null
    logger debug "translate => " + face
    //logger debug "langs => " + langs
    var radioCellEditor: CellEditor = null
    var checkboxIt: Button = null
    var any: Any = null
    val result = composite(
      tabItem("Translate"),
      fillLayout(vertical),
      sashHorizontal(
        textArea(words = _),
        composite (
          borderLayoutScalars(),
          //          tableViewerBuilderCheuck[Lang](
          tableViewerBuilderCheck[Lang](
            langs = _,
            _.createColumn(_.value, "Langs").
              sortable.build())(
              _.setContentProvider(face.langTableProvider),
              _.setInput()
            ),
          combo(
            lang = _,
            layoutData = BorderData.NORTH,
            _.setItems(face.langs.map(_.value).toArray)
          ),
          button ("Translate", layoutData = BorderData.SOUTH,
            { e: SelectionEvent => face.translate(words, lang, langs.table) }
          )
        )
      )
    )

    result
  }
}