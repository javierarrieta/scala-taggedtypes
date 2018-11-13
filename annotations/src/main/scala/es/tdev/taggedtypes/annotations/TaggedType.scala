package es.tdev.taggedtypes.annotations

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import scala.annotation.StaticAnnotation
import scala.annotation.compileTimeOnly

@compileTimeOnly("enable macro paradise to expand macro annotations")
class TaggedType[A] extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro TaggedType.impl

}

object TaggedType {
  private final val suffix = "Tag"

  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

    val extendedTypename: TypeName = c.prefix.tree match {
      case q"new TaggedType[$t]()" => TypeName(show(t))
      case _ => c.abort(c.enclosingPosition, "Could not detect class to extend, use @TaggedType[my_class]")
    }

    def valid(t: TypeName): Boolean = t.toString.endsWith(suffix)

    def expandTrait(traitName: TypeName) = {
      val typeName = TypeName(traitName.decodedName.toString.dropRight(suffix.length))
      val termName = TermName(typeName.toString)

      //Desugaring instead of going through `shapeless.tag[$traitName](v)`
      q"""
        sealed trait $traitName
        type $typeName = shapeless.tag.@@[$extendedTypename, $traitName]
        object $termName { def apply(v: $extendedTypename) = v.asInstanceOf[$typeName] }
       """
    }

    annottees.map(_.tree).toList match {
      case (q"sealed trait $name")  :: Nil if valid(name) => c.Expr[Any](expandTrait(name))
      case _ => c.abort(c.enclosingPosition, "Invalid annottee, need to annotate a sealed trait ending in " + suffix)
    }
  }

}