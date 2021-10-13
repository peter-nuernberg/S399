val scala3Version = "3.0.2"

// === DEPENDENCIES ===

// --- cats ---

val catsVersion = "2.6.1"
val catsDeps = Seq("cats-core")
    .map(m => "org.typelevel" %% m % catsVersion)

// --- scalactic ---

val scalacticVersion = "3.2.9"
val scalacticDeps = Seq("scalactic")
    .map(m => "org.scalactic" %% m % scalacticVersion)

// --- scalacheck ---

val scalacheckVersion = "1.15.4"
val scalacheckDeps = Seq("scalacheck")
    .map(m => "org.scalacheck" %% m % scalacheckVersion % "test")

// --- scalatest ---

val scalatestVersion = "3.2.9"
val scalatestDeps = Seq("scalatest")
    .map(m => "org.scalatest" %% m % scalatestVersion % "test")

val scalatestPlusVersion = scalatestVersion + ".0"
val scalacheckBridgeSuffix = scalacheckVersion
    .split(raw"\.").iterator.take(2).foldLeft("") { case (acc, elem) => acc + s"-$elem" }
val scalatestPlusDeps = Seq(s"scalacheck$scalacheckBridgeSuffix")
    .map(m => "org.scalatestplus" %% m % scalatestPlusVersion % "test")

// === MODULES ===

// --- root ---

lazy val root = project
  .in(file("."))
  .settings(
    name := "S399",
    version := "0.1.0",
    scalaVersion := scala3Version,

    libraryDependencies ++=
        catsDeps
            ++ scalacticDeps
            ++ scalacheckDeps
            ++ scalatestDeps
            ++ scalatestPlusDeps
)
