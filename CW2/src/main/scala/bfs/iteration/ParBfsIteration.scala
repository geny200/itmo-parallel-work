package bfs.iteration

import graph.{Graph, VertexId}

import scala.annotation.tailrec
import scala.collection.parallel.CollectionConverters.ImmutableSeqIsParallelizable
import scala.collection.parallel.immutable.ParSeq

case class ParBfsIteration(graph: Graph, f: Int => VertexId => Unit) extends BfsIteration {

  @tailrec
  final override def iteration(
      visited: Vector[Boolean],
      frontier: List[VertexId],
      length: Int
  ): Unit = {
    val newFrontier: ParSeq[VertexId] =
      frontier.par
        .map(id => graph.vertex(id))
        .flatMap(_.neighbors)
        .distinct
        .filterNot(v => visited(v.id))

    val updatedVisit =
      newFrontier
        .foldLeft(visited)((visitedAcc, v) => visitedAcc.updated(v.id, true))

    frontier.par.foreach(f(length))

    if (newFrontier.nonEmpty)
      iteration(updatedVisit, newFrontier.toList, length + 1)
  }
}
