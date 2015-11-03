package com.virdis

import java.util.regex.Pattern

/**
  * Created by sandeep on 11/3/15.
  */
trait TweetCleanUp {

  val nonAscii = "[^\\p{ASCII}]+"
  val newLineOrTab = "\\n|\\t"
  val backSlash = "\\\\"

  val p = Pattern.compile(nonAscii)
  var unicodeCounter: Long = 0L
  /*
    Check if input contains non ascii characters
    replace newline/tab with space
    and remove unicode and backslash
  */
  def cleanAndProcessInput(input: String): String = {
    if (p.matcher(input).find()) {
      unicodeCounter = unicodeCounter + 1
      input.replaceAll(newLineOrTab, " ").replaceAll(nonAscii + "|" + backSlash, "")
    } else input
  }

}

object tweetCleaner extends TweetCleanUp

