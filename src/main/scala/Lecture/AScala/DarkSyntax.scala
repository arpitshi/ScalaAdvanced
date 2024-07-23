package Lecture.AScala
import scala.util.Try

object DarkSyntax extends App {
  def sigargu(arg: Int): String = s"${arg} is my age"

  println(sigargu(7))

  trait action {
    def act(args: Int): Int
  }

  val acting: action = new action {
    override def act(x: Int): Int = x * 3
  }
  println(acting.act(5))
  // Short method of above
  val Easyacting: action = (x: Int) => x * 4
  println(Easyacting.act(5))

  // Try-catch block
  try {
    val result = 10 / 0
    println(result)
  } catch {
    case e: ArithmeticException => println("Arithmetic Exception occurred")
    case e: Exception => println("An error occurred")
  }

  // Thread
  val Thread = new Thread(new Runnable {
    override def run(): Unit = println("I'm a thread")
  })
  println(Thread.run())
  val SweetThread = new Thread(() => println("Sweet Thread"))
  println(SweetThread.run())

  abstract class Anabstracttype {
    def implemented = 23

    def f(a: Int): Unit
  }

  val AbstractInstance: Anabstracttype = (a:Int) => println("Abstract")
  println(AbstractInstance.implemented)
  println(AbstractInstance.f(5))
}
