import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> exp1BST = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(0.0);
        y2Values.add(0.0);

        for (int x = 1; x <= 5000; x += 1) {
            ExperimentHelper.insertRandomNumber(exp1BST);
            xValues.add(x);
            yValues.add(exp1BST.averageDepth()); //real depth
            y2Values.add(ExperimentHelper.optimalAverageDepth(x)); // Theoretical depth
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real Depth", xValues, yValues);
        chart.addSeries("Optimal Depth", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> exp1BST = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        for (int x = 1; x <= 5000; x += 1) {
            ExperimentHelper.insertRandomNumber(exp1BST);
        }
        xValues.add(0);
        yValues.add(exp1BST.averageDepth());

        for (int x = 1; x <= 50000; x++) {
            ExperimentHelper.deleteRandomUnit(exp1BST);
            ExperimentHelper.insertRandomNumber(exp1BST);
            xValues.add(x);
            yValues.add(exp1BST.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real Depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> exp1BST = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        for (int x = 1; x <= 5000; x += 1) {
            ExperimentHelper.insertRandomNumber(exp1BST);
        }
        xValues.add(0);
        yValues.add(exp1BST.averageDepth());

        for (int x = 1; x <= 50000; x++) {
            ExperimentHelper.deleteRandomUnit2(exp1BST);
            ExperimentHelper.insertRandomNumber(exp1BST);
            xValues.add(x);
            yValues.add(exp1BST.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real Depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();
    }
}
