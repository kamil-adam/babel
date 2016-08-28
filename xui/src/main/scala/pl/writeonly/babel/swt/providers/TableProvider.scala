package pl.writeonly.babel.swt.providers

import org.eclipse.jface.viewers.IStructuredContentProvider
import org.eclipse.jface.viewers.Viewer

import com.coconut_palm_software.xscalawt.ProviderImplicits

import pl.writeonly.babel.dtos.LernuQuery
import pl.writeonly.babel.entities._

abstract class AbstractTableProvider extends IStructuredContentProvider {
  def inputChanged(v: Viewer, oldInput: Object, newInput: Object) {}
  def dispose() {}
//  def add[T](added : Array[T]) {array ++= added}
}

@org.springframework.stereotype.Controller
class DefaultTableProvider[T <: AnyRef](var array: Array[T])
  extends AbstractTableProvider with ProviderImplicits[T] {
  def this() = this(null)
  def getElements(i: Object) = array
}

@org.springframework.stereotype.Controller
class LernuQueryTableProvider(var array: Array[LernuQuery])
  extends AbstractTableProvider with ProviderImplicits[LernuQuery] {
  def this() = this(new Array[LernuQuery](0))
  def getElements(i: Object) = array
}

@org.springframework.stereotype.Controller
class LangTableProvider(var array: Array[Lang])
  extends AbstractTableProvider with ProviderImplicits[Lang] {
  def this() = this(new Array[Lang](0))
  def getElements(i: Object) = array
  def add(added : Array[Lang]) {array ++= added}
}
@org.springframework.stereotype.Controller
class LangRadioTableProvider(var array: Array[LangRadio])
  extends AbstractTableProvider with ProviderImplicits[LangRadio] {
  def this() = this(new Array[LangRadio](0))
  def getElements(i: Object) = array
}
@org.springframework.stereotype.Controller
class WordTableProvider(var array: Array[Word])
  extends AbstractTableProvider with ProviderImplicits[Word] {
  def this() = this(new Array[Word](0))
  def getElements(i: Object) = array
  def add(added : Array[Word]) {array ++= added}
}

@org.springframework.stereotype.Controller
class RelationTableProvider(var array: Array[Relation])
  extends AbstractTableProvider with ProviderImplicits[Relation] {
  def this() = this(new Array[Relation](0))
  def getElements(i: Object) = array
  def add(added : Array[Relation]) {array ++= added}
}

@org.springframework.stereotype.Controller
class RecordTableProvider(var array: Array[Record])
  extends AbstractTableProvider with ProviderImplicits[Record] {
  def this() = this(new Array[Record](0))
  def getElements(i: Object) = array
  def add(added : Array[Record]) {array ++= added}
}





