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

/** The specification of the behavior of a correct solution to [[P04.length]]. */
class P04Spec extends BaseSpec :

  // === ASSERTIONS ===

  "A solution to problem 4" - {

    "when given the example input, should return the example output" - {

      def assertion(f: Solution): Unit =
        f(List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 6

      test(assertion)
    }

    "when given a list as input, should return that list's length" - {

      def assertion(f: Solution): Unit =
        forAll((arbIntList, "l")) {
          (l) => f(l).rightValue shouldBe l.length
        }

      test(assertion)
    }
  }

  // === INFRASTRUCTURE ===

  type Solution = List[_] =*=> Int

  given List[(S399Tag, Solution)] = List(
    S399Tag.ExerciseSolution -> X04.length,
    S399Tag.PrimarySolution -> S04.length,
    S399Tag.AlternateSolution -> A104.length,
    S399Tag.AlternateSolution -> A204.length,
    S399Tag.AlternateSolution -> A304.length,
    S399Tag.PracticeSolution -> D104.length,
  )
