grails {
    profile = 'web'
    codegen {
        defaultPackage = 'net.zomis.duga'
    }
}

info {
    app {
        name = '@info.app.name@'
        version = '@info.app.version@'
        grailsVersion = '@info.app.grailsVersion@'
    }
}

spring {
    groovy {
        template['check-template-location'] = false
    }
}

hibernate {
    naming_strategy = 'org.hibernate.cfg.DefaultNamingStrategy'
    cache {
        use_second_level_cache = false
        queries = false
    }
}

grails {
    mime {
        disable {
            accept {
                header {
                    userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
                }
            }
        }

        types {
            all = '*/*'
            atom = 'application/atom+xml'
            css = 'text/css'
            csv = 'text/csv'
            form = 'application/x-www-form-urlencoded'
            html = ['text/html', 'application/xhtml+xml']
            js = 'text/javascript'
            json = ['application/json', 'text/json']
            multipartForm = 'multipart/form-data'
            rss = 'application/rss+xml'
            text = 'text/plain'
            hal = ['application/hal+json', 'application/hal+xml']
            xml = ['text/xml', 'application/xml']
        }
    }
    urlmapping {
        cache {
            maxsize = 1000
        }
    }
    controllers {
        defaultScope = 'singleton'
    }
    converters {
        encoding = 'UTF-8'
    }
    views {
        'default' { codec = 'html' }
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml'
            codecs {
                expression = 'html'
                scriptlets = 'html'
                taglib = 'none'
                staticparts = 'none'
            }
        }
    }
}

dataSource {
    pooled = true
    jmxExport = true
    driverClassName = 'org.postgresql.Driver'
    dbCreate = ''
    username = 'railer'
    password = 'broom'
}

environments {
    development {
        dataSource {
            dbCreate 'update'
            url = 'jdbc:postgresql://localhost:5432/grails'
        }
    }
}