import java.io.File

import zio.console.Console
import zio.{ExitCode, Task, URIO, ZIO}

object Exercise2 extends zio.App{

  def writeFile(file: String, text: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(file))
    try pw.write(text) finally pw.close
  }

  def writeFileZio(file: String, text: String): Task[Unit] = {
    ZIO.effect(writeFile(file, text))
  }

  val toRun = writeFileZio("/home/leonid/Programs/zio-exercixses/new.txt","test")

  def run(args: List[String]): URIO[Any with Console, ExitCode] =
    toRun.exitCode

}
