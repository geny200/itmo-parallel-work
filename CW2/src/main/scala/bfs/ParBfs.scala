package bfs

import bfs.iteration.{BfsIteration, ParBfsIteration}
import graph.{Graph, VertexId}

class ParBfs extends ContextBfs {
  override def context(graph: Graph, f: Int => VertexId => Unit): BfsIteration =
    ParBfsIteration(graph, f)
}
