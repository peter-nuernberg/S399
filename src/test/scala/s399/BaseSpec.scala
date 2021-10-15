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

/**
 * A base class for all tests in this package.
 *
 * This base class contains:
 *   1. Common generators for random data;
 *   1. a `test` method that tests both the exercise and provided solutions against an assertion; and,
 *   1. extension methods for interrogating `Either` and `Option` instances.
 *
 * The following generators are defined:
 *   1. `arbInt` - generates an arbitrary integer; and,
 *   1. `arbIntList` - generates an arbitrary (possibly 0) length list of arbitrary integers.
 *
 * The `test` method takes an assertion and a pair of implicit functions.
 * The assertion type is `A => B => Unit`, the two implicit functions should each be of type `A => B`.
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

  /** A generator of arbitrary characters. */
  val arbChar: Gen[Char] = Arbitrary.arbChar.arbitrary

  /** A generator of arbitrary integers. */
  val arbInt: Gen[Int] = Arbitrary.arbInt.arbitrary

  /** A generator of arbitrary length (possibly empty) lists of arbitrary integers. */
  val arbIntList: Gen[List[Int]] = Gen.listOf(arbInt)

//  /**
//   * Tests the given assertion against a pair of solutions (assumed to be the exercise and provided solutions).
//   *
//   * @param a   the assertion to be tested
//   * @param fns the solutions to be tested
//   * @tparam A the type of input taken by a solution
//   * @tparam B the type of output produced by a solution
//   */
//  def test[S](a: S => Unit)(using fns: (S, S)): Unit =
//    val (x, p) = fns
//    "(exercise solution)" taggedAs (ExerciseSolution) in a(x)
//    "(provided solution)" taggedAs (ProvidedSolution) in a(p)

  /**
   * Tests the given assertion against a pair of solutions (assumed to be the exercise and provided solutions).
   *
   * @param a   the assertion to be tested
   * @param fns the solutions to be tested
   * @tparam A the type of input taken by a solution
   * @tparam B the type of output produced by a solution
   */
  def test[S](a: S => Unit)(using ss: List[S]): Unit =
    SolutionType.iterator(ss).foreach {
      si =>
          si.st.tags match
            case Nil => si.st.name in a(si.s)
            case t :: Nil => si.st.name taggedAs (t) in a(si.s)
            case t1 :: t2 :: Nil => si.st.name taggedAs (t1, t2) in a(si.s)
            case _ => throw new Exception()
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

/** A scalatest tag for tests of exercise solutions. */
object ExerciseSolution extends Tag("s399.ExerciseSolution")

/** A scalatest tag for tests of provided solutions. */
object ProvidedSolution extends Tag("s399.ProvidedSolution")

/** A scalatest tag for tests of primary provided solutions. */
object PrimarySolution extends Tag("s399.PrimarySolution")

/** A scalatest tag for tests of alternate provided solutions. */
object AlternateSolution extends Tag("s399.AlternateSolution")

final case class SolutionType(name: String, tags: List[Tag])

final case class SolutionInfo[S](st: SolutionType, s: S)

object SolutionType:

  def stIterator: Iterator[SolutionType] =
    Iterator.single(SolutionType("(exercise solution)", List(ExerciseSolution))) ++
        Iterator.single(SolutionType("(provided solution)", List(ProvidedSolution, PrimarySolution))) ++
        Iterator.from(1).map(i => SolutionType(s"(alternate solution $i)", List(ProvidedSolution, AlternateSolution)))

  def iterator[S](ss: List[S]): Iterator[SolutionInfo[S]] =
    stIterator.zip(ss).map(SolutionInfo.apply)