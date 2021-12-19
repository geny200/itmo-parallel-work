package grpah

object CubeMapGraph {
  private def unsafeIndex(sideLength: Int)(x: Int, y: Int, z: Int): VertexId =
    VertexId(x + y * sideLength + z * sideLength * sideLength)

  private def index(sideLength: Int)(x: Int, y: Int, z: Int): Option[VertexId] =
    for {
      fst <- test(sideLength)(x)
      snd <- test(sideLength)(y)
      thr <- test(sideLength)(z)
    } yield unsafeIndex(sideLength)(fst, snd, thr)

  private def test(sideLength: Int)(index: Int): Option[Int] =
    if (0 <= index && index < sideLength)
      Some(index)
    else
      None

  def graph(sideLength: Int): MapGraph = {
    val side = 0 until sideLength
    MapGraph(
      (for {
        z <- side
        y <- side
        x <- side
      } yield neighbors(sideLength)(x, y, z))
        .map(v => (v.id, v))
        .toMap
    )
  }

  private def neighbors(sideLength: Int)(x: Int, y: Int, z: Int): Vertex = {
    val indexSide: (Int, Int, Int) => Option[VertexId] = index(sideLength)
    val neigh = List(
      indexSide(x - 1, y, z),
      indexSide(x + 1, y, z),
      indexSide(x, y - 1, z),
      indexSide(x, y + 1, z),
      indexSide(x, y, z + 1),
      indexSide(x, y, z - 1)
    ).flatMap {
      case Some(value) => Some(value)
      case None        => None
    }
    Vertex(unsafeIndex(sideLength)(x, y, z), neigh)
  }
}
