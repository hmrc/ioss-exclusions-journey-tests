import sbt.*

object Dependencies {

  // format: off
  val test: Seq[ModuleID] = Seq(
    "com.vladsch.flexmark" % "flexmark-all"      % "0.64.8"   % Test,
    "org.scalatest"       %% "scalatest"         % "3.2.19"   % Test,
    "uk.gov.hmrc"         %% "ui-test-runner"    % "0.52.0"   % Test,
    "junit"                % "junit"             % "4.13.2"   % Test
  )
  // format: on

}
