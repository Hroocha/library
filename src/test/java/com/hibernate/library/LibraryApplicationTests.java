package com.hibernate.library;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Authorship;
import com.hibernate.library.entities.Book;
import com.hibernate.library.entities.Reader;
import com.hibernate.library.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	BookRepositoryNotOptimized bookRepositoryNotOptimized;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	ReaderRepository readerRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	AuthorshipRepository authorshipRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void getAuthorsOfBooks(){
		List <Book> books = (List<Book>) bookRepositoryNotOptimized.findAll();
		books.forEach(book -> {
			System.out.println(book.getName());
			book.getAuthors().forEach(author -> {
				System.out.println(author.getName());
			});
			System.out.println();
		});
	} // при загрузке книг была n+1 (т.к. в book у поля reader небыло LAZY)
	  // но все еще есть n+1 при запросе авторов

	@Test
	@Transactional
	public void getAuthorsOfBooksOptimized(){
		List <Book> books = bookRepository.findAll();
		books.forEach(book -> {
			System.out.println(book.getName());
			book.getAuthors().forEach(author -> {
				System.out.println(author.getName());
			});
			System.out.println();
		});
	} // оптимизированно, использован EntityGraph
	@Test
	@Transactional
	public void getReadersOfEveryBook(){
		bookRepository.findAll()
				.forEach(book -> {
					System.out.println(book.getName());
					Reader reader = book.getReader();
					if (reader != null) {
						System.out.println(reader.getName());
					}
					System.out.println();
				});
	} // было n+1, оптимизированно, использован EntityGraph



	@Test
	@Transactional
	public void getBooksByAuthors(){
		List<Author> authors = (List<Author>) authorRepository.findAll();
		authors.forEach(author -> {
			System.out.println(author.getName());
			author.getBooks().forEach(book -> {
				System.out.println(book.getName());
			});
			System.out.println();
		});
	} // n+1

	@Test
	@Transactional
	public void getBooksByAuthorsOptimized(){
		List<Author> authors = authorRepository.findAllAuthorsWithBooks();
		authors.forEach(author -> {
			System.out.println(author.getName());
			author.getBooks().forEach(book -> {
				System.out.println(book.getName());
			});
			System.out.println();
		});
	} // оптимизированно, использован JOIN FETCH


	@Test
	@Transactional
	public void getBooksOfEveryReaderOptimized(){
		List<Reader> readers = readerRepository.findAll();
		readers.forEach(reader -> {
			List<Book> books = reader.getBooks();
			if (!books.isEmpty()) {
				System.out.println(reader.getName());
				books.forEach(book -> {
					System.out.println(book.getName());
				});
				System.out.println();
			}
		});
	} // оптимизировано, пакетная загрузка = 10, если ее убрать то на каждого читателя добавляется запрос
	@Test
	@Transactional
	public void getReaders(){
		readerRepository.findAll()
				.forEach(reader -> {
					System.out.println(reader.getName());
				});
	}// все ок, т.к. по умолчанию в сущность ничего не загружается и у oneToMany по умолчанию lazy

	@Test
	@Transactional
	public void getBooks(){
		List<Book> books1 = (List<Book>) bookRepositoryNotOptimized.findAll();
		books1.forEach(book -> {
			System.out.println(book.getName());});
	}// n+1 к каждому запросу где есть читатель если не ставить lazy (lazy поставлен)

}
