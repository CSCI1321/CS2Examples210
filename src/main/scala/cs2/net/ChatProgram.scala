package cs2.net

import java.lang.Thread
import java.net._
import java.io._
import org.scalactic.Explicitly

object ChatProgram {

  def createInputThread(sock:Socket):Thread = {
    new Thread {
      override def run():Unit = {
        val is = new BufferedInputStream(sock.getInputStream)

        var msg = ""
        while(!sock.isClosed && msg != "EXIT\n") {
          msg = ""
          while(!sock.isClosed && is.available == 0) Thread.sleep(10)
          var lastChar = 'Z'
          while(!sock.isClosed && is.available > 0 && lastChar != '\n') {
            lastChar = is.read.toChar
            msg += lastChar
          }
          println("<< " + msg)
        }
        is.close()
        sock.close()
        println("after input while")

      }
    }
  }

  def createOutputThread(sock:Socket):Thread = {
    new Thread {
      override def run():Unit = {
        val os = new BufferedOutputStream(sock.getOutputStream)
        val is = new BufferedInputStream(System.in)

        var msg = ""
        while(!sock.isClosed && msg != "EXIT\n") {
          msg = ""
          while(!sock.isClosed && is.available == 0) Thread.sleep(10)
          var lastChar = 'Z'
          while(!sock.isClosed && is.available > 0 && lastChar != '\n') {
            lastChar = is.read.toChar
            msg += lastChar
          }
          println(">> " + msg)
          if(!sock.isClosed()) {
            for(c <- msg) os.write(c)
            os.flush()
          }
        }
        os.close()
        sock.close()
        println("after output while")

      }
    } 
  }


  def main(args:Array[String]):Unit = {
    //args(0) = server | client
    //args(1) = port number
    //args(2) = hostname (only for client)

    var sock:Socket = null
    var ss:ServerSocket = null
    if(args(0) == "server") {
      ss = new ServerSocket(args(1).toInt)
      sock = ss.accept()
    } else if(args(0) == "client") {
      sock = new Socket(args(2), args(1).toInt)
    } else {
      println("Incorrect arguments")
      sys.exit()
    }
    val inThread = createInputThread(sock)
    val outThread = createOutputThread(sock)
    inThread.start
    outThread.start
    inThread.join
    outThread.join
    sock.close
    if(ss != null) ss.close 


  }
}

