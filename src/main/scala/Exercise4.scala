import zio.{ExitCode, URIO, ZIO}
import zio.console.Console

object Exercise4 extends zio.App{

  def printLine(line: String) = ZIO.effect(println(line))
  val readLine = ZIO.effect(scala.io.StdIn.readLine())
  val toRun1 = printLine("What is your name?").flatMap(_ =>readLine.flatMap(name =>printLine(s"Hello, ${name}!")))

  val toRun = for {
    _    <- printLine("What is your name?")
    name <- readLine
    res  <- printLine(s"Hello, ${name}!")
  } yield res

  def run(args: List[String]): URIO[Any with Console, ExitCode] =
    toRun.exitCode

}
