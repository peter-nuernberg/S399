package s399

import scala.annotation.targetName

extension (i: Int)

  @targetName("floormod")
  def %%(j: Int): Result[Int] =
    if j == 0 then
      Left(S399Error("cannot calculate modulo 0"))
    else
      Right(scala.math.floorMod(i, j))
