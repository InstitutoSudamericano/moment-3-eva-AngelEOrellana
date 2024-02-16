package com.example.evam3.controller

import com.example.evam3.entity.Characters
import com.example.evam3.service.CharactersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
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

    @PutMapping
    fun update (@RequestBody characters:Characters):ResponseEntity<Characters>{
        return ResponseEntity(charactersService.update(characters), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return charactersService.delete(id)
    }



}