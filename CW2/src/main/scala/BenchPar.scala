import bench.CommonBench
import bfs.ParBfs
import bfs.aop.{LoggerBfs, PerformanceBfs}

object BenchPar extends CommonBench {
  override def name: String = "Par"
  override def bfs          = new ParBfs with PerformanceBfs with LoggerBfs

  def main(args: Array[String]): Unit =
    run()
}
