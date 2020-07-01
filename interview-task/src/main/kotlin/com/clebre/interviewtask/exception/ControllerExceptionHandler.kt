package com.clebre.interviewtask.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(NoteNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoteNotFoundException(ex: NoteNotFoundException) =
        ResponseStatusException(HttpStatus.NOT_FOUND, ex.message, ex)
}