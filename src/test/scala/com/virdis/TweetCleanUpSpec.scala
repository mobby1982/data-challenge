package com.virdis

/**
  * Created by sandeep on 11/3/15.
  */
class TweetCleanUpSpec extends MySpec {

  def fixture = {
    new {
      val s1 = "I'm at Terminal de Integra\u00e7\u00e3o do Varadouro in Jo\u00e3o Pessoa, PB https:\\/\\/t.co\\/HOl34REL1a"
      val s2 = "Spark Summit East this week! #Spark #Apache"

    }
  }

  "TweetCleanUp" should "should remove unicode character" in {
    val f = fixture
    val result = "I'm at Terminal de Integrao do Varadouro in Joo Pessoa, PB https://t.co/HOl34REL1a"
    tweetCleaner.cleanAndProcessInput(f.s1) == result should be(true)
    tweetCleaner.cleanAndProcessInput(f.s1).length should be(result.length)
  }

  it should "handle ascii characters correctly" in {
    val f = fixture
    tweetCleaner.cleanAndProcessInput(f.s2) == f.s2 should be(true)
    tweetCleaner.cleanAndProcessInput(f.s2).length should be(f.s2.length)
  }

  it should "check for unicode characters" in {
    val f = fixture
    tweetCleaner.checkNonAsciiCharacters(f.s1) should be(true)
    tweetCleaner.checkNonAsciiCharacters(f.s2) should be(false)
  }

}
