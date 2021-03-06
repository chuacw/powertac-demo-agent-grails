// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
    xml: ['text/xml', 'application/xml'],
    text: 'text/plain',
    js: 'text/javascript',
    rss: 'application/rss+xml',
    atom: 'application/atom+xml',
    css: 'text/css',
    csv: 'text/csv',
    all: '*/*',
    json: ['application/json', 'text/json'],
    form: 'application/x-www-form-urlencoded',
    multipartForm: 'multipart/form-data'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true

grails.views.javascript.library='jquery'

// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// set per-environment serverURL stem for creating absolute links
environments {
  production {
    grails.serverURL = "http://www.powertac.org"

  /* Local server - it may happen that you just want to test your broker without a running server. ActiveMQ
         tries to connect to the server on startup and blocks the app unless it is connected.
          startupMaxReconnectAttempts=1 reduces the reconnection attempts at startup
  */
    //powertac.server = 'http://wolf-08.fbk.eur.nl:8080/powertac-server/'
    powertac.server = 'http://xlarge.rsm.nl:8080/powertac-server/'
  }
  development {
    grails.serverURL = "http://localhost:8080/${appName}"
    powertac.server = 'http://localhost:8080/powertac-server/'
  }
  test {
    grails.serverURL = "http://localhost:8080/${appName}"
    powertac.server = 'http://localhost:8080/powertac-server/'
  }

}

// log4j configuration
log4j = {
  appenders {
    console name: 'stdout',
        layout: pattern(conversionPattern: "%d [%t] %-5p %c %x - %m%n"),
        threshold: org.apache.log4j.Level.WARN
    file name: 'file', file: 'logs/powertac-broker.log',
        layout: pattern(conversionPattern: "%d [%t] %-5p %c{2} %x - %m%n"),
        threshold: org.apache.log4j.Level.DEBUG,
        append: false
    file name: 'incoming_message', file: 'logs/powertac-incoming-messages.log',
        threshold: org.apache.log4j.Level.DEBUG,
        append: false
  }

  error 'org.codehaus',
      'org.springframework',
      'org.hibernate',
      'org.activemq',
      'net.sf.ehcache'

  debug 'grails.app.service'
  debug 'grails.app.controller'
  debug 'grails.app.task'
  debug 'org.powertac'

  debug 'incoming_message' : 'org.powertac.broker.infrastructure.messaging.XMLMessageReceiver'

  root {
    warn 'stdout', 'file'
  }
}

// Added by the Joda-Time plugin:
grails.gorm.default.mapping = {
  "user-type" type: org.joda.time.contrib.hibernate.PersistentDateTime, class: org.joda.time.DateTime
  "user-type" type: org.joda.time.contrib.hibernate.PersistentDuration, class: org.joda.time.Duration
  "user-type" type: org.joda.time.contrib.hibernate.PersistentInstant, class: org.joda.time.Instant
  "user-type" type: org.joda.time.contrib.hibernate.PersistentInterval, class: org.joda.time.Interval
  "user-type" type: org.joda.time.contrib.hibernate.PersistentLocalDate, class: org.joda.time.LocalDate
  "user-type" type: org.joda.time.contrib.hibernate.PersistentLocalTimeAsString, class: org.joda.time.LocalTime
  "user-type" type: org.joda.time.contrib.hibernate.PersistentLocalDateTime, class: org.joda.time.LocalDateTime
  "user-type" type: org.joda.time.contrib.hibernate.PersistentPeriod, class: org.joda.time.Period
}

powertac {
  /* Broker login configuration */
  username = 'grailsDemo'
  apiKey = '5d064dd7-1ec9-4a98-bece-2ca09b03e367'

  brokerUrlOpts = '?timeout=3000&jms.redeliveryPolicy.maximumRedeliveries=0'

  broker.login.mode = 'auto'
}

// Added by the powertac-common plugin:
grails.validateable.packages = ['org.powertac.common.command']
