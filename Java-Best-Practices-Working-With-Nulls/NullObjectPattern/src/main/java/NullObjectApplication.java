
import service.BookService;

import java.util.List;

public class NullObjectApplication {

    public static void main(String[] args) {
        BookService bookService = new BookService();
        List<String> list = bookService.getAuthorTitlesWithReadingLevel(1);

        list.forEach(System.out::println);
    }
}
