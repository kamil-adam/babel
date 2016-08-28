package pl.writeonly.babel.swt.faces

import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.cards.BrowserCard

@org.springframework.stereotype.Controller
class BrowserFace extends Face {
	def apply() = BrowserCard(this)
}