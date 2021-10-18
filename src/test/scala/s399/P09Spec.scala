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

/** The specification of the behavior of a correct solution to [[P09]]. */
class P09Spec extends BaseSpec {

  type Solution[A] = List[A] => Result[List[List[A]]]

  given[A]: List[Solution[A]] = List(P09x.pack, P09s.pack)

  "A solution to problem 9" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)).rightValue shouldBe List(
          List(1, 1, 1, 1), List(2), List(3, 3), List(1, 1), List(4), List(5, 5, 5, 5))

      test(assertion)
    }

    "when given an empty list as input, should return list containing the empty list" - {

      def assertion(f: Solution[Any]): Unit =
        f(Nil).rightValue shouldBe List(Nil)

      test(assertion)
    }

    "when given a list with a single element as input, should return list containing that list" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbInt, "a")) {
          (a) => f(List(a)).rightValue shouldBe List(List(a))
        }

      test(assertion)
    }

    "when given a list with a single repeated element as input, should return list containing that list" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((Gen.chooseNum(2, 10), "a")) {
          (a) =>
              val in = List.fill(a)(a)
              f(in).rightValue shouldBe List(in)
        }

      test(assertion)
    }

    "when given a list with a several repeated element as input, should return the expected list" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((Gen.chooseNum(1, 10), "n")) {
          (n) =>
            val base = scala.util.Random.shuffle((1 to n).toList)
            val out = base.map(i => List.fill(i)(i))
            val in = out.flatten
            f(in).rightValue shouldBe out
        }

      test(assertion)
    }
  }
}
