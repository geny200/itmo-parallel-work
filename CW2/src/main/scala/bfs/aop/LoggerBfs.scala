package bfs.aop

import bfs.BfsRun
import graph.{Graph, VertexId}

trait LoggerBfs extends BfsRun {
  abstract override def start(
      first: VertexId,
      graph: Graph,
      f: Int => VertexId => Unit
  ): Unit = {
    println("Run bfs")
    val result: Unit = super.start(first, graph, f)
    println("End bfs")
    result
  }
}
