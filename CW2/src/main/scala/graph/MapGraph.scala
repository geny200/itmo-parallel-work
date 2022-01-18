package graph

case class MapGraph(
    graph: Map[VertexId, Vertex]
) extends Graph {
  override def vertex(vertexId: VertexId): Vertex =
    graph(vertexId)
  override def size(): Int = graph.size
}
