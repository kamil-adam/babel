package pl.writeonly.babel.swt.faces

import com.weiglewilczek.slf4s.Logging
import javax.annotation.Resource
import org.springframework.stereotype.Controller
import pl.writeonly.babel.beans._
import pl.writeonly.babel.daos.DaoCsv
import pl.writeonly.babel.dtos.LernuQuery
import pl.writeonly.babel.swt.providers.DefaultTableProvider
import javax.annotation.PostConstruct
import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import org.eclipse.jface.viewers.TableViewer
import pl.writeonly.babel.swt.cards.UseCard
import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.Facade

@org.springframework.stereotype.Controller
class UseFace extends Face {
  @Resource var dialogFace: Facade = _
  @Resource var sideService: SideBean = _
  @Resource var csvService: CsvBean = _
  @Resource var daoCsv: DaoCsv = _
  @Resource var lernuQueryTP: DefaultTableProvider[LernuQuery] = _

  @PostConstruct
  def init() {
    lernuQueryTP.array = Array[LernuQuery](new LernuQuery("UseFace", "UseFace", "UseFace"))
  }

  def apply() = UseCard(this)

  def viewSideItem = sideService.findName.toArray
  def open() = {
    logger.debug("open");
    val fileName = dialogFace.open.open();
    val readed = csvService.readFile(fileName)
    logger.debug("fileName " + fileName);
    lernuQueryTP.array = daoCsv.read(classOf[LernuQuery], fileName).toArray[LernuQuery]
  }

  def addLernuQueries(viewer: TableViewer) = {
    val list = open[LernuQuery](classOf[LernuQuery])
    list.foreach((query: LernuQuery) => {
      viewer.add(query)
    })
  }

  /**
   * wczytuje plik cvs, konwertuje na klasy i wyświetla
   */
  def open[T](clazz: Class[T]) = {
    val fileName = dialogFace.open.open();
    daoCsv.read(clazz, fileName)
  }
  def use() = {
    logger.debug("use")
    //sideService.

  }
}