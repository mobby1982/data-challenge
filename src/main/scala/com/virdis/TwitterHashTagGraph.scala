package com.virdis

import java.io.{OutputStreamWriter, FileOutputStream, File, BufferedWriter}

import com.typesafe.config.Config
import tweetDataParser._
/**
 * Created by sandeep on 11/1/15.
 */

trait TwitterHashTagGraph extends LoanPattern {

  val FEATURE_2_OUTPUT_FILENAME = "ft2.txt"

  val graph = new Graph()

  def run(config: Config) = {
    using(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
      config.getString("directory.output") + FEATURE_2_OUTPUT_FILENAME))))){
      writer =>
        using(io.Source.fromFile(config.getString("directory.input") + "tweets.txt")) {
          readerSrc =>
            val lines: Iterator[String] = readerSrc.getLines()
            while (lines.hasNext) {
              val line = lines.next()
              val sfData: Option[SecondFeature] = secondFeatureFormat(line)
              if (sfData.nonEmpty) {
                serialTimeStampLog.add(sfData.get)
                graph.processData(sfData.get)
                val staleKeys = serialTimeStampLog.getStaleKeys(sfData.get)
                val staleTags = serialTimeStampLog.purgeAndReturnTags(staleKeys)
                graph.updateGraph(staleTags)
                writer.write(graph.averageDegree.toString())
                writer.newLine()
              }
            }
        }
    }

  }

}
