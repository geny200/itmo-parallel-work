import io.estatico.newtype.macros.newtype

package object grpah {
  @newtype case class VertexId(id: Int)
}
