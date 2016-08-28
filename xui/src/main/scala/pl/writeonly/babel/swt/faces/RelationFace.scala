package pl.writeonly.babel.swt.faces

import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Table
import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import com.weiglewilczek.slf4s.Logging
import javax.annotation.Resource
import pl.writeonly.babel.beans.RecordBean
import pl.writeonly.babel.beans.RelationBean
import pl.writeonly.babel.beans.WordBean
import pl.writeonly.babel.entities.Relation
import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.FaceBuilder
import pl.writeonly.babel.swt.cards.RelationCard
import pl.writeonly.babel.swt.providers.RelationTableProvider
import pl.writeonly.scala.util.SingleAbstractMethod._
import com.google.gson.Gson
import java.util.Arrays
import pl.writeonly.babel.swt.Facade

@org.springframework.stereotype.Controller
class RelationFace extends FaceBuilder[Relation] {
  @Resource var relationService: RelationBean = _
  @Resource var tableProvider: RelationTableProvider = _
  @Resource var recordService: RecordBean = _
  @Resource var parseService: ParseBean = _
  @Resource var facade: Facade = _

  def apply() = RelationCard(this)

  def insert = relationService.persistAll (check())
  //  def find = try { addAll(relationService.find) } catch { case e: RuntimeException => runtime(e)}
  def recordAll = recordService.toRecordAll(check())
  def find = clear.addAll(relationService.find)
  def toJson = logger debug new Gson().toJson(Arrays.asList(tableProvider.array))
  def deen = parseService.deen (facade.open.getFileName())

  protected def clear() = { builder.table.clearAll(); this }
  def addAll(list: List[Relation]) {
    logger debug "addAll list => " + list
    tableProvider.add(list.toArray)
    Display.getDefault.syncExec { list.foreach(builder.viewer.add(_)) }
  }
}