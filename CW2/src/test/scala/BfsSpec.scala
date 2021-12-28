import bfs.{ParBfs, SeqBfs}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BfsSpec extends AnyFlatSpec with Matchers with BfsBehaviors {

  behavior of "Seq bfs"
  it should behave like validBfs(new SeqBfs)

  behavior of "Par bfs"
  it should behave like validBfs(new ParBfs)
}
