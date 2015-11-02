package com.virdis.datachallenge

import com.virdis.TweetParser

object DataChallenge {
  def main(args: Array[String]) {
    val t1 =
      """
        |{
        |    "created_at": "Fri Oct 30 15:29:45 +0000 2015",
        |    "id": 660116385083838500,
        |    "id_str": "660116385083838464",
        |    "text": "Have you got your #pumpkin ready for #Halloween Have Fun! #HappyHalloween #happyfriday https://t.co/UIo2LeYPoZ https://t.co/vamv9XSsH4",
        |    "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        |    "truncated": false,
        |    "in_reply_to_status_id": null,
        |    "in_reply_to_status_id_str": null,
        |    "in_reply_to_user_id": null,
        |    "in_reply_to_user_id_str": null,
        |    "in_reply_to_screen_name": null,
        |    "user": {
        |        "id": 334828432,
        |        "id_str": "334828432",
        |        "name": "Mark Pritchard",
        |        "screen_name": "markpritchardUK",
        |        "location": "Prague",
        |        "url": "http://www.astrafs.com",
        |        "description": "Helping Czech, European and American clients secure their financial future +420 776496414",
        |        "protected": false,
        |        "verified": false,
        |        "followers_count": 5265,
        |        "friends_count": 5773,
        |        "listed_count": 49,
        |        "favourites_count": 3,
        |        "statuses_count": 9804,
        |        "created_at": "Wed Jul 13 18:46:24 +0000 2011",
        |        "utc_offset": 0,
        |        "time_zone": "London",
        |        "geo_enabled": true,
        |        "lang": "en",
        |        "contributors_enabled": false,
        |        "is_translator": false,
        |        "profile_background_color": "07060D",
        |        "profile_background_image_url": "http://pbs.twimg.com/profile_background_images/378800000042431002/0a337bd8ddf3a6e8eae3e697fd23f88b.gif",
        |        "profile_background_image_url_https": "https://pbs.twimg.com/profile_background_images/378800000042431002/0a337bd8ddf3a6e8eae3e697fd23f88b.gif",
        |        "profile_background_tile": true,
        |        "profile_link_color": "6E0226",
        |        "profile_sidebar_border_color": "000000",
        |        "profile_sidebar_fill_color": "FE8D36",
        |        "profile_text_color": "14A8E0",
        |        "profile_use_background_image": false,
        |        "profile_image_url": "http://pbs.twimg.com/profile_images/378800000230001721/aa98f36619670505bcf9ba506883f39a_normal.jpeg",
        |        "profile_image_url_https": "https://pbs.twimg.com/profile_images/378800000230001721/aa98f36619670505bcf9ba506883f39a_normal.jpeg",
        |        "profile_banner_url": "https://pbs.twimg.com/profile_banners/334828432/1391615522",
        |        "default_profile": false,
        |        "default_profile_image": false,
        |        "following": null,
        |        "follow_request_sent": null,
        |        "notifications": null
        |    },
        |    "geo": null,
        |    "coordinates": null,
        |    "place": {
        |        "id": "0126ba1e341b038c",
        |        "url": "https://api.twitter.com/1.1/geo/id/0126ba1e341b038c.json",
        |        "place_type": "admin",
        |        "name": "Prague",
        |        "full_name": "Prague, Czech Republic",
        |        "country_code": "CZ",
        |        "country": "Česká republika",
        |        "bounding_box": {
        |            "type": "Polygon",
        |            "coordinates": [
        |                [
        |                    [
        |                        14.225243,
        |                        49.941904
        |                    ],
        |                    [
        |                        14.225243,
        |                        50.177256
        |                    ],
        |                    [
        |                        14.706508,
        |                        50.177256
        |                    ],
        |                    [
        |                        14.706508,
        |                        49.941904
        |                    ]
        |                ]
        |            ]
        |        },
        |        "attributes": {}
        |    },
        |    "contributors": null,
        |    "is_quote_status": false,
        |    "retweet_count": 0,
        |    "favorite_count": 0,
        |    "entities": {
        |        "hashtags": [
        |            {
        |                "text": "pumpkin",
        |                "indices": [
        |                    18,
        |                    26
        |                ]
        |            },
        |            {
        |                "text": "Halloween",
        |                "indices": [
        |                    37,
        |                    47
        |                ]
        |            },
        |            {
        |                "text": "HappyHalloween",
        |                "indices": [
        |                    58,
        |                    73
        |                ]
        |            },
        |            {
        |                "text": "happyfriday",
        |                "indices": [
        |                    74,
        |                    86
        |                ]
        |            }
        |        ],
        |        "urls": [
        |            {
        |                "url": "https://t.co/UIo2LeYPoZ",
        |                "expanded_url": "http://www.astrafs.com/",
        |                "display_url": "astrafs.com",
        |                "indices": [
        |                    87,
        |                    110
        |                ]
        |            }
        |        ],
        |        "user_mentions": [],
        |        "symbols": [],
        |        "media": [
        |            {
        |                "id": 660116383968198700,
        |                "id_str": "660116383968198656",
        |                "indices": [
        |                    111,
        |                    134
        |                ],
        |                "media_url": "http://pbs.twimg.com/media/CSk0WpDXAAAxQLH.jpg",
        |                "media_url_https": "https://pbs.twimg.com/media/CSk0WpDXAAAxQLH.jpg",
        |                "url": "https://t.co/vamv9XSsH4",
        |                "display_url": "pic.twitter.com/vamv9XSsH4",
        |                "expanded_url": "http://twitter.com/markpritchardUK/status/660116385083838464/photo/1",
        |                "type": "photo",
        |                "sizes": {
        |                    "small": {
        |                        "w": 340,
        |                        "h": 253,
        |                        "resize": "fit"
        |                    },
        |                    "thumb": {
        |                        "w": 150,
        |                        "h": 150,
        |                        "resize": "crop"
        |                    },
        |                    "large": {
        |                        "w": 403,
        |                        "h": 300,
        |                        "resize": "fit"
        |                    },
        |                    "medium": {
        |                        "w": 403,
        |                        "h": 300,
        |                        "resize": "fit"
        |                    }
        |                }
        |            }
        |        ]
        |    },
        |    "extended_entities": {
        |        "media": [
        |            {
        |                "id": 660116383968198700,
        |                "id_str": "660116383968198656",
        |                "indices": [
        |                    111,
        |                    134
        |                ],
        |                "media_url": "http://pbs.twimg.com/media/CSk0WpDXAAAxQLH.jpg",
        |                "media_url_https": "https://pbs.twimg.com/media/CSk0WpDXAAAxQLH.jpg",
        |                "url": "https://t.co/vamv9XSsH4",
        |                "display_url": "pic.twitter.com/vamv9XSsH4",
        |                "expanded_url": "http://twitter.com/markpritchardUK/status/660116385083838464/photo/1",
        |                "type": "photo",
        |                "sizes": {
        |                    "small": {
        |                        "w": 340,
        |                        "h": 253,
        |                        "resize": "fit"
        |                    },
        |                    "thumb": {
        |                        "w": 150,
        |                        "h": 150,
        |                        "resize": "crop"
        |                    },
        |                    "large": {
        |                        "w": 403,
        |                        "h": 300,
        |                        "resize": "fit"
        |                    },
        |                    "medium": {
        |                        "w": 403,
        |                        "h": 300,
        |                        "resize": "fit"
        |                    }
        |                }
        |            }
        |        ]
        |    },
        |    "favorited": false,
        |    "retweeted": false,
        |    "possibly_sensitive": false,
        |    "filter_level": "low",
        |    "lang": "en",
        |    "timestamp_ms": "1446218985230"
        |}
      """.stripMargin

    object tp extends TweetParser

    println(tp.secondFeatureFormat(t1))
  }
}

