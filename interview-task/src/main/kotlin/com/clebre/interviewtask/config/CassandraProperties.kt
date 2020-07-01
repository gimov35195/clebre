package com.clebre.interviewtask.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cassandra")
class CassandraProperties {
    lateinit var keyspaceName: String
    lateinit var contactPoints: String
    lateinit var port: String
    lateinit var schemaAction: String
}

