package com.hibernate.library.service;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Book;
import com.hibernate.library.entities.Reader;
import com.hibernate.library.exception.BookException;
import lombok.Locked;

import java.util.List;
import java.util.UUID;

public interface LibraryService {
    public List<Book> getAllBooks();
    public List<Author> getAllAuthors();
    public List<Reader> getAllReaders();

//    public Optional<Reader> getReaderByBook();
//    public List<Book> getBooksByReader();
//    public List<Book> getBooksByAuthor();
//    public List<Author> getAuthorsByBook();

    public Book addBook(Book book);
    public Book modifyBook(Book book);
    public void deleteBook(Book book);

    public Author addAuthor(Author author);
    public Author modifyAuthor(Author author);
    public void deleteAuthor(Author author);

    public Reader addReader(Reader reader);
    public Reader modifyReader(Reader reader);
    public void deleteReader(Reader reader);


    public Book takeBook(UUID reader, UUID book) throws BookException;
    public Book returnBook(UUID reader, UUID book) throws BookException;


}
