package pl.writeonly.babel.mediators

import pl.writeonly.babel.beans.FacadeBean
import com.google.inject.Guice
import com.google.inject.AbstractModule
import pl.writeonly.babel.beans.RecordBean
import com.google.inject.Scopes;
import pl.writeonly.babel.daos.DaoCrud

object AppInjector extends AppLogging {
  val injector = Guice.createInjector(new CustomModule())
  val facade = injector.getInstance(classOf[FacadeBean])
}

class CustomModule extends AbstractModule {
  /**
   * Konfiguracja modułu.
   */
  def configure = {
    bind(classOf[RecordBean]).to(classOf[RecordBean]).in(Scopes.SINGLETON);
    //bind(classOf[Dao]).to(classOf[DaoJdo]).in(Scopes.SINGLETON);
  }
}