package bfs

import bfs.context.{Context, SeqContext}
import grpah.{Graph, Vertex}

class SeqBfs extends ContextBfs {
  override def context(graph: Graph, f: Vertex => Unit): Context =
    SeqContext(graph, f)
}
