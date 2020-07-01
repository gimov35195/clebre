package com.clebre.interviewtask.controller

import com.clebre.interviewtask.db.entity.Note
import com.clebre.interviewtask.db.repository.NoteRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(NoteController::class)
class NoteControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var noteRepository: NoteRepository

    @Test
    fun `should delete note`() {
        // given
        val noteId = 111L
        doNothing().`when`(noteRepository).deleteById(noteId)

        // when
        // then
        mockMvc
            .delete("/notes/$noteId")
            .andExpect {
                status { isOk }
            }
    }

    @Test
    fun `should create note`() {
        // given
        val note = Note("do some workout")
        `when`(noteRepository.save(note)).thenReturn(note)

        // when
        // then
        mockMvc
            .post("/notes") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(note)
            }
            .andExpect {
                status { isOk }
                content { json(objectMapper.writeValueAsString(note)) }
            }
    }

    @Test
    fun `should return 404 on missing note`() {
        // given
        val noteId = 1L
        `when`(noteRepository.findById(noteId)).thenReturn(Optional.empty())

        // when
        // then
        mockMvc.get("/notes/$noteId")
            .andExpect { status { isNotFound } }
    }

    @Test
    fun `should return note`() {
        // given
        val noteId = 10L
        val note = Note("play some games")
        `when`(noteRepository.findById(noteId)).thenReturn(Optional.of(note))

        // when
        // then
        mockMvc
            .get("/notes/$noteId")
            .andExpect {
                status { isOk }
                content { json(objectMapper.writeValueAsString(note)) }
            }
    }

    @Test
    fun `should return all notes`() {
        // given
        val notes = listOf(Note("buy some toys"), Note("learn Kotlin"))
        `when`(noteRepository.findAll()).thenReturn(notes)

        // when
        // then
        mockMvc
            .get("/notes")
            .andExpect {
                status { isOk }
                content { json(objectMapper.writeValueAsString(notes)) }
            }
    }
}