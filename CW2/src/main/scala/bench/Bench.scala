package bench

import bfs.Bfs
import bfs.aop.PerformanceBfs
import grpah.{CubeMapGraph, Graph, VertexId}

trait Bench {
  def name: String
  def runTimes: Int
  def cubeSideLength: Int
  def bfs: Bfs with PerformanceBfs

  def run(): Unit = {
    val runner = bfs

    (1 to runTimes)
      .foreach(_ => runner.start(VertexId(0), cube, { _ => }))

    println(
      s"$name: run ${runner.count} times; total - ${runner
        .totalNs(5) / 1000000000d}s;\tavg - ${runner.avg(5) / 1000000000d} s"
    )
  }

  def cube: Graph = CubeMapGraph.graph(cubeSideLength)
}
