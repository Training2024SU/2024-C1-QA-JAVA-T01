package Garcia.Juan.Exporter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

class ExcelExporter {

    fun exportToExcel(productos: List<Producto>, filePath: String) {
        XSSFWorkbook().use { workbook ->
                val sheet = workbook.createSheet("Productos")

            // Crear la fila de encabezados
            val headerRow = sheet.createRow(0)
            val headers = arrayOf("Nombre", "Precio", "Cantidad") // Ajusta según tus necesidades
            headers.forEachIndexed { index, header ->
                    headerRow.createCell(index).setCellValue(header)
            }

            // Escribir los datos de la lista en el archivo Excel
            productos.forEachIndexed { rowIndex, producto ->
                    val row = sheet.createRow(rowIndex + 1)
                row.createCell(0).setCellValue(producto.nombre) // Ajusta según tus necesidades
                row.createCell(1).setCellValue(producto.precio) // Ajusta según tus necesidades
                row.createCell(2).setCellValue(producto.cantidad) // Ajusta según tus necesidades
                // Agrega más celdas según los atributos de tu objeto Producto
            }

            // Escribir el libro de Excel en un archivo
            FileOutputStream(filePath).use { outputStream ->
                    workbook.write(outputStream)
            }
        }
    }
}