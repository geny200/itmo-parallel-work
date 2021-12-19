package bfs

import bfs.context.{Context, ZIOContext}
import grpah.{Graph, Vertex}

class ZIOBfs extends ContextBfs {
  override def context(graph: Graph, f: Vertex => Unit): Context =
    ZIOContext(graph, f)
}
