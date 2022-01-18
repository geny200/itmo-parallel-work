package graph

trait Graph {
  def vertex(vertexId: VertexId): Vertex
  def size(): Int
}
