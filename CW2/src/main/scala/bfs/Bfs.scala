package bfs
import grpah.{Graph, Vertex, VertexId}

trait Bfs {
  def start(first: VertexId, graph: Graph, f: Int => Vertex => Unit): Unit
}
