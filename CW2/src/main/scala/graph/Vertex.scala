package graph

case class Vertex(
    id: VertexId,
    neighbors: Seq[VertexId]
)
