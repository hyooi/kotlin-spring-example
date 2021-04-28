package com.example.kotlinspringexample.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String?= null,
    @Id @GeneratedValue r id: Long?= null)