package com.example.evam3.service

import com.example.evam3.entity.Characters
import com.example.evam3.entity.Scene
import com.example.evam3.repository.CharactersRepository
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun getDuration (filmId: Long?): Long?{
        try {
            val durationFilm = filmRepository.findById(filmId)
                ?: throw Exception("ID no existe film")
            return  durationFilm.duration
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun getSuma (filmId: Long?): Double?{
        try{
            var suma: Double = 0.0
            val sceneFilm = sceneRepository.findByFilmId(filmId)
            sceneFilm.map{scenes ->
                suma += scenes?.minutes!!
            }
            return suma
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun list ():List<Scene>{
        return sceneRepository.findAll()
    }

    fun save (scene: Scene): Scene {
        try{
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Scene no debe ser vacio")

            val durationTime = getDuration(scene.filmId)

            val newScene: Long = scene.minutes!!

            val tiempoActual = getSuma(scene.filmId)

            val totalTiempo= newScene + tiempoActual!!

            if(totalTiempo >= durationTime!!){
                throw Exception("No se puede agregar la escena, sobrepasó la duracion...")
            }

            return sceneRepository.save(scene)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(scene: Scene): Scene{
        try {
            sceneRepository.findById(scene.id)
                ?: throw Exception("ID no existe")

            return sceneRepository.save(scene)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}