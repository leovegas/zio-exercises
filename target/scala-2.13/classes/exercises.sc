import zio.console.Console
import zio.{ExitCode, URIO, ZIO}

def printLine(line: String) = ZIO.effect(println(line))
val readLine = ZIO.effect(scala.io.StdIn.readLine())
val toRun1 = printLine("What is your name?").flatMap(_ =>readLine.flatMap(name =>printLine(s"Hello, ${name}!")))

val toRun = for {
  name <- printLine("What is your name?")
  _ <-    readLine
  _ <-    printLine(s"Hello, ${name}!")
} yield ()

object GroceryStore extends App {
  def run(args: List[String]): URIO[Any with Console, ExitCode] =
    toRun.exitCode}
