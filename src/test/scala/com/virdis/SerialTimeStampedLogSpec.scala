package com.virdis

import org.joda.time.DateTime

/**
  * Created by sandeep on 11/2/15.
  */
class SerialTimeStampedLogSpec extends MySpec {

  val dt = new DateTime

  trait lessThan60 {
    val sf1 = SecondFeature(Seq("spark", "apache"), dt)
    val sf2 = SecondFeature(Seq("apache", "hadoop", "storm"), dt.plusSeconds(10))
    val sf3 = SecondFeature(Seq("flink", "spark"), dt.plusSeconds(15))
    val sf4 = SecondFeature(Seq("hbase", "spark"), dt.plusSeconds(25))
  }

  trait duplicateForKey {
    val sf1 = SecondFeature(Seq(), dt)
    val sf2 = SecondFeature(Seq("apache", "hadoop", "storm"), dt)
    val sf3 = SecondFeature(Seq("flink", "spark"), dt)
    val sf4 = SecondFeature(Seq("hbase", "spark"), dt)
  }


  trait greaterThan60 {
    val sf5 = SecondFeature(Seq("hadoop", "apache"), dt.plusSeconds(61))
  }

  def fixture = new {
    val tsLog = new SerialTimeStampedLog {}
  }

  "SerialTimeStampedLog" should "add parsed tweets data" in  new lessThan60 {
    val f = fixture
    f.tsLog.add(sf1)
    f.tsLog.add(sf2)
    f.tsLog.add(sf3)
    f.tsLog.add(sf4)
    f.tsLog should have size(4)
  }

  it should "maintain insertion order while traversing all keys" in  new lessThan60 {
    val f = fixture
    f.tsLog.add(sf1)
    f.tsLog.add(sf2)
    f.tsLog.add(sf3)
    f.tsLog.add(sf4)
    f.tsLog.allKeys.toList should contain theSameElementsInOrderAs(List(sf1.created_at,
                                                                                sf2.created_at, sf3.created_at, sf4.created_at))
  }

  it should "handle duplicate data for a key" in new duplicateForKey {
    val f = fixture
    f.tsLog.add(sf1)
    f.tsLog.add(sf2)
    f.tsLog.add(sf3)
    f.tsLog.add(sf4)
    f.tsLog.size should be(1)
  }

  it should "return stale keys, for timestamps greater than 60 seconds compared to timestamp of current " +
    "tweet being processed" in new lessThan60 with greaterThan60 {

    val f = fixture
    f.tsLog.add(sf1)
    f.tsLog.add(sf2)
    f.tsLog.add(sf3)
    f.tsLog.add(sf4)

    f.tsLog.getStaleKeys(sf4) shouldBe empty

    f.tsLog.add(sf5)

    val staleKeys = f.tsLog.getStaleKeys(sf5)
    staleKeys should not be empty
    staleKeys should contain (dt)
  }

  it should "purge all stale keys/data and return hashtags associated with stale keys" in new lessThan60 with greaterThan60 {
    val f = fixture
    f.tsLog.add(sf1)
    f.tsLog.add(sf2)
    f.tsLog.add(sf3)
    f.tsLog.add(sf4)

    f.tsLog.add(sf5)
    val staleKeys  = f.tsLog.getStaleKeys(sf5)
    val htags      = f.tsLog.purgeAndReturnTags(staleKeys)
    
    htags should contain theSameElementsAs(sf1.hashtags)
    f.tsLog.size should be(4)
  }

}
