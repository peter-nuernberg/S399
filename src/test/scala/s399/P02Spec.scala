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

import s399.solutions.*

/** The specification of the behavior of a correct solution to [[P02]]. */
class P02Spec extends BaseSpec {

  type Solution[A] = List[A] => Result[A]

  given[A]: List[Solution[A]] = List(P02x.penultimate, P02s.penultimate,
    P02s.penultimateAlt1, P02s.penultimateAlt2, P02s.penultimateAlt3)

  "A solution to problem 2" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 5

      test(assertion)
    }

    "when given a list with 2 or more elements as input, should return that list's penultimate element" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbIntList, "base"), (arbInt, "penElem"), (arbInt, "lastElem")) {
          (base, penElem, lastElem) => f(base :+ penElem :+ lastElem).rightValue shouldBe penElem
        }

      test(assertion)
    }

    "when given an empty list as input, should return an error" - {

      def assertion(f: Solution[Any]): Unit =
        f(Nil).leftValue shouldBe an[S399Error]

      test(assertion)
    }

    "when given a 1-element list as input, should return an error" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbInt, "elem")) {
          (elem) => f(List(elem)).leftValue shouldBe an[S399Error]
        }

      test(assertion)
    }
  }
}
