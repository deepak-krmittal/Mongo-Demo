package com.demo

import grails.transaction.Transactional

@Transactional
class PopulateDataService {

    void populateAuthors() {
        (1..10).each {
            Address address = populateAddress(it)
            List<Book> books = populateBooks(it)
            Author author = new Author(name: "Deepak ${it}", address: address, books: books)
            author.save(flush: true, failOnError: true)
        }
    }

    List<Book> populateBooks(Integer index) {
        List<Book> books = []
        (1..5).each {
            Book book = new Book(title: "Mongo ${index} ${it}", price: 10, publishDate: new Date())
            book.save(flush: true, failOnError: true)
            books.add(book)
        }
        return books
    }

    Address populateAddress(Integer index) {
        Address address = new Address(localAddress: "Test ${index}", city: 'Delhi', country: 'India')
        address.save(flush: true, failOnError: true)
    }
}
