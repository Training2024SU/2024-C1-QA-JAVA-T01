package co.com.sofka.utils;

import co.com.sofka.DAO.Impl.NovelDaoImpl;
import co.com.sofka.DAO.NovelDAO;

import co.com.sofka.model.Novel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvNovelManagement {
    private static final NovelDAO novelDao = new NovelDaoImpl();

    public static void exportNovelsData(){
        List<Novel> novels = novelDao.getAllNovels();
        String filePath = "./src/main/resources/csvFiles/novels.csv";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .builder()
                    .setHeader("novelId", "novelTitle","recommendedAge", "quantity", "quantityLoaned", "quantityAvailable", "gender", "authorName")
                    .build();
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            writeNovelsToCsv(novels, csvPrinter);

        } catch (IOException e) {
            UserLogger.error(e.getMessage());
        }
    }
    public static void writeNovelsToCsv(List<Novel> novels, CSVPrinter csvPrinter){
        novels.forEach(novel -> {
            try {
                csvPrinter.printRecord(
                        novel.getId(),
                        novel.getTitle(),
                        novel.getRecommendedAge(),
                        novel.getQuantity(),
                        novel.getQuantityLoaned(),
                        novel.getQuantityAvailable(),
                        novel.getGender(),
                        novel.getAuthor().getName()
                );
            }catch (IOException e){
                UserLogger.error(e.getMessage());

            }
        });
    }
}
