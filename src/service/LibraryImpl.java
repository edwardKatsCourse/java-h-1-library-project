package service;

import model.Author;
import model.Book;
import model.Countries;
import model.Publisher;
import util.DBLib;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class LibraryImpl implements ILibrary {

    public static final TreeSet<Book> EMPTY_TREE_SET = new TreeSet<Book>();

    private HashMap<Long, Book> isbnHM;
    private TreeMap<Author, TreeSet<Book>> authorTM;
    private TreeMap<String, TreeSet<Book>> titleTM;
    private HashMap<Publisher, TreeSet<Book>> publisherHM;
    private TreeMap<String, TreeSet<Book>> publisherNameTM;
    private TreeMap<String, TreeSet<Book>> publisherCountryTM;
    private TreeMap<LocalDate, TreeSet<Book>> editionTM;
    private TreeMap<Double, TreeSet<Book>> priceTM;

    public LibraryImpl() {
        emptyLibrary();
    }

    private void emptyLibrary() {
        isbnHM = new HashMap<Long, Book>();
        authorTM = new TreeMap<Author, TreeSet<Book>>();
        titleTM = new TreeMap<String, TreeSet<Book>>();
        publisherHM = new HashMap<Publisher, TreeSet<Book>>();
        publisherNameTM = new TreeMap<String, TreeSet<Book>>();
        publisherCountryTM = new TreeMap<String, TreeSet<Book>>();
        editionTM = new TreeMap<LocalDate, TreeSet<Book>>();
        priceTM = new TreeMap<Double, TreeSet<Book>>();
    }

    @Override
    public boolean addBook(Book book) {
        if (book == null) return false;

        if (isbnHM.putIfAbsent(book.getISBN(), book) != null) {
            return false;
        }

        for (Author author : book.getAuthors()) DBLib.putToMultivalueMap(authorTM, author, book);
        DBLib.putToMultivalueMap(titleTM, book.getTitle(), book);
        DBLib.putToMultivalueMap(publisherHM, book.getPublisher(), book);
        DBLib.putToMultivalueMap(publisherNameTM, book.getPublisher().getName(), book);
        DBLib.putToMultivalueMap(publisherCountryTM, book.getPublisher().getCountry().name(), book);
        DBLib.putToMultivalueMap(editionTM, book.getEdition(), book);
        DBLib.putToMultivalueMap(priceTM, book.getPrice(), book);

        return true;
    }

    @Override
    public boolean addAll(Collection<Book> bCollection) {
        if (bCollection == null) {
            return false;
        }

        boolean returnValue = true;

        for (Book book : bCollection) {
            boolean addedToCollection = addBook(book);
            if (!addedToCollection) {
                returnValue = false;
            }
        }

        return returnValue;
    }

    @Override
    /**/ public boolean addLibary(LibraryImpl lib) {
        // TODOTODO Auto-generated method stub
        return false;
    }

    @Override
    public void fillRandomLibrary(int numBooks) {
        emptyLibrary();
        for (int counter = 0; counter < numBooks; counter++)
            addBook(Book.getRandomBook());

    }

    @Override
    public void fillWithIterable(Iterable<Book> iterable) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean contains(Book book) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<Book> bCollection) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean remove(Book book) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<Book> removeAll(Collection<Book> bCollection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> retainAll(Collection<Book> bCollection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getAllBooksSortedWithComparator(Comparator<Book> comparator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getAllBooksFilteredWithPredicate(Predicate<Book> predicate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean correctBookISBN(long isbn, long newISBN) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean correctBookAuthors(long isbn, Set<Author> newAuthors) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean correctBookTitle(long isbn, String newTitle) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean correctBookPublisher(long isbn, Publisher newPublisher) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean correctBookEditionDate(long isbn, LocalDate newEditionDate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean correctBookPrice(long isbn, double newPrice) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean correctBookWithPattern(long isbn, Book pattern) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Book getBookByISBN(long isbn) {
        return isbnHM.get(isbn);
    }

    @Override
    public Iterable<Book> getBooksByAuthor(Author author) {
        Iterable<Book> res = authorTM.get(author);
        return res == null ? EMPTY_TREE_SET : res;
    }

    @Override
    /**/ public Iterable<Book> getBooksByAllAuthors(Collection<Author> aCollection) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    /**/ public Iterable<Book> getBooksByTitle(String title) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**/ public Iterable<Book> getBooksByPublisher(Publisher publicher) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**/ public Iterable<Book> getBooksByPublisherName(String pName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**/ public Iterable<Book> getBooksByPublisherCountry(Countries pCountry) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getAllBooks() {
        TreeSet<Book> tsb = new TreeSet<Book>();
        tsb.addAll(isbnHM.values());
        return tsb;
    }

    @Override
    /**/ public Iterable<Book> getAllBooksSortedByAuthors() {
        List<Book> books = new ArrayList<>();
        //for each -> map

        return null;
    }

    @Override
    public Iterable<Book> getAllBooksSortedByTitle() {
        ArrayList<Book> alb = new ArrayList<>();
        for (TreeSet<Book> tsb : titleTM.values()) alb.addAll(tsb);
        return alb;
    }

    @Override
    /**/ public Iterable<Book> getAllBooksSortedByPublisherNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**/ public Iterable<Book> getAllBooksSortedByPublisherCountries() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> containsAtLastOne(Collection<Book> bCollection) {
        return null;
    }

    @Override
    public Iterable<Book> getBooksByAtLastOneAuthor(Collection<Author> aCollection) {
        return null;
    }

    @Override
    /**/ public Iterable<Book> getAllBooksSortedByEditionDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**/ public Iterable<Book> getAllBooksSortedByPrice() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksPrintedBefore(LocalDate max) {
        Set<Book> booksToReturn = new HashSet<>();
//        Set<Map.Entry> booksToReturn = new HashSet<>();

        //Entry -> Set<Entry> -> entry.getkey, getValue
        //for (Тип переменная : коллекция)

//        for (Book b : booksToReturn) {
//
//        }


        for (Map.Entry<LocalDate, TreeSet<Book>> map : editionTM.entrySet()) {

            if (map.getKey().isBefore(max)) {
                booksToReturn.addAll(map.getValue());
            }
        }
        return booksToReturn;
    }

    @Override
    public Iterable<Book> getBooksPrintedBefore(int year) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksPrintedAfter(LocalDate min) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksPrintedAfter(int year) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksPrintedInRange(LocalDate min, LocalDate max) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksPrintedInRange(int yearMin, int yearMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksCheaperThan(double maxPrice) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksMoreExpensiveThan(double minPrice) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Book> getBooksPricedInRange(double minPrice, double maxPrice) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        return isbnHM.size();
    }

    @Override
    public boolean isEmpty() {
        return isbnHM.isEmpty();
    }

    @Override
    public void clear() {
        emptyLibrary();
    }

}
