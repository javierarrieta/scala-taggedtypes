package es.tdev.taggedtypes.annotations

import org.scalatest.{FlatSpec, Matchers}

object models {

  @TaggedType[String] sealed trait MyCustomStringTag
  @TaggedType[Int] sealed trait MyCustomIntTag

}

class TaggedTypeSpec extends FlatSpec with Matchers {
  import models._

  "A TaggedType annotation" should "create a type alias" in {

    "a".asInstanceOf[MyCustomString] shouldBe MyCustomString("a")
    shapeless.tag[MyCustomIntTag](1) shouldBe MyCustomInt(1)
  }
}
