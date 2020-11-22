import zio.console.Console
import zio.{ExitCode, Task, URIO, ZIO}

object Exercise5 extends zio.App {

  val random: Task[Int] = ZIO.effect(scala.util.Random.nextInt(3) + 1)

  def printLine(line: String): Task[Unit] = ZIO.effect(println(line))

  val readLine: Task[String] = ZIO.effect(scala.io.StdIn.readLine())

  random.flatMap(int => printLine("Guess a number from 1 to 3:").flatMap(_ => readLine.flatMap(num =>
    if (num == int.toString) printLine("You guessed right!")
    else printLine(s"You guessed wrong, the number was ${int}!"))))

  val toRun = {
    for {
      num <- random
      _   <- printLine("Guess a number from 1 to 3:")
      int <- readLine
      _   <- if (num.toString == int.toString) printLine("You guessed right!")
             else printLine(s"You guessed wrong, the number was ${num}!")
    } yield ()
  }

  def run(args: List[String]): URIO[Any with Console, ExitCode] =
    toRun.exitCode

}
