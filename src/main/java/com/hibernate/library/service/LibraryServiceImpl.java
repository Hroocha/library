package com.hibernate.library.service;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Book;
import com.hibernate.library.entities.Reader;
import com.hibernate.library.exception.BookException;
import com.hibernate.library.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@NoArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private AuthorshipRepository authorshipRepository;


    @PostConstruct
    public void process() {
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
        Book currentBook =  getBook(book);
        if (currentBook.getReader() != null) {
            throw new BookException("книга находится у другого читателя");
        }
        currentBook.setReader(getReader(reader));
        bookRepository.save(currentBook);
        return currentBook;
    }

    @Override
    public Book returnBook(UUID reader, UUID book) throws BookException {
        Book currentBook = getBook(book);
        if (currentBook.getReader() == null) {
            throw new BookException("книга уже находится в библиотеке");
        }
        currentBook.setReader(null);
        return currentBook;
    }

    private Book getBook(UUID book) throws BookException {
        return bookRepository.findById(book).orElseThrow(() ->
                new BookException("книга не найдена"));
    }

    private Reader getReader(UUID reader) throws BookException {
        return readerRepository.findById(reader).orElseThrow(() ->
                new BookException("читатель не найден"));
    }
}
