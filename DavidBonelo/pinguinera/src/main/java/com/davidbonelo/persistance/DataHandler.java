package com.davidbonelo.persistance;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataHandler {

    private static final String INVENTORY_HEADER = "Type,Id,Title,Author,Copies,Borrowed copies," +
            "Field/Genre,Pages/Recommended age";

    private DataHandler() {
        throw new IllegalStateException("Utility Class");
    }

    public static void main(String[] args) {
        Field[] a = Book.class.getFields();
        for (Field b : a) {
            System.out.println(b);
        }
    }

    public static void saveToCsv(String fileName, List<LibraryItem> items) throws IOException {
        try (FileWriter csvWriter = new FileWriter(fileName)) {
            csvWriter.append(INVENTORY_HEADER + "\n");
            for (LibraryItem li : items) {
                if (li instanceof Book book) {
                    csvWriter.append(bookToCsvLine(book));
                } else if (li instanceof Novel novel) {
                    csvWriter.append(novelToCsvLine(novel));
                }
            }
            csvWriter.flush();
        }
    }

    private static String bookToCsvLine(Book b) {
        return "book," + itemToCsvLine(b) + "," + b.getField() + "," + b.getPages() + "\n";
    }

    private static String novelToCsvLine(Novel n) {
        return "novel," + itemToCsvLine(n) + "," + n.getGenre() + "," + n.getRecommendedAge() +
                "\n";
    }

    private static String itemToCsvLine(LibraryItem i) {
        return i.getId() + "," + i.getTitle() + "," + i.getAuthor() + "," + i.getCopies() + "," + i.getCopiesBorrowed();
    }

    public static List<LibraryItem> readFromCsv(String filename) throws IOException {
        List<LibraryItem> items = new ArrayList<>();
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filename))) {
            String lineText;
            validateHeader(lineReader.readLine());

            // process lines
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                items.add(itemFromCsvLine(data));
            }
        }
        return items;
    }

    private static void validateHeader(String header) {
        if (!header.equals(INVENTORY_HEADER)) {
            throw new IllegalArgumentException("The file to import doesn't have the correct " +
                    "format");
        }
    }

    private static LibraryItem itemFromCsvLine(String[] data) {
        String type = data[0];
        int id = Integer.parseInt(data[1]);
        String title = data[2];
        String author = data[3];
        int copies = Integer.parseInt(data[4]);
        int borrowedCopies = Integer.parseInt(data[5]);

        if (type.equals("book")) {
            int pages = Integer.parseInt(data[7]);
            String field = data[6];
            return new Book(id, title, author, copies, borrowedCopies, field, pages);
        } else if (type.equals("novel")) {
            String genre = data[6];
            int recommendedAge = Integer.parseInt(data[7]);
            return new Novel(id, title, author, copies, borrowedCopies, genre, recommendedAge);
        }

        throw new IllegalArgumentException("Couldn't read line: " + Arrays.toString(data));
    }
}
