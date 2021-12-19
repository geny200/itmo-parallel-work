import bench.CommonBench
import bfs.SeqBfs
import bfs.aop.{LoggerBfs, PerformanceBfs}

object BenchSeq extends CommonBench {
  override def name: String = "Seq"
  override def bfs          = new SeqBfs with PerformanceBfs with LoggerBfs

  def main(args: Array[String]): Unit =
    run()
}
