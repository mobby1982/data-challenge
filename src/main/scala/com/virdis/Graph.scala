package com.virdis

import scala.collection.immutable.HashMap

/**
 * Created by sandeep on 11/1/15.
 */

class Graph {

  /*
         To store the graph's state we use a hashmap where

         key -> Node and value -> Set of Adjacent edges

         Manipulating the internal state of the graph ( add/remove ) nodes is effectively O(1)
   */

  private var adjacencyStore = HashMap.empty[String, Set[String]]

  def processData(currTweet: SecondFeature) = {
    if (currTweet.hashtags.size > 1) { // process only if more than one hashtag
      val combinationsOfTags: Iterator[Seq[String]] = currTweet.hashtags.combinations(2) //creates combinations of length 2

      combinationsOfTags.foreach {
        tags =>
          val from = tags.head
          val to   = tags.tail.head
          addEdge(from, to)
          addEdge(to, from)
      }
    }

  }

  /*
      add data if it does not exist
      update existing Set with new data
   */

  private[this] def addEdge(node1: String, node2: String) = {
    if (adjacencyStore.contains(node1)){
      adjacencyStore = adjacencyStore + ((node1, adjacencyStore(node1) + node2))
    } else {
      adjacencyStore = adjacencyStore + ((node1, Set(node2)))
    }
  }

  /*
      updates the graph edges for each hashtag in the set

      &~ creates the difference of 2 sets
      val s1 = Set(1,2,3)
      val s2 = Set(2,3,4,5,6)
      s1 &~ s2 = Set(1)
      
   */
  def updateGraph(hashTags: Set[String]) = {
    hashTags.foreach {
      htag =>
        if (adjacencyStore.contains(htag)) {
          val setDiff: Set[String] = adjacencyStore(htag) &~ hashTags
          if (setDiff.isEmpty) {
            adjacencyStore = adjacencyStore - htag
          } else {
            adjacencyStore = adjacencyStore + ((htag, setDiff))
          }
        }
    }
  }

  def noOfNodes = adjacencyStore.size

  def sumOfDegrees: Double = {
    var sumOfDegrees: Double = 0.00
    val keysIter: Iterator[String] = adjacencyStore.keysIterator

    while (keysIter.hasNext) {
      sumOfDegrees = sumOfDegrees + adjacencyStore(keysIter.next()).size
    }
    sumOfDegrees
  }

  def averageDegree: BigDecimal = {
    if (adjacencyStore.isEmpty) BigDecimal(0.00).setScale(2)
    else {
      BigDecimal( sumOfDegrees / noOfNodes ).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }
  }

  override def toString = adjacencyStore.toString()
}
