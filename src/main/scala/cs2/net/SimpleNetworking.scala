package cs2.net

import java.net._
import java.io._

object SimpleNetworking {

  def getPageSource(link:String):String = {
    val url = new URL(link)
    val bis = new BufferedInputStream(url.openStream)
    var res = ""
    while(bis.available() > 0) {
      res += bis.read.toChar
    }
    res
  }

  def serverSide():Unit = {
    val ss = new ServerSocket(50000)
    val sock:Socket = ss.accept //block and wait for a client to connect
    val os = sock.getOutputStream
    for(i <- 1 to 10) {
      val c = scala.util.Random.nextPrintableChar()
      println(">> Sending: " + c)
      os.write(c)
    }
    os.flush()
    os.close()
    sock.close()
    ss.close()
  }
  def clientSide():Unit = {
    val sock = new Socket("localhost", 50000)
    val is = sock.getInputStream
    while(is.available() > 0) {
      println("<< Received: " + is.read.toChar)
    }
    is.close()
    sock.close()
  }

  def main(args:Array[String]):Unit = {
    println(getPageSource("https://www.google.com"))
  }

}