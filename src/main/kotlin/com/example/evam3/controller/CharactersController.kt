package com.example.evam3.controller

import com.example.evam3.entity.Characters
import com.example.evam3.service.CharactersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
class CharactersController {
    @Autowired
    lateinit var charactersService: CharactersService

    @GetMapping
    fun list (): ResponseEntity<*> {
        return ResponseEntity(charactersService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody characters: Characters): ResponseEntity<*> {
        return ResponseEntity<Characters>(charactersService.save(characters), HttpStatus.CREATED)
    }
}