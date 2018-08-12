import model.Author;
import model.Book;
import service.ILibrary;
import service.LibraryImpl;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static ILibrary library = new LibraryImpl();

    public static void main(String[] args) {
//        int a = 10;
//        int b = 20;
//
//        boolean result = a == b;

//        Book a = new Book(0L, null, "", null, null, 1.0);
//        Book b = new Book(0L, new HashSet<>(), "", null, null, 1.0);

        //int, double, long - PRIMITIVES        ==
        //String, Integer, Person, Book         ==

//        boolean result = a == b;
        //машина_1.открытьБардачок().достатьСинююПапку().открытьСинююПапку().достатьДокумент()
        //машина_2.открытьБардачок().достатьСинююПапку().открытьСинююПапку().достатьДокумент()
        //машина_1 == машина_2 = одинаковое ли парковочное место (B-43)
        //машина_1.equals(машина_2)

        //DATA ACCESS OBJECT
        //Person -> to Data Base

        //Person, PersonDao -> сохраняет в базу данных -> PersonRepository

        Book book = Book.getRandomBook();
        library.addBook(book);

//        addAllTest();

        System.out.println("ISBN");
        Long isbn = new Scanner(System.in).nextLong();

        //John Doe, Jack Smith, Susan Collins
        System.out.println("Authors (separated by comma)");
        String authors = new Scanner(System.in).nextLine();
        String [] authorArray = authors.split(", "); //[{John Doe} {Jack Smith} {Susan Collins}]
        Set<Author> authorSet = new HashSet<>();
        for (String author : authorArray) { //{John Doe}
            String [] name = author.split(" ");
            authorSet.add(new Author(name[0], name[1]));
        }
        System.out.println("Title");
        String title = new Scanner(System.in).nextLine();



        library.addBook(new Book(isbn, authorSet, title, null, null, 0.00));
        System.out.println(library.getAllBooks());

    }

    private static void addAllTest() {

        Set<Book> books = new HashSet<>();
        for (int i = 0; i < 200; i++) {
            books.add(Book.getRandomBook());
        }
        boolean точноВсеЗашли = library.addAll(books);
        System.out.println("точноВсеЗашли: " + (точноВсеЗашли ? "ДА" : "НЕТ"));
    }

}
