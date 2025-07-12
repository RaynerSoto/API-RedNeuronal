package com.example.generarpdf.core.serviceInpl;
import com.lowagie.text.Image;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ChartGenerator {

    public static Image createLineChart(String title, List<Double> values) throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < values.size(); i++) {
            dataset.addValue(values.get(i), "Signal", Integer.toString(i));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                title, // Título del eje (Ej: "Acelerómetro X")
                "Muestra", // Eje X
                "Valor",   // Eje Y
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Personalización del gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);              // Fondo blanco
        plot.setRangeGridlinePaint(Color.GRAY);             // Líneas grises

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);              // Línea roja
        renderer.setSeriesShapesVisible(0, false);          // Sin marcadores

        // Convertir a imagen
        BufferedImage bufferedImage = chart.createBufferedImage(500, 300);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);

        return Image.getInstance(baos.toByteArray());
    }
}
