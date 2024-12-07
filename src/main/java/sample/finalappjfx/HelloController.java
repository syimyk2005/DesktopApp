package sample.finalappjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private VBox fieldsContainer;

    //показатель Достижения Запланированного Значения
    @FXML
    private Label indicatorOfAchievementOfThePlannedValue;

    //количество Целевых показателей
    @FXML
    private TextField numberOfTargets;

    //целевой показатель
    @FXML
    private Label targetIndicator;

    @FXML
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            addTextField();
        }
    }

    @FXML
    public void addField() {
        addTextField();
    }

    @FXML
    public void removeField() {
        for (int i = fieldsContainer.getChildren().size() - 1; i >= 0; i--) {
            var node = fieldsContainer.getChildren().get(i);
            if (node instanceof TextField && "summable".equals(node.getUserData()) ) {
                fieldsContainer.getChildren().remove(i);
                break;
            }
        }
    }

    @FXML
    public void sumValues() {
        double totalSum = 0;

        for (var node : fieldsContainer.getChildren()) {
            if (node instanceof TextField && "summable".equals(node.getUserData())) {
                TextField textField = (TextField) node;
                String text = textField.getText();

                try {
                    if (!text.isEmpty()) {
                        totalSum += Double.parseDouble(text);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Некорректное значение в поле: " + text);
                }
            }
        }
        indicatorOfAchievementOfThePlannedValue.setText("" + totalSum);
    }

    //результат целевого показателя
    @FXML
    public void theResultOfTheTargetIndicator(){
        double finalResult = 0;
        int num = Integer.parseInt(numberOfTargets.getText());
        double sumResult = Double.parseDouble(indicatorOfAchievementOfThePlannedValue.getText());
        finalResult = sumResult / num;
        targetIndicator.setText("Результат целевого показателя:" + finalResult);
    }


    private void addTextField() {
        TextField textField = new TextField();
        Label label = new Label("fdf");
        textField.setPromptText("Введите число");
        textField.setMaxWidth(300);
        textField.setUserData("summable");
        fieldsContainer.getChildren().add(textField);

    }


    @FXML
    TextField theTargetValueOfTheIntegratedIndicator;

    //Коэффициэнт значимости
    @FXML
    TextField coefficientOfSignificance;

    @FXML
    Label perfValue;

    @FXML
    public void perfValueResult(){
        double num1 = Double.parseDouble(theTargetValueOfTheIntegratedIndicator.getText());
        double num2 = Double.parseDouble(coefficientOfSignificance.getText());
        double result = num1 - num2;
        perfValue.setText("Значение статистического показателя эффективности деятельности предприятия: "+result);
    }


    @FXML
    public Label standardDeviationLabel;

    @FXML
    public void standardDeviation() {
        int count = 0;
        double quadratResult = 0.0;

        double num;
        try {
            String targetText = targetIndicator.getText();
            String numericPart = targetText.replaceAll("[^\\d.]", "");
            num = Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            System.err.println("Некорректное значение целевого показателя: " + targetIndicator.getText());
            standardDeviationLabel.setText("Ошибка: некорректное значение целевого показателя.");
            return;
        }


        for (var node : fieldsContainer.getChildren()) {
            if (node instanceof TextField && "summable".equals(node.getUserData())) {
                TextField textField = (TextField) node;
                String text = textField.getText();

                try {
                    if (!text.isEmpty()) {
                        double minusResult = Double.parseDouble(text) - num;
                        quadratResult += minusResult * minusResult;
                        count++;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Некорректное значение в поле: " + text);
                }
            }
        }

        if (count == 0) {
            System.err.println("Нет данных для расчета среднеквадратичного отклонения.");
            standardDeviationLabel.setText("Ошибка: отсутствуют данные для расчета.");
            return;
        }

        double meanSquare = quadratResult / (count-1);
        double finalResult = Math.sqrt(meanSquare);

        standardDeviationLabel.setText(("Результат среднеквадратичного отклонения: " + finalResult));
    }

    @FXML
    Label theProbabilityOfEffectiveImplementationLabel;

    @FXML
    public void theProbabilityOfEffectiveImplementation(){
        double finalResult;
        String targetText = standardDeviationLabel.getText();
        String numericPart = targetText.replaceAll("[^\\d.]", "");
        double standardDeviationLabel1 = Double.parseDouble(numericPart);
        System.out.println(standardDeviationLabel1);

        String targetText1 = perfValue.getText();
        String numericPart1 = targetText1.replaceAll("[^\\d.]", "");
        double targetIndicator1 = Double.parseDouble(numericPart1);
        System.out.println(targetIndicator1);
        finalResult = 1 - (standardDeviationLabel1 / targetIndicator1);
        System.out.println(finalResult);
        theProbabilityOfEffectiveImplementationLabel.setText("Вероятность эффективной реализации: " + finalResult);
    }
}