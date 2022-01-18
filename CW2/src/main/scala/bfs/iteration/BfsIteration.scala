package bfs.iteration

import graph.{Graph, Vertex, VertexId}

trait BfsIteration {
  def graph: Graph
  def f: Int => VertexId => Unit

  def iteration(visited: Vector[Boolean], frontier: List[VertexId], length: Int): Unit
}
