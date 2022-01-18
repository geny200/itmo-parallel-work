package bfs

import bfs.iteration.{BfsIteration, SeqBfsIteration}
import graph.{Graph, VertexId}

class SeqBfs extends ContextBfs {
  override def context(graph: Graph, f: Int => VertexId => Unit): BfsIteration =
    SeqBfsIteration(graph, f)
}
