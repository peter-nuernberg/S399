/*
 * Scala 99 problems
 *
 * Copyright 2021 Peter J. Nuernberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package s399

import org.scalacheck.Gen

/** The specification of the behavior of a correct solution to [[P15.duplicateN]]. */
class P15Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 15" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(3, List(1, 2, 3, 3, 4)).rightValue shouldBe List(1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4)

      test(assertion)
    }

    "when given any non-negative n and an empty list, should return an empty list" - {

      def assertion(f: Solution[Any]): Unit =
        forAll((arbSmallPosInt, "n")) {
          (n) => f(n, Nil).rightValue shouldBe Nil
        }

      test(assertion)
    }

    "when given a zero for number of duplicates and any, should return an empty list" - {

      def assertion(f: Solution[Any]): Unit =
        forAll((arbShuffledIntList, "(seed,l)")) {
          (_, l) => f(0, l).rightValue shouldBe Nil
        }

      test(assertion)
    }

    "when given a non-empty list, should return the expected duplicated list" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbShuffledIntList, "(seed, l)"), (arbSmallPosInt2Plus, "n")) {
          case ((_, l), n) => f(n, l).rightValue shouldBe l.flatMap{ a => List.fill(n)(a) }
        }

      test(assertion)
    }

    "when given a negative for number of duplicates and any list, should return an error" - {

      def assertion(f: Solution[Any]): Unit =
        forAll((arbShuffledIntList, "(seed,l)"), (arbSmallPosInt, "n")) {
          case ((_, l), n) => f(-n, l).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }

    // TODO

    //    "when given an empty list as input, should return list containing the empty list" - {
    //
    //      def assertion(f: Solution[Any]): Unit =
    //        f(Nil).rightValue shouldBe List(Nil)
    //
    //      test(assertion)
    //    }
    //
    //    "when given a list with a single element as input, should return list containing that list" - {
    //
    //      def assertion(f: Solution[Int]): Unit =
    //        forAll((arbInt, "a")) {
    //          (a) => f(List(a)).rightValue shouldBe List(List(a))
    //        }
    //
    //      test(assertion)
    //    }
    //
    //    "when given a list with a single repeated element as input, should return list containing that list" - {
    //
    //      def assertion(f: Solution[Int]): Unit =
    //        forAll((Gen.chooseNum(2, 10), "a")) {
    //          (a) =>
    //            val in = List.fill(a)(a)
    //            f(in).rightValue shouldBe List(in)
    //        }
    //
    //      test(assertion)
    //    }
    //
    //    "when given a list with a several repeated element as input, should return the expected list" - {
    //
    //      def assertion(f: Solution[Int]): Unit =
    //        forAll((Gen.chooseNum(1, 10), "n")) {
    //          (n) =>
    //            val base = scala.util.Random.shuffle((1 to n).toList)
    //            val out = base.map(i => List.fill(i)(i))
    //            val in = out.flatten
    //            f(in).rightValue shouldBe out
    //        }
    //
    //      test(assertion)
    //    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = (Int, List[A]) => Result[List[A]]

  given [A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X15.duplicateN,
    S399Tag.PrimarySolution -> S15.duplicateN,
  )

