package org.moreno.cristian.servicios.excel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.repositorios.RepositorioUsuario;
import org.moreno.cristian.servicios.ServicioUsuario;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class EscribirExcel {

    private static final Workbook workbook = new XSSFWorkbook();
    private static final RepositorioUsuario servicioUsuario = new ServicioUsuario();
    static Logger log = LogManager.getLogger(String.valueOf(EscribirExcel.class));

    public static void migrarUsuarios () throws IOException {
        Sheet hoja = workbook.createSheet("Usuarios");
        OutputStream output = null;

        Field[] fields = Usuario.class.getDeclaredFields();

        Row cabecera = hoja.createRow(0);
        int columna = 0;

        for (Field field : fields) {
            Cell celda = cabecera.createCell(columna);
            celda.setCellValue(field.getName());
            columna++;
        }

        Optional<List<Usuario>> usuarios = servicioUsuario.listarUsuarios();

        if (usuarios.isPresent()) {
            for (int fila = 1; fila <= usuarios.get().size(); fila++) {

                Row row = hoja.createRow(fila);
                int col = 0;
                Usuario usuario = usuarios.get().get(fila-1);
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(usuario);
                        Cell cell = row.createCell(col);
                        cell.setCellValue(value.toString());
                        col++;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            output = new FileOutputStream("EscrituraExcel.xlsx");
            workbook.write(output);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ocurrió un error al tratar de crear el archivo Excel ",e);
        } catch (IOException e) {
            throw new RuntimeException("Ocurrió un error al tratar de escribir en el archivo Excel ",e);
        }

        workbook.close();
        output.close();
    }

}
