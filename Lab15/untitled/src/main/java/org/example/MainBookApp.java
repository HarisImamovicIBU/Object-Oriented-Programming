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

enum CaseFormatter{
    ORDINARY, UPPER_CASE, LOWER_CASE
}
enum NumberFormatter{
    COMMA, PERCENTAGE
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface WriteConcerns{
    CaseFormatter case_format() default CaseFormatter.ORDINARY;
    NumberFormatter number_format() default NumberFormatter.COMMA;
}
class Book{
    private Date date;
    @WriteConcerns(case_format = CaseFormatter.ORDINARY)
    private String quarter;
    private int qtr;
    private int year;
    @WriteConcerns(case_format = CaseFormatter.UPPER_CASE)
    private String customerId;
    @WriteConcerns(number_format = NumberFormatter.COMMA)
    private double totalAmount;
    @WriteConcerns(number_format = NumberFormatter.PERCENTAGE)
    private double profitPercentage;
    private double profitInr;
    private double costPrice;

    public Date getDate() {
        return date;
    }

    public String getQuarter() {
        return quarter;
    }

    public int getQtr() {
        return qtr;
    }

    public int getYear() {
        return year;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }

    public double getProfitInr() {
        return profitInr;
    }

    public double getCostPrice() {
        return costPrice;
    }

    private Book(BookBuilder builder){
        this.date=builder.date;
        this.quarter=builder.quarter;
        this.qtr=builder.qtr;
        this.year=builder.year;
        this.customerId=builder.customerId;
        this.totalAmount=builder.totalAmount;
        this.profitPercentage=builder.profitPercentage;
        this.profitInr=builder.profitInr;
        this.costPrice=builder.costPrice;
    }
    public static class BookBuilder{
        private Date date;
        @WriteConcerns(case_format = CaseFormatter.ORDINARY)
        private String quarter;
        private int qtr;
        private int year;
        @WriteConcerns(case_format = CaseFormatter.UPPER_CASE)
        private String customerId;
        @WriteConcerns(number_format = NumberFormatter.COMMA)
        private double totalAmount;
        @WriteConcerns(number_format = NumberFormatter.PERCENTAGE)
        private double profitPercentage;
        private double profitInr;
        private double costPrice;
        public BookBuilder() {

        }

        public BookBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public BookBuilder setQuarter(String quarter) {
            this.quarter = quarter;
            return this;
        }

        public BookBuilder setCostPrice(double costPrice) {
            this.costPrice = costPrice;
            return this;
        }

        public BookBuilder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public BookBuilder setProfitIncrease(double profitInr) {
            this.profitInr = profitInr;
            return this;
        }

        public BookBuilder setProfitPercentage(double profitPercentage) {
            this.profitPercentage = profitPercentage;
            return this;
        }

        public BookBuilder setQtr(int qtr) {
            this.qtr = qtr;
            return this;
        }

        public BookBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public BookBuilder setYear(int year) {
            this.year = year;
            return this;
        }
        public Book build() {
            return new Book(this);
    }
    }
}
class WrongFormatException extends RuntimeException{
    public WrongFormatException(String message){
        super(message);
    }
    public WrongFormatException(String message, Throwable cause){
        super(message, cause);
    }
}
class FinalPrep{
    private List<Book> myBooks;

    public List<Book> getMyBooks() {
        return myBooks;
    }

    public FinalPrep(String filename){
        try{
            this.myBooks=loadBooks(filename);
        }
        catch(ParseException | IOException e){
            e.printStackTrace();
        }
    }
    public static List<Book> loadBooks(String filename) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Book> books = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        String line;
        while((line=reader.readLine())!=null){
            lines.add(line);
        }
        for(int i=1;i<lines.size();i++){
            String[] parts = lines.get(i).split(";");
            if(parts[0].isEmpty() || parts[4].isEmpty() || parts[5].isEmpty()){
                throw new WrongFormatException("Values are emtpy", new ClassCastException());
            }
            Book bookToAdd = new Book.BookBuilder()
                    .setDate(new SimpleDateFormat("MM/dd/yyyy").parse(parts[0]))
                    .setQuarter(parts[1])
                    .setQtr(Integer.parseInt(parts[2]))
                    .setYear(Integer.parseInt(parts[3]))
                    .setCustomerId(parts[4])
                    .setTotalAmount(Double.parseDouble(parts[5]))
                    .setProfitPercentage(Double.parseDouble(parts[6]))
                    .setProfitIncrease(Double.parseDouble(parts[7]))
                    .setCostPrice(Double.parseDouble(parts[8]))
                    .build();
            books.add(bookToAdd);
        }
        reader.close();
        return books;
    }
}
class ReportWriter{
    public static void writeReport(String outputFilename, List<Book> books){
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(outputFilename)
            );

            Class clazz = Book.class;
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++){
                writer.write(fields.length - 1 == i ?
                        fields[i].getName().concat("\n") :
                        fields[i].getName().concat(";")
                );
            }

            for (Book book : books){
                for (int i = 0; i < fields.length; i++){
                    fields[i].setAccessible(true);
                    if(fields[i].isAnnotationPresent(WriteConcerns.class)){
                        if(fields[i].get(book) instanceof String){
                            CaseFormatter caseFormat = fields[i].getAnnotation(WriteConcerns.class).case_format();

                            switch (caseFormat) {
                                case LOWER_CASE -> writer.write(
                                        fields.length - 1 == i ?
                                                fields[i].get(book).toString().toLowerCase().concat("\n") :
                                                fields[i].get(book).toString().toLowerCase().concat(";")
                                );

                                case UPPER_CASE -> writer.write(
                                        fields.length - 1 == i ?
                                                fields[i].get(book).toString().toUpperCase().concat("\n") :
                                                fields[i].get(book).toString().toUpperCase().concat(";")
                                );

                                case ORDINARY -> writer.write(
                                        fields.length - 1 == i ?
                                                fields[i].get(book).toString().concat("\n") :
                                                fields[i].get(book).toString().concat(";")
                                );
                            }
                        } else {
                            NumberFormatter numberFormat = fields[i].getAnnotation(WriteConcerns.class).number_format();

                            if(numberFormat == NumberFormatter.COMMA){
                                DecimalFormat df = new DecimalFormat("#,###.##");
                                writer.write(
                                        fields.length - 1 == i ?
                                                df.format(fields[i].get(book)).concat("\n") :
                                                df.format(fields[i].get(book)).concat(";")
                                );
                            } else {
                                writer.write(
                                        fields.length - 1 == i ?
                                                "%".concat(fields[i].get(book).toString()).concat("\n") :
                                                "%".concat(fields[i].get(book).toString()).concat(";")
                                );
                            }
                        }
                    } else {
                        writer.write(
                                fields.length - 1 == i ?
                                        fields[i].get(book).toString().concat("\n") :
                                        fields[i].get(book).toString().concat(";")
                        );
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
public class MainBookApp {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        FinalPrep fp = new FinalPrep("C:\\xampp\\htdocs\\Object-Oriented-Programming\\Lab15\\untitled\\src\\main\\java\\org\\example\\mybooks.csv");
        ReportWriter.writeReport("tmp.csv", fp.getMyBooks());
    }
}