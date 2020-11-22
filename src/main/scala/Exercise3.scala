import zio.console.Console
import zio.{ExitCode, Task, URIO, ZIO}

object Exercise3 extends zio.App{

  def readFile(file: String): String = {
    val source = scala.io.Source.fromFile(file)
    try source.getLines.mkString finally source.close()
  }

  def readFileZio(file: String): Task[String] = ZIO.effect(readFile(file))

  def writeFile(file: String, text: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(file))
    try pw.write(text) finally pw.close
  }

  def writeFileZio(file: String, text: String): Task[Unit] = {
    ZIO.effect(writeFile(file, text))
  }

  //E3
  def copyFile(source: String, dest: String): Unit = {
    val contents = readFile(source)
    writeFile(dest, contents)
  }

  def copyFileZio(source: String, dest: String): ZIO[Any, Throwable, Unit] = {
    ZIO.effect(readFile(source)).flatMap(contents => ZIO.effect(writeFile(dest, contents)))
  }

  val toRun = copyFileZio("/home/leonid/Programs/zio-exercixses/build.sbt","/home/leonid/Programs/zio-exercixses/test2.txt")

  def run(args: List[String]): URIO[Any with Console, ExitCode] =
    toRun.exitCode

}
