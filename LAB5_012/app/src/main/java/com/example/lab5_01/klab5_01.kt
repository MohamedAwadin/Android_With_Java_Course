package com.example.lab5_01

import java.time.temporal.TemporalQuery

abstract class LibraryItem(val title: String , val isbn: String , val publication: String , val numberOfPages: Int){
    private var available: Boolean = true

    fun isAvailable(): Boolean = available

    fun markAsBorrowed(){
        available = false
    }

    fun markAsAvailable(){
        available = true
    }

    override fun toString(): String {
        return "$title (ISBN: $isbn, Publication: $publication, Pages: $numberOfPages, Available: $available)"
    }
}

class Book(title: String , isbn: String , publication: String , numberOfPages: Int) : LibraryItem(title, isbn , publication ,numberOfPages)
class Journal(title: String , isbn: String , publication: String , numberOfPages: Int) : LibraryItem(title, isbn , publication ,numberOfPages)
class Magazine(title: String , isbn: String , publication: String , numberOfPages: Int) : LibraryItem(title, isbn , publication ,numberOfPages)


abstract class Person(val name: String , val  id : Int){
    override fun toString(): String {
        return "$name (ID: $id)"
    }
}

class Librarian(name: String,id: Int,val password: String): Person(name , id)
class User(name: String,id: Int,val job: String): Person(name , id)

class LibraryDataBase{
    private val availableItems: MutableList<LibraryItem> = mutableListOf()
    private val borrowedItems: MutableMap<LibraryItem, User> = mutableMapOf()


    fun addBook(item: LibraryItem){
        availableItems.add(item)
        println("Added: $item")
    }

    fun lendBook(item: LibraryItem, user: User): Boolean{
        return if (item in  availableItems && item.isAvailable()){
            availableItems.remove(item)
            item.markAsBorrowed()
            borrowedItems[item]= user
            println("$user borrowed $item")
            true
        }else {
            println("Item $item is not available for lending ")
            false
        }
    }

    fun recieveBookFromBorrower(item: LibraryItem): Boolean{
        return if (item in borrowedItems.keys){
            val user = borrowedItems[item]
            borrowedItems.remove(item)
            item.markAsAvailable()
            availableItems.add(item)
            println("$user returned $item")
            true
        }else{
            println("Item $item was not borrowed")
            false
        }
    }

    fun viewAvailableBooks(){
        println("Available Items:")
        if (availableItems.isEmpty())
        {
            println("No Items available. ")
        }
        else{
            availableItems.forEach { println(it) }
        }
    }

    fun searchForABook(query: String): List<LibraryItem>{
        val allitems = availableItems + borrowedItems.keys
        val results = allitems.filter { it.title.contains(query) || it.isbn == query }
        println("Search results for '$query':")
        if (results.isEmpty()) {
            println("No items found")
        } else {
            results.forEach { println(it) }
        }
        return results
    }
}

fun main(){
    val library = LibraryDataBase()


    val book1 = Book("shams", "ISBN001", "Gen", 300)
    val book2 = Book("alf lila", "ISBN002", "khaial", 400)
    val journal = Journal("Science", "ISSN001", "SciencePub", 50)
    val magazine = Magazine("Tech Trends", "ISSN002", "TechMag", 60)
    val librarian = Librarian("Awadin", 1, "pass123")
    val user1 = User("Ahmed", 101, "Student")
    val user2 = User("Medo", 102, "Teacher")
    library.addBook(book1)
    library.addBook(book2)
    library.addBook(journal)
    library.addBook(magazine)
    library.viewAvailableBooks()
    library.searchForABook("shams")
    library.lendBook(book1, user1)
    library.lendBook(journal, user2)
    library.viewAvailableBooks()
    library.searchForABook("shams")
    library.recieveBookFromBorrower(book1)
    library.viewAvailableBooks()

}