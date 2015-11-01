package com.virdis

/**
 * Created by sandeep on 11/1/15.
 */

import org.joda.time.DateTime
import play.api.libs.json._

case class SecondFeature(hashtags: Seq[String], created_at: DateTime)

trait TweetParser {

  def secondFeatureFormat(input: String): SecondFeature = {
    val js = Json.parse(input)
    SecondFeature(
      (js \ "entities" \ "hashtags" \\ "text").map(_.as[String] toLowerCase) ,
      (js \ "created_at").as[DateTime]
    )
  }
}

object tweetParser extends TweetParser