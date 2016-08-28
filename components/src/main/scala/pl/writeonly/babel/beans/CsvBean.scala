package pl.writeonly.babel.beans

import au.com.bytecode.opencsv.CSVReader
import java.io.FileReader
import org.springframework.stereotype.Service


@org.springframework.stereotype.Service
class CsvBean {
  def readFile(fileName: String) = new CSVReader(new FileReader(fileName)).readAll
}