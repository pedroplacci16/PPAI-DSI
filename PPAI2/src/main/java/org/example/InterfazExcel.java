package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InterfazExcel {

    public static void exportarExcel(List<Map.Entry<String, HashMap<String, Object>>> vinosOrdenados) {
        // Crear un nuevo libro de Excel
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Top 10 Vinos");

        // Encabezados de columnas
        String[] encabezadoColumnas = {"Nombre", "Calificación Sommelier", "Precio Sugerido", "Bodega", "Varietal", "Región", "País"};

        // Crear el encabezado
        Row encabezado = hoja.createRow(0);
        for (int i = 0; i < encabezadoColumnas.length; i++) {
            Cell celda = encabezado.createCell(i);
            celda.setCellValue(encabezadoColumnas[i]);
        }

        // Agregar los datos de los vinos al Excel
        int fila = 1;
        int contador = 0;
        for (Map.Entry<String, HashMap<String, Object>> entry : vinosOrdenados) {
            if (contador >= 10) {
                break;
            }
            HashMap<String, Object> info = entry.getValue();
            Row row = hoja.createRow(fila++);

            row.createCell(0).setCellValue(entry.getKey());
            if (info.get("puntajePromedio") != null) {
                row.createCell(1).setCellValue((double) info.get("puntajePromedio"));
            }
            if (info.get("precio") != null) {
                row.createCell(2).setCellValue((double) info.get("precio"));
            }
            if (info.get("nombreBodega") != null) {
                row.createCell(3).setCellValue((String) info.get("nombreBodega"));
            }
            if (info.get("descripcionVarietal") != null) {
                // Convertir el array de Strings en un solo String
                String[] varietales = (String[]) info.get("descripcionVarietal");
                String varietalesStr = String.join(", ", varietales);
                row.createCell(4).setCellValue(varietalesStr);
            }
            if (info.get("nombreRegion") != null) {
                row.createCell(5).setCellValue((String) info.get("nombreRegion"));
            }
            if (info.get("nombrePais") != null) {
                row.createCell(6).setCellValue((String) info.get("nombrePais"));
            }

            contador++;
        }

        try {
            FileOutputStream archivo = new FileOutputStream("Top10Vinos.xlsx");
            libro.write(archivo);
            archivo.close();
            System.out.println("Archivo Excel generado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al generar el archivo Excel: " + e.getMessage());
        }
    }


}
