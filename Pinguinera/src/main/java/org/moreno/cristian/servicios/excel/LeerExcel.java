package org.moreno.cristian.servicios.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.repositorios.RepositorioUsuario;
import org.moreno.cristian.servicios.ServicioUsuario;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class LeerExcel {

    private static final Workbook workbook = new XSSFWorkbook();
    private static final RepositorioUsuario servicioUsuario = new ServicioUsuario();

    public static void leer () {

        File archivo = new File("LecturaExcel.xlsx");

        try {
            InputStream input = new FileInputStream(archivo);
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet hoja = workbook.getSheetAt(0);
            Iterator<Row> filas = hoja.rowIterator();
            Iterator<Cell> columnas = null;
            Row filaActual = null;
            Cell columnaActual = null;
            while (filas.hasNext()) {
                filaActual = filas.next();
                columnas = filaActual.cellIterator();
                while (columnas.hasNext()) {
                    columnaActual = columnas.next();
                    System.out.println(columnaActual);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el archivo de lectura de Excel ",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
