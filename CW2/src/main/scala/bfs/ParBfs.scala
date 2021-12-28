package bfs

import bfs.context.{Context, ParContext}
import grpah.{Graph, Vertex}

class ParBfs extends ContextBfs {
  override def context(graph: Graph, f: Int => Vertex => Unit): Context =
    ParContext(graph, f)
}
