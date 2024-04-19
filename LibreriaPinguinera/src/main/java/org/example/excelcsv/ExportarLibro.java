package org.example.excelcsv;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.integration.database.mysql.MySqlOperation;
import org.example.integration.database.persistencia.CrudPublicacion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.integration.database.mysql.MySqlConstants.SELECT;
import static org.example.logica.control.ConstantesLogica.TIPO_PUBLICACION;

public class ExportarLibro {
    static MySqlOperation mySqlOperation = new MySqlOperation();

    public ExportarLibro() {
        mySqlOperation = new MySqlOperation();
    }

    public static void reporte() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Publicaciones");

        try {
            String[] cabecera = new String[]{"Titulo", "Tipo publicacion", "Autor", "numero paginas", "Cantidad ejemplares",
                    "Cantidad prestados", "Cantidad disponible"};

            Row filaEncabezados = sheet.createRow(1);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEncabezado = filaEncabezados.createCell(i);
                celdaEncabezado.setCellValue(cabecera[i]);
            }

            int filaDatos = 6;

            mySqlOperation.setSqlStatement(String.format(SELECT, TIPO_PUBLICACION));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            int numCol = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                Row filasDatos = sheet.createRow(filaDatos);
                for (int i = 0; i < numCol; i++) {
                    Cell celdaDatos = filasDatos.createCell(i);
                    if (i == 3 || i == 4 || i == 5 || i == 6) {
                        celdaDatos.setCellValue(resultSet.getInt(i + 1));
                    } else {
                        celdaDatos.setCellValue(resultSet.getString(i + 1));
                    }
                }
                filaDatos++;
            }


            FileOutputStream fileOut = new FileOutputStream("Publicaciones.xlsx");
            workbook.write(fileOut);
            fileOut.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
