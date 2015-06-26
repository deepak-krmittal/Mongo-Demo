package com.demo

import org.bson.types.ObjectId

class Student {

    ObjectId id
    String name

    static mapWith = "mongo"

    static hasMany = [courses: Course]

}
