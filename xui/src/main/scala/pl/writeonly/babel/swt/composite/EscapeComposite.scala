package pl.writeonly.babel.swt.composite

import com.coconut_palm_software.xscalawt.XScalaWT.button
import com.coconut_palm_software.xscalawt.XScalaWT.composite
import com.coconut_palm_software.xscalawt.XScalaWT.fillLayout
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import com.coconut_palm_software.xscalawt.XScalaWT.vertical

object EscapeComposite {
  
  def apply() = {
    
    composite(
        fillLayout(vertical),
        button("Insert" ),
        button("Delete"),
        button("Paste"),
        button("Find"),
        button("")
        )
  }
}