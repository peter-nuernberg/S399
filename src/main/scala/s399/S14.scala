package s399

import scala.annotation.tailrec

/** The provided solution to [[P14]]. */
object S14 extends P14 :

  /**
   * Returns a list in which all of the elements of the given list are duplicated.
   *
   * TODO
   */
  override def duplicate[A](as: List[A]): Result[List[A]] =
    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil): List[A] =
      rest match
        case Nil => acc.reverse
        case h :: t => aux(t, h :: h :: acc)

    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s14main: Unit = println(duplicate(List(1, 2, 3, 3, 4)))
