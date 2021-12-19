package error

object VertexNotFoundError extends GraphError {
  override def message: String = "Vertex not found"
}
