package com.virdis.datachallenge

import com.typesafe.config.{ConfigFactory, Config}
import com.virdis.TwitterHashTagGraph


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
    object twitterGraph extends TwitterHashTagGraph
    twitterGraph.run(config)
  }
}

