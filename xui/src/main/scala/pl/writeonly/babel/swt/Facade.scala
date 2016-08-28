package pl.writeonly.babel.swt

import org.eclipse.swt.widgets.FileDialog
import org.eclipse.swt.widgets.Text

import pl.writeonly.babel.entities.Lang
import pl.writeonly.babel.entities.Lang.stringToLang
import pl.writeonly.babel.entities.Part
import pl.writeonly.babel.entities.Part.stringToPart
import pl.writeonly.babel.entities.User

object Facade {
  implicit def textToPart(text: Text): Part = text.getText
  implicit def textToLang(text: Text): Lang = text.getText
}

@org.springframework.stereotype.Controller
class Facade {
  var open: FileDialog = _
  var save: FileDialog = _
  var user: User = _
}