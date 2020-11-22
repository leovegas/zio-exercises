import java.io.File

import zio.{ExitCode, Task, URIO, ZIO}
import zio.console.Console

object Exercise1 extends zio.App{

  def readFile(file: String): String = {
    val source = scala.io.Source.fromFile(new File(file))
    try source.getLines.mkString finally source.close()
  }

  def readFileZio(file: String): Task[String] = ZIO.effect(readFile(file))
  val toRun = readFileZio("/home/leonid/Programs/zio-exercises/build.sbt")

  def run(args: List[String]): URIO[Any with Console, ExitCode] =
    toRun.exitCode

}
