package bfs
import graph.{Graph, VertexId}

trait BfsRun {
  def start(first: VertexId, graph: Graph, f: Int => VertexId => Unit): Unit
}
