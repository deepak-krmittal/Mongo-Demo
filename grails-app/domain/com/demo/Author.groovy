package com.demo

import org.bson.types.ObjectId

class Author {

    ObjectId id
    String name
    Address address
    List<Book> books

    static mapWith = "mongo"

    static embedded = ['books', 'address']

}
