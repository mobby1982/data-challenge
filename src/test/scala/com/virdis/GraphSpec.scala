package com.virdis

import org.joda.time.DateTime

import scala.collection.immutable.Range
import scala.math

/**
  * Created by sandeep on 11/2/15.
  */
class GraphSpec extends MySpec {

  val dt = new DateTime

  trait tweetData {
    val sf1 = SecondFeature(Seq("spark", "apache"), dt)
    val sf2 = SecondFeature(Seq("apache", "hadoop", "storm"), dt.plusSeconds(10))
    val sf3 = SecondFeature(Seq("flink", "spark"), dt.plusSeconds(15))
    val sf4 = SecondFeature(Seq("apache"), dt.plusSeconds(25))
    val sf5 = SecondFeature(Seq("hbase", "spark"), dt.plusSeconds(122))
  }

  trait incompleteData {
    val sf1 = SecondFeature(Seq("spark"), dt)
  }

  def fixture = new {
    val graph = new Graph()
  }

  "A Graph" should "process tweet data" in new tweetData {
    val f = fixture
    f.graph.processData(sf1)
    f.graph.processData(sf2)
    f.graph.processData(sf3)
    f.graph.noOfNodes should be(5)
  }

  it should "process incomplete data properly" in new incompleteData {
    val f = fixture
    f.graph.processData(sf1)
    f.graph.noOfNodes should be(0)
  }

  it should "maintain averageDegree" in new tweetData {
    val f = fixture
    f.graph.processData(sf1)
    f.graph.processData(sf2)
    f.graph.processData(sf3)
    f.graph.processData(sf4)

    f.graph.averageDegree should be(BigDecimal(2.00))

    f.graph.processData(sf5)
    f.graph.averageDegree should be(BigDecimal(2.00))

    f.graph.updateGraph(sf1.hashtags.toSet)
    f.graph.averageDegree should be(BigDecimal(1.67))
  }

  it should "remove nodes and update the graph" in new tweetData {
    val f = fixture
    f.graph.processData(sf1)
    f.graph.processData(sf2)
    f.graph.processData(sf3)
    f.graph.processData(sf4)
    f.graph.processData(sf5)

    f.graph.updateGraph(sf1.hashtags.toSet)
    f.graph.noOfNodes should be(6)

  }
}
