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

/**
 * A base class for errors.
 *
 * @param message a message describing the error encountered
 * @param cause   the underlying cause of this error, if any
 */
final case class S399Error(message: String, cause: Option[Throwable]) extends Exception(message, cause.orNull)

/**
 * An error encountered by a solution to one of the 99 problems.
 */
object S399Error {

  /**
   * Creates a new error with the given message.
   *
   * @param message a message describing the error encountered
   * @return a new error with the given message and no underlying cause
   */
  def apply(message: String): S399Error = new S399Error(message, Option.empty[Throwable])

  /**
   * Creates a new error with the given message and underlying cause.
   *
   * @param message a message describing the error encountered
   * @param cause   the underlying cause of this error
   * @return a new error with the given message and underlying cause
   */
  def apply(message: String, cause: Throwable): S399Error = new S399Error(message, Some(cause))
}
