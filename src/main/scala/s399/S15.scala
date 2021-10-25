package s399

import scala.annotation.tailrec

/** The provided solution to [[P15]]. */
object S15 extends P15 :

  /**
   * Returns a list in which all of the elements of the given list are duplicated the given number of times.
   *
   * TODO
   */
  override def duplicateN[A](n: Int, as: List[A]): Result[List[A]] =

    @tailrec
    def aux(rest: List[A], acc: List[A] = Nil): List[A] =
      rest match
        case Nil => acc.reverse
        case h :: t => aux(t, List.fill(n)(h) ++ acc)

    if n < 0 then
      Left(S399Error(s"cannot duplicate $n times (must be non-negative)"))
    else
      Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s15main: Unit = println(duplicateN(3, List(1, 2, 3, 3, 4)))
