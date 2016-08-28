package pl.writeonly.babel.swt.faces

import org.eclipse.swt.widgets.Display
import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import javax.annotation.Resource
import pl.writeonly.babel.beans.ValueBean
import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.FaceBuilder
import pl.writeonly.babel.swt.cards.LangCard
import pl.writeonly.babel.swt.providers.LangTableProvider
import pl.writeonly.scala.util.SingleAbstractMethod._
import org.eclipse.swt.widgets.Text
import pl.writeonly.babel.entities.Lang.stringToLang

@org.springframework.stereotype.Controller
class LangFace extends FaceBuilder[Lang] {
  @Resource var langService: ValueBean = _
  @Resource var tableProvider: LangTableProvider = _

  var insertText: Text = _
  var findText: Text = _

  def apply() = LangCard(this)

  def insert = add(insertText.getText())
  def delete = { val checked = check(); langService.deleteLangs (checked); removeAll(checked) }

  def find = clear.addAll(langService.langs)

  protected def clear() = { builder.table.clearAll(); this }

  protected def addAll(list: List[Lang]) = {
    val filtred = list.filter(!tableProvider.array.contains(_))
    tableProvider.add(list.toArray)
    Display.getDefault.syncExec { list.foreach(builder.viewer.add(_)) }
    this
  }
  protected def add(lang: Lang) {
    if (!tableProvider.array.contains(lang)) {
      tableProvider.array ++= Array[Lang](lang)
      Display.getDefault.syncExec { builder.viewer.add(lang) }
      langService.persistLang(lang)
    }

  }
  protected def removeAll(list: List[Lang]) {
    //logger debug "removeAll list => " + list
  }
}