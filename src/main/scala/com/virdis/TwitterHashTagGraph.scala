package com.virdis

import java.io.{OutputStreamWriter, FileOutputStream, File, BufferedWriter}

/**
 * Created by sandeep on 11/1/15.
 */

trait TwitterHashTagGraph extends TweetParser with LoanPattern {

  val graph = new Graph()

  def run = {
    using(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("tweet_output/test.txt"))))){
      writer =>
        using( io.Source.fromFile("tweet_input/tweets.txt")) {
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
