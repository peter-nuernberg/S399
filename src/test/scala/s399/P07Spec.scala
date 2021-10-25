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

/** The specification of the behavior of a correct solution to [[P07.flatten]]. */
class P07Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 7" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution): Unit =
        f(List(List(1, 1), 2, List(3, List(5, 8)))).rightValue shouldBe List(1, 1, 2, 3, 5, 8)

      test(assertion)
    }

    "when given an empty list as input, should return an empty list" - {

      def assertion(f: Solution): Unit =
        f(Nil).rightValue shouldBe Nil

      test(assertion)
    }

    "when given a flat list as input, should return that list" - {

      def assertion(f: Solution): Unit =
        forAll((arbIntList, "l")) {
          (l) => f(l).rightValue shouldBe l
        }

      test(assertion)
    }

    "when given a nested list as input, should return the flattened list" - {

      def assertion(f: Solution): Unit =
        forAll((arbIntList, "l"), (Gen.chooseNum(1, 10), "n")) {
          (l, n) =>
              val nl = Iterator.iterate[List[_]](l, n)(List.apply).toList.reverse.head
              f(nl).rightValue shouldBe l
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution = List[_] =*=> Any

  given List[(S399Tag, Solution)] = List(
    S399Tag.ExerciseSolution -> X07.flatten,
    S399Tag.PrimarySolution -> S07.flatten,
    S399Tag.AlternateSolution -> A107.flatten,
  )

