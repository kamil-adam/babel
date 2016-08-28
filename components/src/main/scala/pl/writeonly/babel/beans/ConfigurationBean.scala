package pl.writeonly.babel.beans

import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.Lang
import com.weiglewilczek.slf4s.Logging
import javax.annotation.Resource
import org.springframework.stereotype.Service

@Service
class ConfigurationBean extends Logging {
  @Resource(name = "daoImpl") var dao: DaoCrud = _

  def merge {
    val langs = List(new Lang("de"), new Lang("en"), new Lang("eo"), new Lang("fr"), new Lang("pl"))

    logger debug langs.map(_.value).mkString(",")
    
    dao.mergeAll(langs)

  }
}