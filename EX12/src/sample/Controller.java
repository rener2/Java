package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class.
 */
public class Controller {

    /**
     * The chart for the values.
     */
    @FXML private LineChart<String, Number> lineChart;

    /**
     * Text field for the equation.
     */
    @FXML private TextField inputField;

    /**
     * Largest square pow number.
     */
    private final int biggestPow = 100;

    /**
     * Clear chart and fill it with new values depending
     * on user given equation.
     * @param e trigger
     */
    public final void xController(ActionEvent e) {
        String equation = inputField.getText();
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        List<Double> values = parse();
        int hour = 1;
        for (Double value: values) {
            double result = getValue(equation, value);
            series.getData().add(new XYChart.Data<>(String.valueOf(hour), result));
            hour++;
        }
        lineChart.getData().add(series);
        series.setName("Like ratio");
    }

    /**
     * Parse the file with given values and make it into
     * a list.
     * @return the list
     */
    public final List<Double> parse() {
        List<Double> resultValues = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("c:/users/rene/desktop/rerakk/EX12/src/sample/ITI0011-2016_EX12_Data.txt"));
            String result = br.readLine();
            String[] resultList = result.split(";");
            for (String s : resultList) {
                resultValues.add(Double.valueOf(s));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultValues;
    }

    /**
     * Take the user given equation and value from
     * the file, calculate the new value according
     * to the equation.
     * @param equation user given equation
     * @param value values from the file
     * @return the calculated value
     */
    public final double getValue(String equation, double value) {
        double result = 0;
        equation = equation.replaceAll("x", String.valueOf("*" + value));
        for (int i = 0; i < biggestPow; i++) {
            equation = equation.replaceAll(String.format(value + "\\^" + i), String.format(
                    "Math.pow(" + value + ", " + i + ")"));
        }
        System.out.println(equation);

        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            result = Double.valueOf(engine.eval(equation).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(result);
        return result;
    }
    }

