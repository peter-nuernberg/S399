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

/** The specification of the behavior of a correct solution to [[P01.last]]. */
class P01Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 1" - {

    "when given the example input, should return the example output" - {

      def assertion(s: Solution[String]): Unit =
        s(List("a", "b", "c", "d", "e", "f")).rightValue shouldBe "f"

      test(assertion)
    }

    "when given a non-empty list as input, should return that list's last element" - {

      def assertion(s: Solution[String]): Unit =
        forAll((arbStringList, "base"), (arbString, "lastElem")) {
          (base, lastElem) => s(base :+ lastElem).rightValue shouldBe lastElem
        }

      test(assertion)
    }

    "when given an empty list as input, should return an error" - {

      def assertion(s: Solution[Any]): Unit =
        s(Nil).leftValue shouldBe an[S399Error]

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = List[A] =*=> A

  given[A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X01.last,
    S399Tag.PrimarySolution -> S01.last,
  )
