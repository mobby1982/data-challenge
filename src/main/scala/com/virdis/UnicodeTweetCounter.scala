package com.virdis

import java.io.{File, FileOutputStream, OutputStreamWriter, BufferedWriter}

import com.typesafe.config.Config
import tweetParser._
import tweetCleaner._
/**
  * Created by sandeep on 11/3/15.
  */

trait UnicodeTweetCounter extends LoanPattern {

  val FEATURE_1_OUTPUT_FILENAME = "ft1.txt"
  var counter: Long = 0L
  def run(config: Config) = {
    using(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
      config.getString("directory.output") + FEATURE_1_OUTPUT_FILENAME))))){
      writer =>
        using(io.Source.fromFile(config.getString("directory.input") + "tweets.txt")) {
          readerSrc =>
            val lines: Iterator[String] = readerSrc.getLines()
            while (lines.hasNext) {
              val line = lines.next()
              val ffData: Option[FirstFeature] = firstFeatureFormat(line)
              if (ffData.nonEmpty) {
                val builder = new StringBuilder
                builder.append(ffData.get.text)
                builder.append(" ")
                builder.append("(timestamp: ")
                builder.append(ffData.get.created_at)
                builder.append(")")
                writer.write(builder.toString())
                writer.newLine()
              }
            }
        }
        writer.write("" + unicodeCounter + " tweets contained unicode.")
    }
  }



}
