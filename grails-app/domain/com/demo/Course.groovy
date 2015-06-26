package com.demo

import org.bson.types.ObjectId

class Course {

    ObjectId id
    String title

    static mapWith = "mongo"

    static hasMany = [students: Student]

    static belongsTo = Student
}
