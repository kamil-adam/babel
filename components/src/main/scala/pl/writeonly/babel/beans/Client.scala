package pl.writeonly.babel.beans

import com.weiglewilczek.slf4s.Logging
import java.io.IOException
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpStatus
import org.apache.commons.io.IOUtils

class Client extends  Logging{
  val client = new HttpClient
  val method = new GetMethod("http://structureddata.wikispaces.com/Test");
  try {
    if (HttpStatus.SC_OK == client.executeMethod(method)) {
      logger.info(IOUtils.toString(method.getResponseBodyAsStream()));
    } else {
      throw new IOException("Unable to load page, error " + method.getStatusLine());
    }
  } finally {
    method.releaseConnection();
  }
}