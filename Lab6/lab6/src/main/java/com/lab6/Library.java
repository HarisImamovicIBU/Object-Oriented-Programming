package com.lab6;
import java.util.ArrayList;
import java.util.List;
class StringUtils{
    public static boolean included(String word, String searched){
        if(word.toUpperCase().contains(searched.trim().toUpperCase())){
            return true;
        }
        return false;
    }
}
public class Library {
    private List<Book> books;
    public Library() {
        this.books=new ArrayList<>();
    }
    public void addBook(Book newBook){
        this.books.add(newBook);
    }
    public void printBooks(){
        for(Book book : this.books){
            System.out.println(book);
        }
    }
    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> filteredByTitle = new ArrayList<>();
        for(Book book : this.books){
            if(StringUtils.included(book.getBookTitle(), title)){
                filteredByTitle.add(book);
            }
        }
        return filteredByTitle;
    }
    public ArrayList<Book> searchByPublisher(String publisher){
        ArrayList<Book> filteredByPublisher = new ArrayList<>();
        for(Book book : this.books){
            if(StringUtils.included(book.getPublisherName(), publisher)){
                filteredByPublisher.add(book);
            }
        }
        return filteredByPublisher;
    }
    public ArrayList<Book> searchByYear(int year){
        ArrayList<Book> filteredByYear = new ArrayList<>();
        for(Book book : this.books){
            if(year==book.getPublishingYear()){
                filteredByYear.add(book);
            }
        }
        return filteredByYear;
    }
    public static void main(String[] args) {
        Library Library = new Library();


        Library.addBook(new Book("Cheese Problems Solved", "Woodhead Publishing", 2007));
        Library.addBook(new Book("The Stinky Cheese Man and Other Fairly Stupid Tales", "Penguin Group", 1992));
        Library.addBook(new Book("NHL Hockey", "Stanley Kupp", 1952));
        Library.addBook(new Book("Battle Axes", "Tom A. Hawk", 1851));


        ArrayList<Book> result = Library.searchByTitle("Cheese");
        for (Book book: result) {
            System.out.println(book);
        }


        System.out.println("---");
        for (Book book: Library.searchByPublisher("Penguin Group  ")) {
            System.out.println(book);
        }


        System.out.println("---");
        for (Book book: Library.searchByYear(1851)) {
            System.out.println(book);
        }


        System.out.println("NEW SEARCH: \n\n");

        for (Book book: Library.searchByTitle("CHEESE")) {
            System.out.println(book);
         }
         
         
         System.out.println("---");
         for (Book book: Library.searchByPublisher("PENGUIN  ")) {
            System.out.println(book);
         }
    }
}
class Book{
    private String bookTitle;
    private String publisherName;
    private int publishingYear;
    public Book(String bookTitle, String publisherName, int publishingYear) {
        this.bookTitle = bookTitle;
        this.publisherName = publisherName;
        this.publishingYear = publishingYear;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getPublisherName() {
        return publisherName;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    public int getPublishingYear() {
        return publishingYear;
    }
    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }
    @Override
    public String toString(){
        return this.bookTitle+" "+this.publisherName+" "+this.publishingYear;
    }
}