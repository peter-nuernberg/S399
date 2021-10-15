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

/** The specification of the behavior of a correct solution to [[P05]]. */
class P05Spec extends BaseSpec {

  type Solution[A] = List[A] => Result[List[A]]

  given[A]: List[Solution[A]] = List(P05x.reverse, P05s.reverse,
    P05s.reverseAlt1)

  "A solution to problem 5" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution[Int]): Unit =
        f(List(1, 1, 2, 3, 5, 8)).rightValue shouldBe List(8, 5, 3, 2, 1, 1)

      test(assertion)
    }

    "when given a list as input, should return the reverse of that list" - {

      def assertion(f: Solution[Int]): Unit =
        forAll((arbIntList, "l")) {
          (l) => f(l).rightValue shouldBe l.reverse
        }

      test(assertion)
    }
  }
}
