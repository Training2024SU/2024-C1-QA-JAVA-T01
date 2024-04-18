package org.example.excelcsv;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportarLibro {

    public static void reporte(){
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("Publicaciones");

        try {
            FileOutputStream fileOut=new FileOutputStream("Publicaciones.xlsx");
            workbook.write(fileOut);


            fileOut.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
