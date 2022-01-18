package bfs.iteration

import graph.{Graph, VertexId}

import scala.annotation.tailrec

case class SeqBfsIteration(graph: Graph, f: Int => VertexId => Unit) extends BfsIteration {

  case class Visited(visited: Vector[Boolean], frontier: List[VertexId]) {
    def add(vertex: VertexId): Visited =
      Visited(visited.updated(vertex.id, true), vertex :: frontier)
  }

  @tailrec
  final override def iteration(
      visited: Vector[Boolean],
      frontier: List[VertexId],
      length: Int
  ): Unit = {
    val visit = frontier
      .foldLeft(Visited(visited, List())) { (visitAcc: Visited, visitedId: VertexId) =>
        graph
          .vertex(visitedId)
          .neighbors
          .foldLeft(visitAcc)((acc: Visited, id: VertexId) =>
            if (acc.visited(id.id)) acc
            else acc.add(id)
          )
      }

    frontier.foreach(f(length))

    if (visit.frontier.nonEmpty)
      iteration(visit.visited, visit.frontier, length + 1)
  }
}
