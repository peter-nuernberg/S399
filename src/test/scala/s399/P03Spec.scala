package s399

import org.scalacheck.Gen
import s399.solutions.*

/** The specification of the behavior of a correct solution to [[P03]]. */
class P03Spec extends BaseSpec {

  type Solution[A] = (Int, List[A]) => Result[A]

  given[A]: List[Solution[A]] = List(P03x.nth, P03s.nth,
    P03s.nthAlt1)

  "A solution to problem 3" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(2, List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 2

      test(assertion)
    }

    "when given a non-negative index and a sufficiently long list, should return the expected output" - {

      val legalPositiveIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(1, 100)
          n <- Gen.chooseNum(0, nn - 1)
          l <- Gen.listOfN(nn, arbInt)
        yield (n, l)

      def assertion(f: Solution[Int]): Unit =
        forAll((legalPositiveIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).rightValue shouldBe l(n)
        }

      test(assertion)
    }

    "when given a negative index and a sufficiently long list, should return the expected output" - {

      val legalNegativeIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(1, 100)
          n <- Gen.chooseNum(-nn, -1)
          l <- Gen.listOfN(nn, arbInt)
        yield (n, l)

      def assertion(f: Solution[Int]): Unit =
        forAll((legalNegativeIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).rightValue shouldBe l(l.length + n)
        }

      test(assertion)
    }

    "when given a non-negative index and an insufficiently long list, should return an error" - {

      val illegalPositiveIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(0, 100)
          o <- Gen.chooseNum(0, 100)
          l <- Gen.listOfN(nn, arbInt)
        yield (o + nn, l)

      def assertion(f: Solution[Int]): Unit =
        forAll((illegalPositiveIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }

    "when given a negative index and an insufficiently long list, should return an error" - {

      val illegalNegativeIndexAndList: Gen[(Int, List[Int])] =
        for
          nn <- Gen.chooseNum(0, 100)
          o <- Gen.chooseNum(1, 100)
          l <- Gen.listOfN(nn, arbInt)
        yield (-(o + nn), l)

      def assertion(f: Solution[Int]): Unit =
        forAll((illegalNegativeIndexAndList, "(n, l)")) {
          (n, l) => f(n, l).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }
  }
}
