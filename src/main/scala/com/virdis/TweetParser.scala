package com.virdis

/**
 * Created by sandeep on 11/1/15.
 */

import org.joda.time.DateTime
import play.api.libs.json._

import scala.util.control.NonFatal

case class FirstFeature(text: String, created_at: DateTime)
case class SecondFeature(hashtags: Seq[String], created_at: DateTime)

trait TweetParser {

  /*
      We only care about tweet data in correct format,
      if the data is in incorrect format,
      after catching Parsing Exception we do nothing
   */
  def secondFeatureFormat(input: String): Option[SecondFeature] = {
    try {
      val js = Json.parse(input)
      Option(SecondFeature(
        (js \ "entities" \ "hashtags" \\ "text").map(_.as[String] toLowerCase),
        (js \ "created_at").as[DateTime]
      ))
    } catch {
      case NonFatal(e)  => {
        None
      }
    }
  }

  def firstFeatureFormat(input: String): Option[FirstFeature] = {
    try {
      val js = Json.parse(input)
      Option(FirstFeature(
        (js \ "text").as[String],
        (js \ "created_at").as[DateTime]
      ))
    } catch {
      case NonFatal(e)  => {
        None
      }
    }
  }
}

object tweetParser extends TweetParser