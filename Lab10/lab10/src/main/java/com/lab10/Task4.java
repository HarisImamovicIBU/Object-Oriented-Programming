package com.lab10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


class Task4 {
    public static void main(String[] args) {
        List<Song> playlist = new ArrayList<>();
        Song song1 = new Song("The Weeknd", "Pop", "Call Out My Name");
        Song song2 = new Song("Safet Isović", "Sevdalinka", "Kiša bi pala");
        Song song3 = new Song("Frank Sinatra", "Jazz", "Fly Me To The Moon");
        playlist.add(song1);
        playlist.add(song2);
        playlist.add(song3);
        String targetGenre = "Pop";
        GenreFilterIterator popIterator = new GenreFilterIterator(playlist, targetGenre);
        while (popIterator.hasNext()){
            System.out.println(popIterator.next());
        }
    }      
}
class Song{
    private String title;
    private String artist;
    private String genre;
    public Song(String artist, String genre, String title) {
        this.artist = artist;
        this.genre = genre;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString(){
        return "Title: "+this.title+", Author: "+this.artist+", Genre: "+this.genre;
    }
}
class GenreFilterIterator implements Iterator<Song>{
    public List<Song> playlist;
    public String targetGenre;
    private int currentIndex=0;
    public GenreFilterIterator(List<Song> playlist, String targetGenre){
        this.playlist=playlist;
        this.targetGenre=targetGenre;
    }
    public List<Song> getPlaylist() {
        return playlist;
    }
    public void setPlaylist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }
    public String getTargetGenre() {
        return targetGenre;
    }
    public void setTargetGenre(String targetGenre) {
        this.targetGenre = targetGenre;
    }
    @Override
    public boolean hasNext(){
        while(currentIndex<this.playlist.size()){
            if(this.playlist.get(currentIndex).getGenre().toLowerCase().equals(targetGenre.toLowerCase())){
                return true;
            }
            currentIndex++;
        }
        return false;
    }
    @Override
    public Song next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        return this.playlist.get(currentIndex++);
    }
}