addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.1")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "0.4.0")
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.3.2")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.5")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.1.8")

fullResolvers ~= {_.filterNot(_.name == "jcenter")}
