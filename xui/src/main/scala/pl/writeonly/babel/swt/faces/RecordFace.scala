package pl.writeonly.babel.swt.faces

import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Table

import com.coconut_palm_software.xscalawt.viewers.TableViewerBuilder
import com.weiglewilczek.slf4s.Logging

import javax.annotation.Resource
import pl.writeonly.babel.beans.RecordBean
import pl.writeonly.babel.beans.WordBean
import pl.writeonly.babel.entities.Record
import pl.writeonly.babel.swt.Face
import pl.writeonly.babel.swt.cards.RecordCard
import pl.writeonly.babel.swt.providers.RecordTableProvider
import pl.writeonly.babel.swt.providers.RelationTableProvider
import pl.writeonly.scala.util.SingleAbstractMethod._

@org.springframework.stereotype.Controller
class RecordFace extends Face {
  @Resource var recordService: RecordBean = _
  @Resource var tableProvider: RecordTableProvider = _

  var records: TableViewerBuilder[Record] = _
  var table: Table = _
  var viewer: TableViewer = _

  def apply() = RecordCard(this)

  def setAll {}
  def resetAll {}
  def insert = recordService.persistAll (checked())
  def find = try { addAll(recordService.find) } catch { case e: RuntimeException => logger error (e.getMessage(), e) }
  def remove {}
  def translate {}
  def addAll(recordAll: List[Record]) {
    tableProvider.add(recordAll.toArray)
    Display.getDefault.syncExec { recordAll.foreach(records.viewer.add(_)) }
  }
  def checked() = records.table.getItems.filter(_.getChecked()).map(_.getData().asInstanceOf[Record]).toList

}