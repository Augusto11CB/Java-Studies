package service;


import entity.Book;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.List;


public class BookService {
    private BookRepository bookRepository = new BookRepository();

    public List<String> getAuthorTitlesWithReadingLevel(Integer authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);
        List<String> titlesWithReadingLevel = new ArrayList<>();

        for (Book book : books) {
            titlesWithReadingLevel.add(book.getTitle() + " - " + book.getReadingLevel());
            /*String description = book.getTitle() + " - ";
            if(book.getReadingLevel() != null) {
                description += book.getReadingLevel();
            } else {
                description += "No reading level set";
            }
            titlesWithReadingLevel.add(description);*/
        }

        return titlesWithReadingLevel;
    }
}
