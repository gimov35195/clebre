package com.clebre.interviewtask.controller

import com.clebre.interviewtask.db.entity.Note
import com.clebre.interviewtask.db.repository.NoteRepository
import com.clebre.interviewtask.exception.NoteNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
class NoteController(
    private val noteRepository: NoteRepository
) {
    @GetMapping("/notes")
    fun getNotes() = noteRepository.findAll().toList()

    @PostMapping("/notes")
    fun createNote(@RequestBody note: Note) = noteRepository.save(note)

    @DeleteMapping("/notes/{id}")
    fun deleteNote(@PathVariable id: Long) = noteRepository.deleteById(id)

    @GetMapping("/notes/{id}")
    fun getNote(@PathVariable id: Long) =
        noteRepository.findByIdOrNull(id) ?: throw NoteNotFoundException("cannot find note with id [$id]")
}