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

/** The specification of the behavior of a correct solution to [[P10.encode]]. */
class P10Spec extends BaseSpec :

  // === ASSERTIONS (WORKING DEPENDENCIES) ===

  "Given a solution to problem 9 that works correctly," - {

    "a solution to problem 10" - {

      "when given the example input, should return the example output" - {

        def assertion(f: Solution[Int]): Unit =
          f(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)).rightValue shouldBe List(
            (4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5))

        test(assertion)
      }

      "when given an empty list, should return an empty list" - {

        def assertion(f: Solution[Any]): Unit =
          f(Nil).rightValue shouldBe Nil

        test(assertion)
      }

      "when given a list with a single repeated element, should return the expected result" - {

        def assertion(f: Solution[Int]): Unit =
          forAll((arbInt, "i"), (arbSmallPosInt, "n")) {
            (i, n) => f(List.fill(n)(i)).rightValue shouldBe List((n, i))
          }

        test(assertion)
      }

      "when given a list with multiple repeated elements, should return the expected result" - {

        def assertion(f: Solution[Int]): Unit =
          forAll((arbShuffledIntList, "(seed, l)")) {
            (_, base) =>
              val in = base.flatMap(i => List.fill(i)(i))
              val out = base.map(i => (i, i))
              f(in).rightValue shouldBe out
          }

        test(assertion)
      }
    }

    // === INFRASTRUCTURE (WORKING DEPENDENCIES) ===

    type Solution[A] = List[A] =*=> List[(Int, A)]

    given P09 = S09

    given[A]: List[(S399Tag, Solution[A])] = List(
      S399Tag.ExerciseSolution -> X10.encode,
      S399Tag.PrimarySolution -> S10.encode,
    )
  }

  // === ASSERTIONS (BROKEN DEPENDENCIES) ===

  "Given a solution to problem 9 that always fails," - {

    "a solution to problem 10" - {

      "when given any input, shlould return an error" - {

        def assertion(f: Solution[Any]): Unit =
          f(Nil).leftValue shouldBe an[S399Error]

        test(assertion)
      }
    }

    // === INFRASTRUCTURE (BROKEN DEPENDENCIES) ===

    type Solution[A] = List[A] =*=> List[(Int, A)]

    given P09 = new P09 :
      override def pack[A](as: List[A]): Result[List[List[A]]] = Left(S399Error("an error"))

    given[A]: List[(S399Tag, Solution[A])] = List(
      S399Tag.ExerciseSolution -> X10.encode,
      S399Tag.PrimarySolution -> S10.encode,
    )
  }
