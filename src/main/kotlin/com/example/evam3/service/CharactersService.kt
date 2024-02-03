package com.example.evam3.service

import com.example.evam3.entity.Characters
import com.example.evam3.repository.CharactersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharactersService {
    @Autowired
    lateinit var charactersRepository: CharactersRepository

    fun list ():List<Characters>{
        return charactersRepository.findAll()
    }

    fun save (characters: Characters): Characters {
        try{
            characters.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Film no debe ser vacio")
            return charactersRepository.save(characters)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }
}