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

/** The specification of the behavior of a correct solution to [[P02.penultimate]]. */
class P02Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 2" - {

    "when given the example input, should return the example output" - {

      def assertion(s: Solution[Int]): Unit =
        s(List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 5

      test(assertion)
    }

    "when given a list with 2 or more elements as input, should return that list's penultimate element" - {

      def assertion(s: Solution[Int]): Unit =
        forAll((arbIntList, "base"), (arbInt, "penElem"), (arbInt, "lastElem")) {
          (base, penElem, lastElem) => s(base :+ penElem :+ lastElem).rightValue shouldBe penElem
        }

      test(assertion)
    }

    "when given an empty list as input, should return an error" - {

      def assertion(s: Solution[Any]): Unit =
        s(Nil).leftValue shouldBe an[S399Error]

      test(assertion)
    }

    "when given a 1-element list as input, should return an error" - {

      def assertion(s: Solution[Int]): Unit =
        forAll((arbInt, "elem")) {
          (elem) => s(List(elem)).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution[A] = List[A] =*=> A

  given[A]: List[(S399Tag, Solution[A])] = List(
    S399Tag.ExerciseSolution -> X02.penultimate,
    S399Tag.PrimarySolution -> S02.penultimate,
    S399Tag.AlternateSolution -> A102.penultimate,
    S399Tag.AlternateSolution -> A102.penultimate,
    S399Tag.AlternateSolution -> A102.penultimate,
  )
