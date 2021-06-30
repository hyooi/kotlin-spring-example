package com.example.kotlinspringexample.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String?= null,//기본값이 있는 경우 생략할 수 있도록 마지막에 정의됨
    @Id @GeneratedValue var id: Long?= null)

// jpa는 data class와 호환이되지 않음. spring data jdbc나 spring data mongodb를 쓰는 경우 data class사용 가능