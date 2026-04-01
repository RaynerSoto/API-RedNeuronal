package com.example.generarpdf.core.serviceInpl;
import com.example.generarpdf.core.dto.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class CrearPDF {
    public static byte[] generateParticipantPdf(List<ParticipanteDto> participants, UserName usuario) throws Exception {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, out);

        document.open();

       for (ParticipanteDto participant : participants) {
            if(usuario.getId_rol() != 1){
                // Título - Nombre (Header)
                Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD);
                Paragraph title = new Paragraph(participant.getNombre(), titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Línea divisoria
                document.add(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_CENTER, -2));

                document.add(Chunk.NEWLINE);

                // Contenedor con datos básicos
                Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
                Font valueFont = new Font(Font.HELVETICA, 12);

                PdfPTable infoTable = new PdfPTable(2);
                infoTable.setWidthPercentage(100);
                infoTable.setSpacingBefore(10);

                infoTable.addCell(createCell("Carnet de identidad:", labelFont));
                infoTable.addCell(createCell(participant.getCi(), valueFont));

                infoTable.addCell(createCell("Teléfono:", labelFont));
                infoTable.addCell(createCell(participant.getTelefono(), valueFont));

                infoTable.addCell(createCell("Sexo:", labelFont));
                infoTable.addCell(createCell(participant.getSexo(), valueFont));

                document.add(infoTable);

                // Otra Línea divisoria
                document.add(new LineSeparator(1, 100, Color.BLACK, Element.ALIGN_CENTER, -2));
                document.add(Chunk.NEWLINE);
            }



            // Tabla de dataset (edad, calzado, etc.)
            for (DatoDto data : participant.getDatoDtoList()) {
                // Crear la tabla dentro del for: nueva tabla para cada dato
                PdfPTable datasetTable = new PdfPTable(5);
                datasetTable.setWidthPercentage(100);
                datasetTable.setSpacingBefore(10);

                // Agregar cabecera
                addHeader(datasetTable, "Edad");
                addHeader(datasetTable, "Número de calzado");
                addHeader(datasetTable, "Medición cintura-tobillo");
                addHeader(datasetTable, "Largo de pierna");
                addHeader(datasetTable, "Patología");

                // Agregar solo la fila del dato actual
                datasetTable.addCell(String.valueOf(data.getEdad()));
                datasetTable.addCell(String.valueOf(data.getNumerodecalzado()));
                datasetTable.addCell(String.valueOf(data.getMedicioncinturatobillo()));
                datasetTable.addCell(String.valueOf(data.getLargopierna()));
                datasetTable.addCell(String.valueOf(data.isPatologia()));

                // Agregar la tabla al documento
                document.add(datasetTable);

                // Agregar salto de línea después de cada tabla
                document.add(Chunk.NEWLINE);

                // Tabla de patologías dinámicas
              if ( data.getPatologiaList() != null && !data.getPatologiaList().isEmpty()) {
                    PdfPTable extraTable = new PdfPTable(1);
                    extraTable.setWidthPercentage(100);
                    addHeader(extraTable, "Patologías adicionales");

                    for (PatologiaDto patologia : data.getPatologiaList()) {
                        extraTable.addCell(patologia.getNombre());
                    }
                    document.add(extraTable);
                }

                // Gráficos por sensor
                SennalDto sennal = data.getSennalList();
                if (sennal != null) {
                    Map<String, List<Double>> sensoresMap = mapearEjeconLista(sennal);
                    for (Map.Entry<String, List<Double>> entry : sensoresMap.entrySet()) {
                        String nombreEje = entry.getKey();
                        List<Double> datos = entry.getValue();

                        if (datos != null && !datos.isEmpty()) {
                            document.newPage();
                            //Paragraph chartTitle = new Paragraph(nombreEje, titleFont);
                           // chartTitle.setAlignment(Element.ALIGN_CENTER);
                           // document.add(chartTitle);

                            // Suponiendo que tu método createLineChart puede recibir List<Double> ahora
                            Image chart = ChartGenerator.createLineChart(nombreEje, datos);
                            chart.scaleToFit(500, 400);
                            document.add(chart);
                        }
                    }
                }
                document.newPage();
            }

            document.newPage(); // Cada participante en una nueva hoja
        }

        document.close();
        return out.toByteArray();
    }

    private static PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static void addHeader(PdfPTable table, String headerTitle) {
        Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        PdfPCell header = new PdfPCell(new Phrase(headerTitle, headFont));
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }

   private static Map<String, List<Double>> mapearEjeconLista(SennalDto sennal){
        Map<String, List<Double>> sensoresMap = new LinkedHashMap<>();

        sensoresMap.put("Acelerómetro X", sennal.getAcelerometroX());
        sensoresMap.put("Acelerómetro Y", sennal.getAcelerometroY());
        sensoresMap.put("Acelerómetro Z", sennal.getAcelerometroZ());

        sensoresMap.put("Giroscopio X", sennal.getGiroscopioX());
        sensoresMap.put("Giroscopio Y", sennal.getGiroscopioY());
        sensoresMap.put("Giroscopio Z", sennal.getGiroscopioZ());

        sensoresMap.put("Magnetómetro X", sennal.getMagnetometroX());
        sensoresMap.put("Magnetómetro Y", sennal.getMagnetometroY());
        sensoresMap.put("Magnetómetro Z", sennal.getMagnetometroZ());

        return sensoresMap;
    }
}
