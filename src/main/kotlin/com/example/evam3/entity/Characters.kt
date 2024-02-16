package com.example.evam3.entity

import jakarta.persistence.*
import java.text.DecimalFormat

@Entity
@Table(name="characters")
class Characters {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var cost: Double? = null
    @Column(name = "name_char")
    var nameChar: String? = null
    @Column(name = "scene_id")
    var sceneId: Long? = null
}