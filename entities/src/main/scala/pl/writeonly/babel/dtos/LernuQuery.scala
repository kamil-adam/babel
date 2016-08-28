package pl.writeonly.babel.dtos

object LernuQuery {
  val searchi = "http://en.lernu.net/cgi-bin/serchi.pl"
}

case class LernuQuery(
  modelo: String, delingvo: String, allingvo: String) {
  //def this() = this ("", "", "")

  def toQuery = "modelo=" + modelo + "&delingvo=" + delingvo + "&allingvo=" + allingvo +
    "&prioritato=0&starto=0&bobeloid=undefined&dishaki=true&hazardo=UXxqugKH"
  def toUrl = LernuQuery.searchi + toQuery
}