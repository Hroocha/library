package com.hibernate.library.service;

import com.hibernate.library.LibraryApplication;
import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Book;
import com.hibernate.library.entities.History;
import com.hibernate.library.entities.Reader;
import com.hibernate.library.exception.BookException;
import com.hibernate.library.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@NoArgsConstructor
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private AuthorshipRepository authorshipRepository;
    @Autowired
    private HistoryRepository historyRepository;


    @PostConstruct
    public void process(){
        addReader(new Reader("Волков И.П.", 33333));
        addReader(new Reader("Дугина В.А.", 44444));
        addReader(new Reader("Аюпов В.И.", 666666));
        addReader(new Reader("Лукина М.П.", 88888));
        addReader(new Reader("Хорин А.А.", 22222));
        addReader(new Reader("Родин С.Г.", 99999));
        addReader(new Reader("Малышев П.С.", 11111));
        addReader(new Reader("Щепин В.П.", 55555));
        addReader(new Reader("Тюрина Ю.И.", 77777));
        addReader(new Reader("Ерохина О.Р.", 10101));
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public List<Reader> getAllReaders() {
        return (List<Reader>) readerRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book modifyBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author modifyAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Author author) {
       authorRepository.delete(author);
    }

    @Override
    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader modifyReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteReader(Reader reader) {
        readerRepository.delete(reader);
    }

    @Override
    public Book takeBook(UUID reader, UUID book) throws BookException {
        Book currentBook = checkBookAndReader(reader,book);
        if(currentBook.getReaderId() != null){
            throw new BookException("книга находится у другого читателя");
        }
        currentBook.setReaderId(reader);
        bookRepository.save(currentBook);
        historyRepository.save(new History(currentBook.getId(),reader, LocalDateTime.now()));
        return currentBook;
    }

    @Override
    public Book returnBook(UUID reader, UUID book) throws BookException {
        Book currentBook = checkBookAndReader(reader,book);
        if(currentBook.getReaderId() == null){
            throw new BookException("книга уже находится в библиотеке");
        }
        currentBook.setReaderId(null);
        historyRepository.findHistoryByReturnDateIsNullAndReaderIdAndBookId
                (reader, book).ifPresent(history -> history.setReturnDate(LocalDateTime.now()));
        //а если итория не найдена надо подумать как обработать
        return currentBook;
    }

    private Book checkBookAndReader(UUID reader, UUID book) throws BookException {
        Book currentBook = bookRepository.findById(book).orElseThrow(()->
                new BookException("книга не найдена"));
        Reader curReader = readerRepository.findById(reader).orElseThrow(() ->
                new BookException("читатель не найден"));
        return currentBook;
    }
}
