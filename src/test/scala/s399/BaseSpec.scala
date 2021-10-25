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

import org.scalacheck.{Arbitrary, Gen}
import org.scalactic.source
import org.scalatest.Tag
import org.scalatest.exceptions.TestFailedException
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.*
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import scala.util.Random

/**
 * A base class for all tests in this package.
 *
 * This base class contains:
 *   1. Common generators for random data;
 *   1. a `test` method that tests given solutions against an assertion;
 *   1. extension methods for interrogating `Either` and `Option` instances; and,
 *   1. some custom scalatest tags.
 *
 * The following generators are defined:
 *   1. `arbInt` - generates an arbitrary integer; and,
 *   1. `arbIntList` - generates an arbitrary (possibly 0) length list of arbitrary integers.
 *
 * The `test` method takes an assertion and a list of implicit functions.
 * The assertion type is `A => B => Unit`; the implicit functions should each be of type `A => B`.
 * In the given test sources, there are two relevant declarations:
 * `type Solution`, which is the signature of the function under test; and,
 * `given (Solution, Solution)`, which are the template and provided solutions, respectively.
 * For example, problem 1 (find the last element of a list) has the following declarations:
 * {{{
 *   type Solution[A] = List[A] => Result[A]
 *   given [A]: (Solution[A], Solution[A]) = (P01.last, P01s.last)
 * }}}
 * This allows assertions to be defined and tested as follows:
 * {{{
 *   def assertion(f: Solution[Int]): Unit = f(List(1, 1, 2, 3, 5, 8)).rightValue shouldBe 8
 *   test(assertion)
 * }}}
 * This call to test will result in *two* tests being registered:
 *   1. the first, named "exercise solution" and tagged as [[ExerciseSolution]], tests the first given function;
 *   1. the second, named "provided solution" and tagged as [[ProvidedSolution]], tests the second.
 *
 * The extension methods on `Either` (namely `rightValue` and `leftValue`)
 * and the extension method on `Option` (namely, `value`) are inspired by `EitherValues` and `OptionValues` in
 * scalatest.
 * Unfortunately, the implementations provided by scalatest result in a error (cannot override class `Valuable`).
 */
trait BaseSpec extends AnyFreeSpec
    with Matchers
    with ScalaCheckPropertyChecks :

  /** A type alias to help define solution types for testing. */
  type =*=>[A, B] = A => Result[B]

  /** A generator of arbitrary characters. */
  val arbChar: Gen[Char] = Arbitrary.arbChar.arbitrary

  /** A generator of arbitrary integers. */
  val arbInt: Gen[Int] = Arbitrary.arbInt.arbitrary

  /** A generator of arbitrary integers between 1 and 10 inclusive. */
  val arbSmallPosInt: Gen[Int] = Gen.chooseNum(1, 10)

  /** A generator of arbitrary integers between 2 and 10 inclusive. */
  val arbSmallPosInt2Plus: Gen[Int] = Gen.chooseNum(2, 10)

  /** A generator of arbitrary length (possibly empty) lists of arbitrary integers. */
  val arbIntList: Gen[List[Int]] = Gen.listOf(arbInt)

  /** A generator of a shuffled list of integers (with the seed given to [[scala.util.Random]]). */
  val arbShuffledIntList: Gen[(Long, List[Int])] =
    for
      seed <- Arbitrary.arbLong.arbitrary
      rnd = new Random(seed)
      n <- arbSmallPosInt
    yield (seed, rnd.shuffle((1 to n).toList))

  /**
   * Tests the given assertion against a list of solutions.
   *
   * @param a  the assertion to be tested
   * @param ss the solutions to be tested
   * @tparam A the type of input taken by a solution
   * @tparam B the type of output produced by a solution
   */
  def test[S](a: S => Unit)(using ss: List[(S399Tag, S)]): Unit =
    ss.toList.groupBy(_._1).toList // List[(S399Tag, List[(S399Tag, S)])]
        .sortBy(_._1.ordinal).map(_._2) // List[List[(S399Tag, S)]]
        .flatMap {
          l =>
            l.zipWithIndex.map {
              case ((t, s), i) => (s, (if l.length == 1 then t.label else s"${t.label} ${i + 1}"), t)
            }
        }
        .foreach {
          case (s, l, t) => s"($l solution)" taggedAs (t) in a(s)
        }

  /**
   * Extension methods for an instance of [[Either]].
   *
   * @param e the either instance being extended
   * @tparam A the type on the left side of the given either
   * @tparam B the type on the right side of the given either
   */
  extension[A, B] (e: Either[A, B])(using pos: source.Position)

  /**
   * Returns the left side of the given either, or throws a [[TestFailedException]] if the given either is
   * right-sided.
   */
    def leftValue: A =
      e.fold(
        identity,
        _ => throw new TestFailedException(_ => Some("right-valued either does not have a left value"), None, pos))

    /**
     * Returns the right side of the given either, or throws a [[TestFailedException]] if the given either is
     * left-sided.
     */
    def rightValue: B =
      e.fold(
        _ => throw new TestFailedException(_ => Some("right-valued either does not have a left value"), None, pos),
        identity)

  /**
   * Extension methods for an instance of [[Option]].
   *
   * @param o the option instance being extended
   * @tparam A the type inside of the given option
   */
  extension[A] (o: Option[A])(using pos: source.Position)

  /**
   * Returns the inside of the given option, or throws a [[TestFailedException]] if the given option is empty.
   */
    def value: A =
      o.fold(throw new TestFailedException(_ => Some("empty option does not have a value"), None, pos))(identity)

/** An enumeration of tags specific to the S399 package. */
enum S399Tag(val label: String, n: String) extends Tag(n) :

  case ExerciseSolution extends S399Tag("exercise", "s399.ExerciseSolution")
  case PrimarySolution extends S399Tag("primary", "s399.PrimarySolution")
  case AlternateSolution extends S399Tag("alternate", "s399.AlternateSolution")
  case PracticeSolution extends S399Tag("practice", "s399.PracticeSolution")
