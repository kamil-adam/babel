package pl.writeonly.babel.entities

class Side(var name: String, var url: String, var langs: List[Lang]) extends Entity {
  def this() = this ("", "", null)
}