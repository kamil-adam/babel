package pl.writeonly.babel.swt.faces

import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct
import javax.annotation.Resource
import pl.writeonly.babel.swt.providers.LangTableProvider
import com.weiglewilczek.slf4s.Logging
import pl.writeonly.babel.beans.SideBean
import pl.writeonly.babel.beans.ValueBean
import org.eclipse.jface.viewers.TableViewer
import com.google.gson._
import pl.writeonly.babel.entities.Lang
import org.eclipse.swt.widgets.Table
import pl.writeonly.babel.beans.TranslateBean
import scala.collection.mutable._
import pl.writeonly.babel.swt.providers.DefaultTableProvider
import pl.writeonly.babel.entities.LangRadio
import pl.writeonly.babel.swt.providers._
import org.eclipse.swt.widgets.Combo
import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.cards.TranslateCard

@org.springframework.stereotype.Controller
class TranslateFace extends Face {
  @Resource var langTableProvider: LangTableProvider = _
  @Resource var sideService: SideBean = _
  @Resource var translateService: TranslateBean = _
  @Resource var valueService: ValueBean = _

  @PostConstruct
  private def init {
    langTableProvider.array = langs.toArray
  }

  def apply() = TranslateCard(this)

  def langs(): List[Lang] = valueService.langs
  //def add(viewer: TableViewer):Unit = viewer.add(langs().toArray)

  def translate(text: String, combo: Combo, table: Table) = {
    val words = text.split("\n").toList
    val lang = new Lang(combo.getText())
    logger debug "translate lang => " + lang
    val langs = table.getItems.filter(_.getChecked()).map(_.getData().asInstanceOf[Lang]).toList
    //val langs = (for (i <- table.getItems; if i.getChecked()) yield i.getData().asInstanceOf[Lang]).toList
    val relations = translateService.translateAsyn(words, lang, langs)

  }
}