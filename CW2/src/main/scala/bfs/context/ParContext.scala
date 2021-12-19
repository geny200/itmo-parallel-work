package bfs.context

import grpah.{Graph, Vertex, VertexId}

import scala.annotation.tailrec
import scala.collection.parallel.CollectionConverters.ImmutableSeqIsParallelizable
import scala.collection.parallel.immutable.ParSeq

case class ParContext(graph: Graph, f: Vertex => Unit) extends Context {

  @tailrec
  final def iterationPar(
      visited: Set[VertexId],
      frontier: Seq[VertexId]
  ): Unit = {
    val vertexes: ParSeq[Vertex] = frontier.par.map(graph.vertex).distinct
    val newFrontier: ParSeq[VertexId] =
      vertexes.flatMap(_.neighbors).filterNot(id => visited.contains(id))
    val newVisited: Set[VertexId] = visited ++ frontier
    vertexes.foreach(f)

    if (frontier.nonEmpty)
      iterationPar(newVisited, newFrontier.seq)
  }

  override def iteration(
      visited: Set[VertexId],
      frontier: Seq[VertexId]
  ): Unit =
    iterationPar(visited, frontier)
}
