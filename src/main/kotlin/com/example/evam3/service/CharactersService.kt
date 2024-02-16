package com.example.evam3.service

import com.example.evam3.entity.Characters
import com.example.evam3.entity.Scene
import com.example.evam3.repository.CharactersRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharactersService {
    @Autowired
    lateinit var charactersRepository: CharactersRepository

    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun getBudget (sceneId: Long?): Double?{
        try {
            val budgetScene = sceneRepository.findById(sceneId)
                ?: throw Exception("ID no existe scene")
           return  budgetScene.budget
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun getSuma (sceneId: Long?): Double?{
        try{
            var suma: Double = 0.0
            val charactersScene = charactersRepository.findBySceneId(sceneId)
            charactersScene.map{characters ->
                suma += characters?.cost!!
            }
            return suma
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun list ():List<Characters>{
        return charactersRepository.findAll()
    }

    fun save (characters: Characters): Characters {
        try{
            characters.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Character no debe ser vacio")

            val presupuesto = getBudget(characters.sceneId)
            val nuevoCaracter: Double = characters.cost!!
            val costoActual = getSuma(characters.sceneId)
            val totalCosto= nuevoCaracter + costoActual!!
            if(totalCosto >= presupuesto!!){
                 throw Exception("No se puede agregar el personaje, sobrepasó el presupuesto...")
            }
            return charactersRepository.save(characters)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(characters: Characters): Characters{
        try {
            charactersRepository.findById(characters.id)
                ?: throw Exception("ID no existe")

            return charactersRepository.save(characters)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = charactersRepository.findById(id)
                ?: throw Exception("ID no existe")
            charactersRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}