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
  private var unicodeCounter: Long = 0L

  /*
      Check non ascii characters
   */
  def checkNonAsciiCharacters(input:String): Boolean = {
    p.matcher(input).find()
  }
  /*
    Check if input contains non ascii characters
    replace newline/tab with space
    and remove unicode and backslash
  */
  def cleanAndProcessInput(input: String): String = {
    if (checkNonAsciiCharacters(input)) {
      unicodeCounter = unicodeCounter + 1
      input.replaceAll(newLineOrTab, " ").replaceAll(nonAscii + "|" + backSlash, "")
    } else input
  }

  def unicodeCharacterCount = unicodeCounter
}

object tweetCleaner extends TweetCleanUp

