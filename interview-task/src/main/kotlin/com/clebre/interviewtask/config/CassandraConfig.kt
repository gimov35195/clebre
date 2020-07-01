package com.clebre.interviewtask.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories


@Configuration
@EnableCassandraRepositories("com.clebre.interviewtask.db.repository.NoteRepository")
class CassandraConfig(
    private val cassandraProperties: CassandraProperties
) : AbstractCassandraConfiguration() {

    override fun getKeyspaceName() = cassandraProperties.keyspaceName

    override fun getContactPoints() = cassandraProperties.contactPoints

    override fun getPort() = cassandraProperties.port.toInt()

    override fun getSchemaAction() = SchemaAction.valueOf(cassandraProperties.schemaAction)
}