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

/** The provided solution to [[P10]]. */
object S10 extends P10 :

  /** The solution to [[P09]] used by [[S10.encode]] by default. */
  given P09 = S09

  /**
   * Returns a run-length encoding of the given list.
   *
   * The method itself is fairly straightforward if it builds upon [[P09]].
   * But first, a note about `given` and `using`.
   * In Scala 2, the word `implicit` was used all over the place to mean many things.
   * In Scala 3, some of these uses have been replaced by `given` and `using`.
   * Note that `encode` actually takes two parameter lists.
   * The first is the list the method will process.
   * The second is the implementation of `P09` the method will use (to call `pack`).
   * But, if you see the [[S10.s10main]] call to `encode`, it does not provide anything for the second parameter list.
   * If you play around a bit, you'll not you *can* provide an implementation, so `encode(List(1, 2, 3))(S09)`, e.g.,
   * certainly works.
   * If the call site does not provide a value for `using` parameters, the compiler will look for `given` values that
   * would fit and are in scope.
   * This is a pretty extensive subject, but the idea is that, for many cases, the value for `using` parameters does
   * not change much.
   * For example, [[scala.collection.immutable.List.sorted]] takes an "implicit" ordering parameter (which, in Scala 3,
   * is declared by `using`).
   * For example, if we write `val l = List(1, 3, 2).sorted`, the compiler fills in the value for the ordering of `Int`s
   * for us, which (luckily) does what we (probably) want it to.
   * So, somewhere, there is a `Ordering[Int]` that specifies how to order integers (by default).
   * In this object, we define a "default" instance of `P09` (namely, [[S09]]), by writing `given P09 = S09`.
   *
   * Recall that [[P09.pack]] takes a `List[A]` and returns a `List[List[A]]`,
   * where runs of identical elements are placed within sublists.
   * This method maps essentially maps each of those sublists into a 2-tuple,
   * in wihch the first element is the length of the sublist and the second is an instance of (all identical) elements.
   * There are two slight complications.
   *
   *   1. `pack` actually returns a [[Result]].
   *      No worries!  This method maps against that result.
   *      If `pack` encountered an error, we'll return it here, since the `map` will just pass the error through.
   *
   *   1. What happens if `pack`` (incorrectly) provides us a 0-length sublist?
   *      This method calls [[scala.collection.immutable.List.headOption]] on each sublist `pack` returns.
   *      That [[scala.Option]] will be mapped to the sort of tuple we need (i.e., a (length, element) pair).
   *      If the method just mapped every sublist like this, it would have a list of options.
   *      However, this method calls `flatMap`, which generates a list of tuples -- `None` instances are "flattened
   *      away".
   *      We could be more explicit here to remove some of the magic that is happening by calling `toList` on all of the
   *      `Option` instances -- it might be clearer to think about this by seeing that `None.toList` yields `Nil` which
   *      "disappears" in the flattening part of `flatMap`.
   *      Either way, `flatMap`-ing across a list of options seems like something I've run into enough that I don't
   *      worry about the explicit `toList`.
   *      If you want to explore more, note that `flatMap` on `List` actually takes a function that generates an
   *      [[scala.collection.IterableOnce]], which both `List` and `Option` implement.
   */
  override def encode[A](as: List[A])(using p09: P09): Result[List[(Int, A)]] =
    p09.pack(as).map(_.flatMap(l => l.headOption.map(h => (l.length, h))))

  /** A main method that executes the provided solution above on the sample input. */
  @main def s10main: Unit = println(encode(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)))
