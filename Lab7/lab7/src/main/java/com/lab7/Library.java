package com.lab7;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private List<Book> books;
    public Library(){
        books=new ArrayList<>();
    }
    public void addBook(Book book){
        books.add(book);
    }
    public Optional<Book> findBookByISBN(String ISBN){
        return books.stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst();
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("1984", "George Orwell", "1234567890", Availability.AVAILABLE));
        library.addBook(new Book("The Trial", "Franz Kafka", "0987654321", Availability.CHEKED_OUT));
        Book exceptionHandlingBook = new Book("-", "-", "-", Availability.UNAVAILABLE);
        Book foundBook1=library.findBookByISBN("1234567890").orElse(exceptionHandlingBook);
        System.out.println("Found book (or exception): "+foundBook1);
        Book foundBook2=library.findBookByISBN("0987654321").orElseGet(()->
                new Book("Lazy", "Lazy Author", "Lazy ISBN", Availability.UNAVAILABLE));
        System.out.println("Found book (or exception): "+foundBook2);
        Book foundBook3 = library.findBookByISBN("0987654321").orElseThrow(()-> 
                new RuntimeException("Book not found"));
        System.out.println("Found book (or exception): " + foundBook3);
        Book foundBook4 = library.findBookByISBN("wlouoeg").orElseThrow(()-> 
                new RuntimeException("Book not found"));
        System.out.println("Found book (or exception): "+foundBook4);
}}
enum Availability{
    AVAILABLE, CHEKED_OUT, RESERVED, UNAVAILABLE
}
class Book {
    private String title;
    private String author;
    private String ISBN;
    private Availability status;
    public Book(String title, String author, String ISBN, Availability status){
        this.title=title;
        this.author=author;
        this.ISBN=ISBN;
        this.status=status;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getISBN(){
        return ISBN;
    }
    public Availability getStatus() {
        return status;
    }
    public void setStatus(Availability status) {
        this.status=status;
    }

    @Override
    public String toString() {
        return "Title: " +this.title+", Author: "+this.author+", ISBN: "+this.ISBN+", Status: " + this.status;
    }
}
