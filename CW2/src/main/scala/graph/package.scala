import io.estatico.newtype.macros.newtype

package object graph {
  @newtype case class VertexId(id: Int)
}
