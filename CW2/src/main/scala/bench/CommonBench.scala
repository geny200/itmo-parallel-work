package bench

trait CommonBench extends Bench {
  override def runTimes: Int       = 5
  override def cubeSideLength: Int = 200
}
