package pl.writeonly.babel.swt.cards

import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text

import com.coconut_palm_software.xscalawt.XScalaWT.button
import com.coconut_palm_software.xscalawt.XScalaWT.composite
import com.coconut_palm_software.xscalawt.XScalaWT.fillLayout
import com.coconut_palm_software.xscalawt.XScalaWT.label
import com.coconut_palm_software.xscalawt.XScalaWT.onSelectionImplicit
import com.coconut_palm_software.xscalawt.XScalaWT.string2setText
import com.coconut_palm_software.xscalawt.XScalaWT.tabItem
import com.coconut_palm_software.xscalawt.XScalaWT.text
import com.coconut_palm_software.xscalawt.XScalaWT.textPasswd
import com.coconut_palm_software.xscalawt.XScalaWT.vertical

import pl.writeonly.babel.swt.faces.ConfigurationFace
import pl.writeonly.xscalawt.YScalaWT.textToString

object ConfigurationCard {
  def apply(configuration: ConfigurationFace) = {

    //config
    var username: Text = null
    var password: Text = null
    var confirm: Text = null
    var state: Label = null

    composite(
      tabItem("Configuration"),
      composite(
        fillLayout(vertical),
        composite(
          fillLayout(),
          label("username"),
          text(username = _),
          label("password"),
          textPasswd(password = _),
          label("confirm"),
          textPasswd(confirm = _)),
        composite(
          fillLayout(),
          button("register", { e: SelectionEvent => configuration.register(username, password, confirm) }),
          button("login", { e: SelectionEvent => configuration.login(username, password) }),
          button("logout", { e: SelectionEvent => configuration.logout }),
          button("update", { e: SelectionEvent => configuration.merge })),
        composite(label(state = _))))

  }
}