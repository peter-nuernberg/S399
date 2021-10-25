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

import scala.annotation.tailrec

/** The provided solution to [[P07]]. */
object S07 extends P07 :

  /**
   * Flattens the given structure into a single, top-level list.
   *
   * This is, for me, a disappointing problem, because the type bounds are so loose.
   * We take a list of anything and hand back a list of anything.
   * More discussion on this after a discussion of this solution.
   *
   * We again use an inner tail-recursive auxilliary method to accumulate our answer as we traverse the input.
   * The auxilliary has three cases:
   *
   *   1. EXIT: If the rest of the input is an empty list, processing is done, the method returns the *reverse* of the
   *      accumulator (see below).
   *   1. If the rest of theinput is a non-empty list:
   *      a. RECURSE: If the head element of the input is a list, the method calls `aux` again on the head prepended to
   *         the tail; no change to the accumulator.  (This just flattens a sublist.)
   *      a. RECURSE: If the head element is not a list, the method prepends it to the accumulator and call `aux` on the
   *         tail.
   *
   * The method processes the input list left to right, so one might think the accumulator should be built by
   * *appending* new items to it.
   * Instead, the method *prepends* new items, and the reverses the entire accumulator at the end.
   * This is because [[scala.collection.immutable.List]] is a sinlgy linked list.
   * Prepends are constant time; appends are linear; and, reversal is linear.
   * If the method would append each time, the number of operations would be proportional to the *square* of the length
   * of the input.
   * For small lists, this might not make much difference, so I've inclued a append/no-reverse alternate solution in
   * [[P07s.flattenAlt1]].
   * However, this prepend/reverse idiom is common enough that it probably pays to become familiar with it.
   *
   * What about the loose type bounds?
   * Well, it's hard to declare a type bound on list that allows it to contain only either instances of `A` or sublists
   * that have the same type as the top-level list.
   * This is partly because the input is more like a tree than a list, and the operation is essentially a tree
   * traversal.
   * If we did model the input as a tree, we might end up with types like [[P07s.Tree]], [[P07s.Leaf]], [[P07s.Node]],
   * and a "flatten" alternative like [[P07s.flattenAlt2]].
   * (Note: this is not a real "alternate" solution, since it rewrites the method signature, so it is not run against
   * the test suite, but you can run it through the "main" entry point [[P07s.p07sAlt2main]].)
   */
  override def flatten(l: List[_]): Result[List[_]] =

    @tailrec
    def aux(rest: List[_], acc: List[_] = Nil): List[_] =
      rest match
        case Nil => acc.reverse
        case h :: t =>
          h match
            case l: List[_] => aux(l ++ t, acc)
            case a => aux(t, a :: acc)

    Right(aux(l))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s07main: Unit = println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))

// === ALTERNATE SOLUTION 1 ===

/** The first alternate solution to [[P07]]. */
object A107 extends P07 :

  /**
   * Flattens the given structure into a single, top-level list.
   * 
   * An alternate solution that runs slower but builds the accumulator in a more natural front-to-back way.
   */
  override def flatten(l: List[_]): Result[List[_]] =

    @tailrec
    def aux(rest: List[_], acc: List[_] = Nil): List[_] =
      rest match
        case Nil => acc
        case h :: t =>
          h match
            case l: List[_] => aux(l ++ t, acc)
            case a => aux(t, acc :+ a)

    Right(aux(l))

  /** A main method that executes the first alternate solution above on the sample input. */
  @main def a107main: Unit = println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))

// === PRACTICE SOLUTION 1 ===

/** The first practice solution to [[P07]]. */
object D107 :

  /** A tree. */
  sealed trait Tree[+A]

  /** A leaf of a tree. */
  final case class Leaf[+A](value: A) extends Tree[A]

  /** A inner node of a tree. */
  final case class Node[+A](children: List[Tree[A]]) extends Tree[A]

  /** Some factories to make trees. */
  object Tree:

    /** Creates a leaf. */
    def apply[A](a: A): Tree[A] = Leaf(a)

    /** Creates a node. */
    def apply[A](ts: Tree[A]*) = Node(ts.toList)

  /**
   * Flattens the given structure into a single, top-level list.
   *
   * An alternate solution that rewrites the signatures significantly, but is type safer.
   * This won't run inside the gievn test suite -- it's mostly just to look at.
   * There is a `main` method included to verify the example input.
   */
  def flatten[A](t: Tree[A]): Result[List[A]] =

    def aux(rest: Tree[A]): List[A] =
      rest match
        case Leaf(a) => List(a)
        case Node(ts) => ts.flatMap(aux)

    Right(aux(t))

  /** A main method that executes the first practice solution above on the sample input. */
  @main def d107main: Unit = println(flatten(Tree(Tree(Tree(1), Tree(1)), Tree(2), Tree(Tree(3), Tree(Tree(5), Tree(8))))))
