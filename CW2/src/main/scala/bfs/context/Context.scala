package bfs.context

import grpah.{Graph, Vertex, VertexId}

trait Context {
  def graph: Graph
  def f: Int => Vertex => Unit

  def iteration(visited: Set[VertexId], frontier: Seq[VertexId], length: Int): Unit
}
