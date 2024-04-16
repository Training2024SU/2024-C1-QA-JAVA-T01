package co.com.sofka.utils;

import co.com.sofka.DAO.BookDAO;
import co.com.sofka.DAO.Impl.BookDAOImpl;
import co.com.sofka.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvBookManagement {
    private static final BookDAO bookDAO = new BookDAOImpl();

    public static void exportBooksData() {
        List<Book> books = bookDAO.getAllBooks();
        String filePath = "./src/main/resources/csvFiles/books.csv";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .builder()
                    .setHeader("bookId", "bookTitle", "quantity", "quantityLoaned", "quantityAvailable", "category", "authorName")
                    .build();
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat);

            writeBooksToCsv(books, csvPrinter);
        } catch (IOException e) {
            UserLogger.error(e.getMessage());
        }
    }

    private static void writeBooksToCsv(List<Book> books, CSVPrinter csvPrinter) {
        for (Book book : books) {
            try {
                csvPrinter.printRecord(
                        book.getId(),
                        book.getTitle(),
                        book.getQuantity(),
                        book.getQuantityLoaned(),
                        book.getQuantityAvailable(),
                        book.getCategory(),
                        book.getAuthor().getName());
            } catch (IOException e) {
                UserLogger.error(e.getMessage());
            }
        }
    }
}
