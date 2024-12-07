package sample.finalappjfx;

import java.awt.*;
import java.awt.event.InputEvent;

public class Hello {
    public static void main(String[] args) {
        try {
            // Создание объекта Robot
            Robot robot = new Robot();
            for (int i = 1; i <= 1000; i++) {
            // Задержка, чтобы успеть переключиться на окно, если нужно
            Thread.sleep(200);

            // Перемещение мыши на позицию (x=500, y=300)

                robot.mouseMove(500, 300);
            }
            // Опционально: симуляция нажатия кнопки мыши (например, левый клик)
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            System.out.println("Курсор перемещен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
