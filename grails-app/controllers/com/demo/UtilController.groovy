package com.demo

class UtilController {

    def mongo

    def authors() {
        List<Author> authorList = Author.list()
        render(authorList*.name)
        render("<br/>")
        render("Count :: " + Author.count)
    }

    def searchAuthor(String q) {
        List<Author> authorList = Author.findAllByNameIlike("%${q}%")
        render(authorList*.name)
    }

    def books() {
        Author author = Author.first()
        List<Book> books = author.books
        render("Author id :: " + author?.id)
        render("<br/>")
        render(books*.title+"---"+books*.id)
        render("<br/>")
        render("Count :: " + Book.count)
    }

    def searchBook(String q) {
        List<Book> bookList = Book.findAllByTitleIlike("%${q}%")
        render(bookList*.title + "--" + bookList*.id)
    }

    def projections(Integer offset, Integer max, String sort, String order) {
        List<String> books = Book.createCriteria().list(offset: offset ?: 0, max: max ?: 10, sort: sort ?: "publishDate", order: order ?: "desc") {
            projections {
                property('title')
            }
            gte('price', 10l)
        }
        render(books)
    }

    def updateAuthor() {
        Author author = Author.first()
        author.name = "New author name"
        author.save(flush: true)
        render("Author id :: " + author?.id)
        render("<br/>")
        List<Author> authorList = Author.list()
        render(authorList*.name)
    }

    def addBook() {
        Author author = Author.first()
        Book book = new Book(title: "Demo book added", price: 10, publishDate: new Date())
        book.save(flush: true, failOnError: true)
        author.books.add(book)
        author.save(flush: true, failOnError: true)
        render("Author id :: " + author?.id)
        render("<br/>")
        render(author.books*.title)
    }

    def updateBook() {
        Author author = Author.first()
        render( "Author id :: " + author?.id)
        render("<br/>")
        Book book = author.books.first()
        book.title = "Updated book title"
        book.save(flush: true)
        author.save(flush: true)
        render("Book id :: " + book.id)
        render("<br/>")
        render("<br/>")
        render("DONE")
    }

    def select() {
        def db = mongo.getDB("mongo_demo")
        render(db.author.find()*.name)
        render("<br />")
        render("<br />")
        render("Count "+Author.collection.count())
        render("<br />")
        render("<br />")
        render("FindOne "+Author.collection.findOne())
    }

    def insert() {
        def db = mongo.getDB("mongo_demo")
        db.author.insert([name: "Pulkit"])
        render(db.author.find()*.name)
    }

    def update() {
        def db = mongo.getDB("mongo_demo")
        db.author.update([name: 'Pulkit'],[name: 'Pulkit Pushkarna'])
        render(db.author.find()*.name)
    }
}
