package grpah

trait Graph {
  def vertex(vertexId: VertexId): Vertex
}
