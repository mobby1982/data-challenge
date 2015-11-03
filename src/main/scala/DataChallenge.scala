package com.virdis.datachallenge

import com.virdis.TwitterHashTagGraph


object DataChallenge {
  def main(args: Array[String]) {
    object twitterGraph extends TwitterHashTagGraph
    val start = System.currentTimeMillis()
    twitterGraph.run
    println("RunTime  "+ (System.currentTimeMillis() - start))
  }
}

