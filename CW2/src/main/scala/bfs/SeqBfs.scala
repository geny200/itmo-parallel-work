package bfs

import bfs.context.{Context, SeqContext}
import grpah.{Graph, Vertex}

class SeqBfs extends ContextBfs {
  override def context(graph: Graph, f: Int => Vertex => Unit): Context =
    SeqContext(graph, f)
}
