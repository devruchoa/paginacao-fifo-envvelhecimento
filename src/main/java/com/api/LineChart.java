package com.api;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;

public class LineChart extends JFrame {

    public LineChart(int[] x, int[] yFifo, int[] yEnvelhecimento, String eixoX) {
        XYSeries serieFifo = new XYSeries("Fifo");
        XYSeries serieEnvelhecimento = new XYSeries("Envelhecimento");

        for (int i = 0; i < x.length; i++) {
            serieFifo.add(x[i], yFifo[i]);
            serieEnvelhecimento.add(x[i], yEnvelhecimento[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serieFifo);
        dataset.addSeries(serieEnvelhecimento);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Falta de páginas Fifo Envelhecimento",
                eixoX,
                "Falta de página",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }
}
