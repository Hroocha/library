package com.hibernate.library;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Book;
import com.hibernate.library.entities.Reader;
import com.hibernate.library.repositories.AuthorRepository;
import com.hibernate.library.repositories.AuthorshipRepository;
import com.hibernate.library.repositories.BookRepository;
import com.hibernate.library.repositories.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class LibraryApplicationTests {

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
		List <Book> books = (List<Book>) bookRepository.findAll();
		books.forEach(book -> {
			System.out.println(book.getName());
			book.getAuthors().forEach(author -> {
				System.out.println(author.getName());
			});
			System.out.println();
		});
	} // при загрузке книг была n+1 (т.к. в book у поля reader небыло LAZY)
	  // n+1 при запросе авторов

//	public void getAuthorsOfBooksOptimization(){
//		authorshipRepository.findAllAuthorsByBooks().forEach(authorship -> {
//			System.out.println(authorship.);
//		});
//	}

	@Test
	@Transactional
	public void getBooks(){
		List<Book> books1 = (List<Book>) bookRepository.findAll();
				books1.forEach(book -> {
					System.out.println(book.getName());});
//		List<Book> books2 = (List<Book>) bookRepository.findAll();
//		System.out.println(books2.size());
	}// n+1 к каждому запросу где есть читатель если не ставить lazy

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
	} // n+1 к каждому запросу где есть читатель добавляется запрос на поиск имени в др. таблице (если не ставить lazy)

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
	public void getBooksByAuthors(){
		List<Author> authors = (List<Author>) authorRepository.findAll();
		authors.forEach(author -> {
			System.out.println(author.getName());
			author.getBooks().forEach(book -> {
				System.out.println(book.getName());
			});
			System.out.println();
		}); // n+1
	}

}
