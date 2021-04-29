package cs2.io

import java.io._

object StreamStuff {

  def copyStream(in:InputStream, out:OutputStream):Unit = {
    while(in.available() > 0) {
      out.write(in.read)
    }
  }

  def main(args:Array[String]):Unit = {

    val infile = new BufferedInputStream(new FileInputStream("tempest.txt"))
    val outfile = new BufferedOutputStream(new FileOutputStream("copy.txt"))
    copyStream(infile, outfile)
    infile.close()
    outfile.close()

  }
}