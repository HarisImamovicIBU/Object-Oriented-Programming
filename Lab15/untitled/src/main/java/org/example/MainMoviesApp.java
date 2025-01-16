package org.example;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum TextsFormatter{
    ORDINARY, UPPER_CASE, LOWER_CASE
}
enum NumbersFormatter{
    CURRENCY, PERCENTAGE
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldFormat{
    TextsFormatter text_format() default TextsFormatter.ORDINARY;
    NumbersFormatter number_format() default NumbersFormatter.CURRENCY;
}
class Movie{
    @FieldFormat
    private Date releaseDate;
    @FieldFormat(text_format = TextsFormatter.ORDINARY)
    private String title;
    @FieldFormat(number_format = NumbersFormatter.CURRENCY)
    private double boxOffice;
    @FieldFormat(number_format = NumbersFormatter.PERCENTAGE)
    private double rating;
    @FieldFormat(text_format = TextsFormatter.UPPER_CASE)
    private String genre;
    @FieldFormat(text_format = TextsFormatter.UPPER_CASE)
    private String director;
    @FieldFormat(text_format = TextsFormatter.LOWER_CASE)
    private String language;
    private Movie(MovieBuilder builder){
        this.releaseDate=builder.releaseDate;
        this.title=builder.title;
        this.boxOffice=builder.boxOffice;
        this.rating=builder.rating;
        this.genre= builder.genre;
        this.director= builder.director;
        this.language=builder.language;
    }
    public static class MovieBuilder{
        @FieldFormat
        private Date releaseDate;
        @FieldFormat(text_format = TextsFormatter.ORDINARY)
        private String title;
        @FieldFormat(number_format = NumbersFormatter.CURRENCY)
        private double boxOffice;
        @FieldFormat(number_format = NumbersFormatter.PERCENTAGE)
        private double rating;
        @FieldFormat(text_format = TextsFormatter.UPPER_CASE)
        private String genre;
        @FieldFormat(text_format = TextsFormatter.UPPER_CASE)
        private String director;
        @FieldFormat(text_format = TextsFormatter.LOWER_CASE)
        private String language;
        public MovieBuilder(){}

        public MovieBuilder setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder setBoxOffice(double boxOffice) {
            this.boxOffice = boxOffice;
            return this;
        }

        public MovieBuilder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public MovieBuilder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public MovieBuilder setDirector(String director) {
            this.director = director;
            return this;
        }

        public MovieBuilder setLanguage(String language) {
            this.language = language;
            return this;
        }
        public Movie build(){
            return new Movie(this);
        }
    }
    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public double getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getLanguage() {
        return language;
    }
}
class InvalidMovieDataException extends RuntimeException{
    public InvalidMovieDataException(String message){
        super(message);
    }
    public InvalidMovieDataException(String message, Throwable cause){
        super(message, cause);
    }
}
class MovieLoader{
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MovieLoader(String fileName){
        try{
            this.movies=loadMovies(fileName);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public static List<Movie> loadMovies(String fileName) throws IOException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<Movie> readMovies = new ArrayList<>();
            List<String> lines = new ArrayList<>();
            String line;
            while((line=reader.readLine())!=null){
                lines.add(line);
            }
            for(int i=1;i<lines.size();i++){
                //Format in the file: RELEASE_DATE,TITLE,BOX_OFFICE,RATING,GENRE,DIRECTOR,LANGUAGE
                String[] parts = lines.get(i).split(",");
                if(parts[1].isEmpty() || parts[2].isEmpty() || parts[3].isEmpty()){
                    throw new InvalidMovieDataException("Invalid data.", new ClassCastException());
                }
                Movie movieToAdd = new Movie.MovieBuilder()
                        .setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(parts[0]))
                        .setTitle(parts[1])
                        .setBoxOffice(Integer.parseInt(parts[2]))
                        .setRating(Double.parseDouble(parts[3]))
                        .setGenre(parts[4])
                        .setDirector(parts[5])
                        .setLanguage(parts[6]).build();
                readMovies.add(movieToAdd);
            }
            reader.close();
            return readMovies;
        }

        catch (IOException | ParseException e){
            throw new IOException(e);
        }
    }
}
public class MainMoviesApp {
    public static void main(String[] args) {
        MovieLoader movieLoader = new MovieLoader("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab15\\untitled\\src\\main\\java\\org\\example\\movies.csv");
        MovieReportWriter movieReportWriter = new MovieReportWriter();
        movieReportWriter.generateReport("movieTask.csv", movieLoader.getMovies() );
    }
}
class MovieReportWriter{
    public static void generateReport(String outputFileName, List<Movie> movies){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            Class myClass = Movie.class;
            Field[] fields = myClass.getDeclaredFields();
            for(int i=0;i< fields.length;i++){
                writer.write(
                        fields.length-1==i ?
                                fields[i].getName().toUpperCase().concat("\n")
                                :
                                fields[i].getName().toUpperCase().concat(",")
                );
            }
            for(Movie movie : movies){
                for(int i=0;i< fields.length;i++){
                    fields[i].setAccessible(true);
                    if(fields[i].isAnnotationPresent(FieldFormat.class)){
                        if(fields[i].get(movie) instanceof String){
                            TextsFormatter tf = fields[i].getAnnotation(FieldFormat.class).text_format();
                            if(tf==TextsFormatter.LOWER_CASE){
                                writer.write(
                                        fields.length-1==i ?
                                                fields[i].get(movie).toString().toLowerCase().concat("\n")
                                                :
                                                fields[i].get(movie).toString().toLowerCase().concat(",")
                                );
                            }
                            else if(tf.equals(TextsFormatter.UPPER_CASE)){
                                writer.write(
                                        fields.length-1==i ?
                                                fields[i].get(movie).toString().toUpperCase().concat("\n")
                                                :
                                                fields[i].get(movie).toString().toUpperCase().concat(",")
                                );
                            }
                            else{
                                writer.write(
                                        fields.length-1==i ?
                                                fields[i].get(movie).toString().concat("\n")
                                                :
                                                fields[i].get(movie).toString().concat(",")
                                );
                            }
                        }
                        if(fields[i].get(movie) instanceof Date) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            writer.write(
                                    fields.length - 1 == i ?
                                            sdf.format(fields[i].get(movie)).concat("\n") :
                                            sdf.format(fields[i].get(movie)).concat(",")
                            );
                        }
                        if(fields[i].get(movie) instanceof Number){
                            NumbersFormatter nf = fields[i].getAnnotation(FieldFormat.class).number_format();
                            if(nf==NumbersFormatter.CURRENCY){
                                DecimalFormat df = new DecimalFormat("$#,###.##");
                                writer.write(
                                        fields.length-1==i ?
                                                df.format(fields[i].get(movie)).concat("\n")
                                                :
                                                df.format(fields[i].get(movie)).concat(",")
                                );
                            }
                            if(nf==NumbersFormatter.PERCENTAGE){
                                writer.write(
                                        fields.length-1==i ?
                                                "%".concat(fields[i].get(movie).toString().concat("\n"))
                                                :
                                                "%".concat(fields[i].get(movie).toString().concat(","))
                                );
                            }
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}