package pl.writeonly.babel.swt

import org.eclipse.swt.widgets.Shell
import pl.writeonly.xscalawt.YScalaWT._
import pl.writeonly.babel.swt.faces.PlayFace
import pl.writeonly.babel.swt.faces.BrowserFace
import pl.writeonly.babel.swt.faces.RecordFace
import pl.writeonly.babel.swt.faces.TranslateFace
import pl.writeonly.babel.swt.faces.UseFace
import pl.writeonly.babel.swt.faces.ConfigurationFace
import pl.writeonly.babel.swt.faces.RelationFace
import pl.writeonly.babel.swt.faces.WordFace
import javax.annotation.Resource
import com.weiglewilczek.slf4s.Logging
import org.eclipse.swt.widgets.Text
import pl.writeonly.babel.entities.Lang
import com.coconut_palm_software.xscalawt.XScalaWT._
import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._
import pl.writeonly.scala.swt.layout.BorderData
import org.eclipse.swt.widgets.Composite
import pl.writeonly.babel.swt.faces.LangFace

//import com.coconut_palm_software.xscalawt.XScalaWT._
//import com.coconut_palm_software.xscalawt.XScalaWT.Assignments._

trait MainWindow extends Runnable with Logging {

  @Resource var browserFace: BrowserFace = _
  @Resource var configurationFace: ConfigurationFace = _
  @Resource var langFace : LangFace = _
  @Resource var playFace: PlayFace = _
  @Resource var recordFace: RecordFace = _
  @Resource var relationFace: RelationFace = _
  @Resource var translateFace: TranslateFace = _
  @Resource var useFace: UseFace = _
  @Resource var wordFace: WordFace = _

  @Resource var mainFace: Facade = _
  @Resource var consumer: JmsConsumer = _

  def run() = {
    consumer.start
  }

  var string = "MainWindow"

  def apply(parent: Shell) = {
    contentDefault(
            fileDialogOpen(mainFace.open = _),
            fileDialogSave(mainFace.save = _),
      string,
      tabFolder(
        configurationFace(),
        translateFace(),
        relationFace(),
        recordFace(),
        playFace(),
        useFace(),
        langFace(),
        wordFace(),
        browserFace()
      ))(parent)
  }

}

  /* potrzebne będzie menu, toolbar, stack/card, pasek stanu,
   * karty logowanie/rejstracja, edycja słów, edycja, edycja relacji/tworzenie 
   * 
   * 
   */