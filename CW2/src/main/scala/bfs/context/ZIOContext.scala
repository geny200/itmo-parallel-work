package bfs.context

import grpah.{Graph, Vertex, VertexId}
import zio.Runtime.default.unsafeRun
import zio.{URIO, ZIO}

case class ZIOContext(graph: Graph, f: Vertex => Unit) extends Context {

  final def iterationPar(
      visited: Set[VertexId],
      frontier: Set[_ <: VertexId]
  ): ZIO[Any, Throwable, Unit] =
    for {
      vertexes <- ZIO.foreachPar(frontier)(id => URIO(graph.vertex(id)))
      newFrontier <- ZIO.foreachPar(vertexes)(v =>
        URIO(ZIO.filterNot(v.neighbors.toSet)(neighb => URIO(visited.contains(neighb))))
      )
      _            <- ZIO.foreachPar(vertexes)(v => URIO(f(v)))
      newFrontierR <- ZIO.reduceAllPar(URIO(Set()), newFrontier) { (x, y) => x ++ y }
      newVisited = visited ++ frontier
      _ <-
        if (frontier.nonEmpty)
          iterationPar(newVisited, newFrontierR)
        else
          URIO()
    } yield ()

  override def iteration(
      visited: Set[VertexId],
      frontier: Seq[VertexId]
  ): Unit = unsafeRun(iterationPar(visited, frontier.toSet))
}
