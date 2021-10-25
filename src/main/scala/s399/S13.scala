package s399

/** The provided solution to [[P13]]. */
object S13 extends P13 :

  /**
   * Returns a run-length encoding of the given list.
   *
   * TODO
   */
  override def encodeDirect[A](as: List[A]): Result[List[(Int, A)]] =
    def aux(rest: List[A], acc: List[(Int, A)] = Nil): List[(Int, A)] =
      rest match
        case Nil => acc.reverse
        case hRest :: tRest =>
          val (accN, accA, tAcc) = acc match
            case Nil => (0, hRest, Nil)
            case (hAccN, hAccA) :: tAcc => (hAccN, hAccA, tAcc)
          if hRest == accA then
            aux(tRest, (accN + 1, accA) :: tAcc)
          else
            aux(tRest, (1, hRest) :: acc)

    Right(aux(as))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s13main: Unit = println(encodeDirect(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
