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
import s399.solutions.*

/** The specification of the behavior of a correct solution to [[P08]]. */
class P08Spec extends BaseSpec {

  type Solution[A] = List[A] => Result[List[A]]

  given[A]: List[Solution[A]] = List(P08x.compress, P08s.compress,
    P08s.compressAlt1)

  "A solution to problem 8" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)).rightValue shouldBe List(1, 2, 3, 1, 4, 5)

      test(assertion)
    }

    "when given an empty list as input, should return an empty list" - {

      def assertion(f: Solution[Any]): Unit =
        f(Nil).rightValue shouldBe Nil

      test(assertion)
    }

    "when given a non-empty list with duplicates as input, should return that list with no duplicates" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((Gen.chooseNum(1, 10), "n")) {
          (n) =>
              val out = Iterator.from(1).take(n).toList
              val in = out.flatMap(i => List(i))
              f(in).rightValue shouldBe out
        }

      test(assertion)
    }
  }
}
