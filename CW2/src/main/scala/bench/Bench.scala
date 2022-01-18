package bench

import bfs.BfsRun
import bfs.aop.PerformanceBfs
import graph.{CubeMapGraph, Graph, VertexId}

trait Bench {
  def name: String
  def runTimes: Int
  def cubeSideLength: Int
  def bfs: BfsRun with PerformanceBfs

  def run(): Unit = {
    val runner = bfs

    (1 to runTimes)
      .foreach(_ => runner.start(VertexId(0), cube, { _ => _ => }))

    println(
      s"$name: run ${runner.count} times; total - ${runner
        .totalNs(5) / 1000000000d}s;\tavg - ${runner.avg(5) / 1000000000d} s"
    )
  }

  def cube: Graph = CubeMapGraph.graph(cubeSideLength)
}
