import scala.math.{ exp, pow }

final case class Weibull(shape: Double, scale: Double):

  val k: Double = shape
  val λ: Double = scale

  def pdf(x: Double): Double =
    if x < 0 then 0 else (k / λ) * ((x / λ) ** (k - 1)) * exp(-((x / λ) ** k))

  def cdf(x: Double): Double =
    if x < 0 then 0 else 1 - exp(-((x / λ) ** k))

// === driver

object Driver:

  @main def main: Unit =
    val w = Weibull(2, 1)
    Iterator.from(0)
        .map(_.toDouble / 100)
        .takeWhile(_ <= 2.0)
        .map(d => (d, w.pdf(d), w.cdf(d)))
        .foreach { case (x, p, c) => println(s"$x,$p,$c") }

// === utilities

extension (d: Double)

  def **(e: Double): Double = pow(d, e)
