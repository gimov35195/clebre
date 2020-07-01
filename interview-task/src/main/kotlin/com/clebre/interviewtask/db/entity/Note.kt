package com.clebre.interviewtask.db.entity

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import kotlin.random.Random

@Table
data class Note(
    val content: String
) {

    @PrimaryKey
    val id: Long = Random.nextLong()
}