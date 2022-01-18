package bfs

import bfs.iteration.BfsIteration
import graph.{Graph, VertexId}

trait ContextBfs extends BfsRun {
  def context(graph: Graph, f: Int => VertexId => Unit): BfsIteration

  override def start(
      first: VertexId,
      graph: Graph,
      f: Int => VertexId => Unit
  ): Unit =
    context(graph, f).iteration(
      ((0 until graph.size).map(_ => false)).toVector.updated(0, true),
      List(first),
      0
    )
}
