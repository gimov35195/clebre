package com.clebre.interviewtask.db.repository

import com.clebre.interviewtask.db.entity.Note
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : CassandraRepository<Note, Long>