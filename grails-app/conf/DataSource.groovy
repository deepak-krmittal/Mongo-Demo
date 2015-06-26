//Configuring MongoDB
grails {
    mongo {
        dbCreate = "update"
        host = "127.0.0.1"
        port = 27017
        options {
            autoConnectRetry = true
            connectTimeout = 3000
            connectionsPerHost = 40
            socketTimeout = 60000
            threadsAllowedToBlockForConnectionMultiplier = 5
            maxAutoConnectRetryTime = 5
            maxWaitTime = 120000
        }
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        }
        grails {
            mongo {
                username = ""
                password = ""
                databaseName = "mongo_demo"
            }
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        }
        grails {
            mongo {
                username = "root"
                password = "igdefault"
                databaseName = "mongo_demo"
            }
        }
    }
    production {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        }
        grails {
            mongo {
                username = ""
                password = ""
                databaseName = "mongo_demo"
            }
        }
    }
}
