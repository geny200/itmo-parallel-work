package bfs.context

import grpah.{Graph, Vertex, VertexId}

import scala.annotation.tailrec

case class SeqContext(graph: Graph, f: Int => Vertex => Unit) extends Context {

  @tailrec
  final def iteration(
      visited: Set[VertexId],
      frontier: Seq[VertexId],
      length: Int
  ): Unit = {
    val vertexes: Seq[Vertex] = frontier.map(graph.vertex).distinct
    val newFrontier: Seq[VertexId] =
      vertexes.flatMap(_.neighbors).filterNot(id => visited.contains(id))
    val newVisited: Set[VertexId] = visited ++ frontier
    vertexes.foreach(f(length))

    if (frontier.nonEmpty)
      iteration(newVisited, newFrontier, length + 1)
  }
}
