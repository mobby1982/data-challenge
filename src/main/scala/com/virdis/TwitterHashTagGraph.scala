package com.virdis

/**
 * Created by sandeep on 11/1/15.
 */

trait TwitterHashTagGraph extends TweetParser with LoanPattern {

  val graph = new Graph()

  def buildAndProcessGraph = {
    using(io.Source.fromFile("tweet_input/tweets.txt")) {
      readerSrc =>
        val lines: Iterator[String] = readerSrc.getLines()

        while (lines.hasNext) {
          val line = lines.next()

          val sfData: SecondFeature = secondFeatureFormat(line)

          serialTimeStampLog.add( sfData )
          graph.processData( sfData )

          val staleKeys = serialTimeStampLog.getStaleKeys( sfData )
          val staleTags = serialTimeStampLog.purgeAndReturnTags( staleKeys )

          graph.updateGraph( staleTags )
        }

    }

  }

}
