package com

import org.joda.time.{Seconds, DateTime}
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.Reads

/**
 * Created by sandeep on 11/1/15.
 */
package object virdis {

  val TWEET_TS_FORMAT = DateTimeFormat.forPattern("EEE MMM d H:m:s Z y")

  val ONE_MINUTE_IN_SECONDS = 60

  implicit val readsJodaDateTime = Reads[DateTime](js =>
    js.validate[String].map[DateTime](dtString =>
      TWEET_TS_FORMAT.parseDateTime(dtString)
    )
  )


  def checkTimeDelta(start: DateTime, end: DateTime): Boolean = {
    Seconds.secondsBetween(start, end).getSeconds > ONE_MINUTE_IN_SECONDS
  }
}
