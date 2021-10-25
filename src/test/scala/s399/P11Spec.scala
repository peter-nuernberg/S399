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

/** The specification of the behavior of a correct solution to [[P11.encodeModified]]. */
class P11Spec extends BaseSpec :

  // === ASSERTIONS (WORKING DEPENDENCIES) ===

  "Given a solution to problem 10 that works correctly," - {

    "a solution to problem 11" - {

      "when given the example input, should return the example output" - {

        def assertion(f: Solution): Unit =
          f(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)).rightValue shouldBe List((4, 1), 2, (2, 3), (2, 1), 4, (4, 5))

        test(assertion)
      }

      "when given the input that consists only of singletons, should return the given list" - {

        def assertion(f: Solution): Unit =
          forAll((arbShuffledIntList, "(seed, l)")) {
            (_, base) =>
              f(base).rightValue shouldBe base
          }

        test(assertion)
      }

      "when given the input that consists only of n copies of each element, should return a list of the expected pairs" - {

        def assertion(f: Solution): Unit =
          forAll((arbShuffledIntList, "(seed, l)"), (arbSmallPosInt2Plus, "n")) {
            case ((_, base), n) =>
              val in = base.flatMap(i => List.fill(n)(i))
              val out = base.map(i => (n, i))
              println(s"base=$base; n=$n; in=$in; out=$out; act=${f(in).rightValue}")
              f(in).rightValue shouldBe out
          }

        test(assertion)
      }
    }

    // === INFRASTRUCTURE (WORKING DEPENDENCIES) ===

    type Solution = List[_] =*=> List[_]

    given P09 = S09

    given P10 = S10

    given List[(S399Tag, Solution)] = List(
      S399Tag.ExerciseSolution -> X11.encodeModified,
      S399Tag.PrimarySolution -> S11.encodeModified,
      S399Tag.AlternateSolution -> A111.encodeModified,
      S399Tag.AlternateSolution -> A211.encodeModified,
    )
  }

  // === ASSERTIONS (BROKEN DEPENDENCIES) ===

  "Given a solution to problem 10 that always fails," - {

    "a solution to problem 11" - {

      "when given any input, should return an error" - {

        def assertion(f: Solution): Unit =
          f(Nil).leftValue shouldBe an[S399Error]

        test(assertion)
      }
    }

    // === INFRASTRUCTURE (BROKEN DEPENDENCIES) ===

    type Solution = List[_] =*=> List[_]

    given P09 = S09

    given P10 = new P10 :
      override def encode[A](as: List[A])(using p09: P09): Result[List[(Int, A)]] = Left(S399Error("an error"))

    given List[(S399Tag, Solution)] = List(
      S399Tag.ExerciseSolution -> X11.encodeModified,
      S399Tag.PrimarySolution -> S11.encodeModified,
      S399Tag.AlternateSolution -> A111.encodeModified,
      S399Tag.AlternateSolution -> A211.encodeModified,
    )
  }

