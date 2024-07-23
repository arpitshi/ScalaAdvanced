package Lecture.AScala

object AdvancedPatternMatching extends App{

  val list = List(1)
  val listmatch = list match{
    case head :: nil => println(s"the only element is $head")
    case _ =>
  }
  println(listmatch)

  class Person(val name: String ,val age: Int)

  object Person{
    def unapply(person:Person): Option[(String,Int)] =
    if(person.age<20) None
    else Some((person.name, person.age))

    def unapply(age :Int): Option[String] =
      Some(if(age>21) "Major" else "Minor")

  }
  val bob = new Person("Bob", 25)
  val greeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a yo."
  }
  println(greeting)
  val legalStatus = bob.age match {
    case Person(status) => s"My legal status is $status"
  }
  println(legalStatus)

  //Exercise

  object even{
    def unapply(n:Int): Boolean = n % 2 == 0
  }
  object singledigit{
    def unapply(n:Int):Boolean = n > -10 && n < 10
  }
  val number  = 9
  val evenOrSingledigit = number match {
    case even()|singledigit() => "Even ya single digit"
    case singledigit() => " sirf Single digit"
    case _ => "Not even or single digit"
  }
  println(evenOrSingledigit)

  /* infix matching */
   case class Or[A,B](a:A, b:B)
  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
    // case Or(number,string) => s"$number is written as $string"
  }
  println(humanDescription)

  // decomposing sequences
//  val vararg = number match {
//    case List(1, _*) => "starting with 1"
//  }

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1, 2"
    case _ => "something else"
  }
  println(decomposed)

  // custom return types for unapply
  // isEmpty: Boolean, get: something.

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }
  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false
      def get = person.name
    }
  }
  println(bob match {
    case PersonWrapper(n) => s"This person's name is $n"
    case _ => "An alien"
  })
}
