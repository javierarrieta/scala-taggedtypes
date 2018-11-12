# Scala Tagged Types


[![Build status](https://travis-ci.org/javierarrieta/scala-taggedtypes.svg?branch=master)](https://travis-ci.org/javierarrieta/scala-taggedtypes)

This small library uses macros to reduce the boilerplate when using shapeless tagged types.

To be able to use this macro you will need to add the macro paradise plugin compiler to your build:

```scala
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
```
Usually you will have to define all this for a tagged type:

```scala
import shapeless.tag, tag.@@

object models {
  sealed trait MySpecialStringTag
  
  type MySpecialString = String @@ MySpecialStringTag
  
  object MySpecialString {
    def apply(v: String): MySpecialString = tag[MySpecialStringTag](v)
  }
}
```

Using this macro you will only have to annotate the `sealed trait`.
**Important:** your sealed trait has to end with the `Tag` suffix.

```scala
import jarrieta.taggedtypes.annotations.TaggedType

@TaggedType[String] sealed trait MyCustomStringTag

```