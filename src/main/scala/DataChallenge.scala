package com.virdis.datachallenge

import com.typesafe.config.{ConfigFactory, Config}
import com.virdis.{UnicodeTweetCounter, TwitterHashTagGraph}


object DataChallenge {
  def main(args: Array[String]) {

    if (args.isEmpty) {
      start(ConfigFactory.load("application.conf"))
    } else if (args(0).equals("test")) {
      start(ConfigFactory.load("test.conf"))
    } else if (args(0).equals("prod")) {
      start(ConfigFactory.load("application.conf"))
    }

  }

  def start(config: Config) = {
    println("Started......")
    object twitterGraph extends TwitterHashTagGraph
    object unicodeCounter extends UnicodeTweetCounter
    unicodeCounter.run(config)
    twitterGraph.run(config)
    println("Done!!!!!")
  }
}

