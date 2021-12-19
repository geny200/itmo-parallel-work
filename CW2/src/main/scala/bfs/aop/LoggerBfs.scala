package bfs.aop

import bfs.Bfs
import grpah.{Graph, Vertex}

trait LoggerBfs extends Bfs {
  abstract override def start(
      first: grpah.VertexId,
      graph: Graph,
      f: Vertex => Unit
  ): Unit = {
    println("Run bfs")
    val result: Unit = super.start(first, graph, f)
    println("End bfs")
    result
  }
}
