package bfs.aop

import bfs.BfsRun
import graph.{Graph, VertexId}

trait PerformanceBfs extends BfsRun {
  private var total: Seq[Long] = Seq()

  abstract override def start(
      first: VertexId,
      graph: Graph,
      f: Int => VertexId => Unit
  ): Unit = {
    val t0 = System.nanoTime()
    super.start(first, graph, f)
    val t1 = System.nanoTime()
//    println(t1 - t0)
    total = (t1 - t0) +: total
  }

  def avg(last: Int): Double = {
    val lastNTimes = total.take(last)
    lastNTimes.sum / lastNTimes.length.toDouble
  }

  def totalNs(last: Int): Long =
    total.take(last).sum

  def count: Int =
    total.length
}
