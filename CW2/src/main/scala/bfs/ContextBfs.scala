package bfs

import bfs.context.Context
import grpah.{Graph, Vertex}

trait ContextBfs extends Bfs {
  def context(graph: Graph, f: Int => Vertex => Unit): Context

  override def start(
      first: grpah.VertexId,
      graph: Graph,
      f: Int => Vertex => Unit
  ): Unit =
    context(graph, f).iteration(Set(), Seq(first), 0)
}
