package com.example.evam3.entity

import jakarta.persistence.*
@Entity
@Table (name="film")
class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var title: String? = null
    var director: String? = null
    var duration: Long? = null
    @Column(name = "num_scene")
    var numScene: Long? = null
    var gender: String? = null
    var synopsis: String? = null
}