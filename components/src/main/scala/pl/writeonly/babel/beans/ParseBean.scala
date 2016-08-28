package pl.writeonly.babel.beans

import java.io.BufferedReader
import java.io.FileReader
import javax.annotation.Resource
import pl.writeonly.babel.daos.DaoCsv
import au.com.bytecode.opencsv.CSVReader

@org.springframework.stereotype.Controller
class ParseBean {
  @Resource var daoCsv: DaoCsv = _
  def deen(fileName: String) = {
    //val reader = new BufferedReader(new FileReader(fileName))
    val reader = new CSVReader(new FileReader(fileName));
    val readed = reader.readAll()

    readed.foldLeft(new BufferList[_])((l, el) => { l})

  }
}