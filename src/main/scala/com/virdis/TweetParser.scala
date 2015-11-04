package com.virdis

/**
 * Created by sandeep on 11/1/15.
 */

import org.joda.time.DateTime
import play.api.libs.json._

import scala.util.control.NonFatal


case class FirstFeature(text: String, created_at: String)
case class SecondFeature(hashtags: Seq[String], created_at: DateTime)

trait TweetParser {

  /*
      For both features we only care about tweet data in correct format,
      if the data is in incorrect format,
      after catching Parsing Exception we do nothing
   */

  def secondFeatureFormat(input: String): Option[SecondFeature] = {
    try {
      val js = Json.parse(input)

      /*
          all tags are converted to lowercase
          all unciode characters, empty tags and duplicates are removed
       */
      val hashtags = (js \ "entities" \ "hashtags" \\ "text").map(_.as[String] toLowerCase)
      val cleanedTags = hashtags.foldLeft(Set.empty[String]) {
        (b, a) =>
          val s = tweetCleaner.cleanAndProcessInput(a)
          if (s.nonEmpty) b + s else b
      }.toSeq

      Option(SecondFeature(
        cleanedTags,
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
      val txt = (js \ "text").as[String]
      Option(FirstFeature(
        tweetCleaner.cleanAndProcessInput(txt),
        (js \ "created_at").as[String]
      ))
    } catch {
      case NonFatal(e)  => {
        None
      }
    }
  }

}

object tweetDataParser extends TweetParser