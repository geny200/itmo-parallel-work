package bfs

import bfs.context.Context
import grpah.{Graph, Vertex}

trait ContextBfs extends Bfs {
  def context(graph: Graph, f: Vertex => Unit): Context

  override def start(
      first: grpah.VertexId,
      graph: Graph,
      f: Vertex => Unit
  ): Unit =
    context(graph, f).iteration(Set(), Seq(first))
}
