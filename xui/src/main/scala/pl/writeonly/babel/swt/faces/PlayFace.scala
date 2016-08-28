package pl.writeonly.babel.swt.faces

import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.cards.PlayCard

@org.springframework.stereotype.Controller
class PlayFace extends Face {
  def apply = PlayCard(this)
}