package com.sriraksha.artsgallery

val getArtDetailsSuccessResponse = """
            {"objectID":1,"isHighlight":false,"accessionNumber":"1979.486.1","accessionYear":"1979","isPublicDomain":false,"primaryImage":"","primaryImageSmall":"","additionalImages":[],"constituents":[{"constituentID":164292,"role":"Maker","name":"James Barton Longacre","constituentULAN_URL":"http://vocab.getty.edu/page/ulan/500011409","constituentWikidata_URL":"https://www.wikidata.org/wiki/Q3806459","gender":""}],"department":"The American Wing","objectName":"Coin","title":"One-dollar Liberty Head Coin","culture":"","period":"","dynasty":"","reign":"","portfolio":"","artistRole":"Maker","artistPrefix":"","artistDisplayName":"James Barton Longacre","artistDisplayBio":"American, Delaware County, Pennsylvania 1794–1869 Philadelphia, Pennsylvania","artistSuffix":"","artistAlphaSort":"Longacre, James Barton","artistNationality":"American","artistBeginDate":"1794","artistEndDate":"1869","artistGender":"","artistWikidata_URL":"https://www.wikidata.org/wiki/Q3806459","artistULAN_URL":"http://vocab.getty.edu/page/ulan/500011409","objectDate":"1853","objectBeginDate":1853,"objectEndDate":1853,"medium":"Gold","dimensions":"Dimensions unavailable","measurements":null,"creditLine":"Gift of Heinz L. Stoppelmann, 1979","geographyType":"","city":"","state":"","county":"","country":"","region":"","subregion":"","locale":"","locus":"","excavation":"","river":"","classification":"","rightsAndReproduction":"","linkResource":"","metadataDate":"2021-04-06T04:41:04.967Z","repository":"Metropolitan Museum of Art, New York, NY","objectURL":"https://www.metmuseum.org/art/collection/search/1","tags":null,"objectWikidata_URL":"","isTimelineWork":false,"GalleryNumber":""}
""".trimIndent()

val getArtListSuccessResponse = """
            {"total":484269,"objectIDs":[1,2,3,4,5,6,7,8,9,10,11,12,13,14]}
""".trimIndent()

val getSearchedArtListSuccessResponse = """
            {"total":89,"objectIDs":[436524,484935,437112,210191,431264,397949,656530,480725,486590,485308,375281,705155,11922,2032,343052,20141,347980,2019,208554,403496,360837,437115,207869,400581,423400,707887,223828,682927,57922,649813,79739,193938,339347,208218,437984,202228,436534,761604,437329,436252,363282,207753,822570,816522,487043,436580,437526,203893,367434,190739,423237,262423,839296,367817,822590,822589,13644,18362,820022,648617,761731,814626,892559,207967,626832,262430,262428,841928,707697,205386,262378,426804,633526,369073,747779,852414,427872,895564,436530,436535,436529,436121,724844,436144,437980,631808,834580,834585,834765]}
""".trimIndent()

val getArtDetailsFailureResponse = """
    {"message":"could not parse objectID"}
""".trimIndent()

val getSearchedArtListFailureResponse = """
    {"total":0,"objectIDs":null}
""".trimIndent()
