package com.virdis

import org.joda.time.DateTime
import scala.collection.mutable

/**
 * Created by sandeep on 11/1/15.
 */

trait SerialTimeStampedLog {

  /*
      We need to preserve the order of incoming tweets
      We use linkedhashmap to create a serial log where key -> timestamp and value -> Set of hashtags

      By using linkedhashmap we have fast lookup / updates

      While searching the serial log, we have have to traverse all the elements, but
      since this is a serial log ( oldest entries first ), we would expect the condition
      Tn - T0 > 60 to be met by scanning the first new entries in the list.

   */

  val serialTsLog = new mutable.LinkedHashMap[DateTime, Set[String]]()

  /*
      add new data Key ( timestamp ) -> Value ( Set(hashtags) )
      update existing data Key ( timestamp ) -> Value ( old data + Set(new hashtags) )
   */
  def add(tweet: SecondFeature) = {
    if (serialTsLog.contains(tweet.created_at)){
      serialTsLog.update(tweet.created_at, serialTsLog(tweet.created_at) ++ Set(tweet.hashtags: _*))
    } else {
      serialTsLog += ((tweet.created_at, Set(tweet.hashtags: _*)))
    }
  }

  /*
      Check current Ts against old Ts and return a list of all Ts ( timestamps ) greater 60 seconds
   */
  def allStaleEntries(currTweet: SecondFeature): List[DateTime] = {
    val keysIter: Iterator[DateTime] = serialTsLog.keysIterator
    var staleKeys = List.empty[DateTime]

    while (keysIter.hasNext) {
      val previous = keysIter.next()
      if (checkTimeDelta(previous, currTweet.created_at)) staleKeys = previous +: staleKeys
      else
        return staleKeys
    }
    staleKeys
  }



}
