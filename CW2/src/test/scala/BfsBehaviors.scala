import bfs.Bfs
import grpah.{CubeMapGraph, MapGraph, Vertex, VertexId}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

trait BfsBehaviors {
  this: AnyFlatSpec with Matchers =>

  def validBfs(testedBfs: => Bfs): Unit = {
    trait TestBfs {
      val bfs: Bfs                       = testedBfs
      val visit: mutable.Set[Int]        = mutable.Set()
      val lengths: mutable.Map[Int, Int] = mutable.Map()
      val f: Int => Vertex => Unit = (length: Int) =>
        (v: Vertex) => {
          visit.add(v.id.id) shouldBe true
          lengths.update(v.id.id, length)
        }

      def testWithGraphCubeN(sideLength: Int): Unit = {
        val graph: MapGraph = CubeMapGraph.graph(sideLength)
        val graphSize       = sideLength * sideLength * sideLength

        bfs.start(VertexId(0), graph, f)

        lengths.get(graphSize - 1) shouldBe Some(sideLength - 1)
        visit.diff((0 until graphSize).toSet) shouldBe Set()
        (0 until graphSize).toSet.diff(visit) shouldBe Set()
      }
    }

    it should "call callback function once for Graph with one Vertex" in new TestBfs {
      testWithGraphCubeN(1)
    }

    it should "correctly evaluate GraphCUbe with side 5" in new TestBfs {
      testWithGraphCubeN(6)
    }
  }
}
