package grpah

case class Vertex(
    id: VertexId,
    neighbors: Seq[VertexId]
)
