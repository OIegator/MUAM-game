package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс окна, в котором размещено игровое поле
class window extends JFrame {
    private field gameP; // Закрытая Переменная класса - игровое поле
    private int difficulty; // Закрытая Переменная класса - сложность игры

    // Обработчик событий нажатий на клавиши
    private class myKey implements KeyListener {


        // Метод, который срабатывает при нажатии
        public void keyPressed(KeyEvent e) {

            // Получение кода нажатой клавиши
            int key_ = e.getKeyCode();

            // Выход из программы - Esc
            if (key_ == 27) System.exit(0);
            else if (key_ == 37) { // Если нажата клавиша влево

                // Контроль передвижения за пределы экрана
                if (gameP.x - 15 > -48) gameP.x -= 15;
                else gameP.x = 952;
            } else if (key_ == 39) { // Если нажата клавиша вправо

                // Контроль передвижения за пределы экрана
                if (gameP.x + 15 < 952) gameP.x += 15;
                else gameP.x = -48;
            } else if (key_ == 87) {

                // Увеличение скорости десантника на 1 пиксель
                gift.verticalSpeed++;
                gift.horizontalSpeed++;
            } else if (key_ == 83) {

                // Уменьшение скорости десантника на 1 пиксель
                if (gift.verticalSpeed > 1 && gift.horizontalSpeed > 1) {
                    gift.verticalSpeed--;
                    gift.horizontalSpeed--;
                }
            } else if (key_ == 68) {

                // Увеличение скорости десантника на 30 пикселей
                gift.verticalSpeed += 30;
                gift.horizontalSpeed += 30;
            } else if (key_ == 65) {

                // Уменьшение скорости десантника на 30 пикселей
                if (gift.verticalSpeed > 30 && gift.horizontalSpeed > 30) {
                    gift.verticalSpeed -= 30;
                    gift.horizontalSpeed -= 30;
                }
            }
        }

        public void keyReleased(KeyEvent e) {}

        public void keyTyped(KeyEvent e) {}
    }

    // Конструктор класса
    public window(int difficulty) {
        // Помещение сложности, выбранной пользователем в переменную класса
        this.difficulty = difficulty;

        // Подключение обработчика события для клавиатуры к окну
        addKeyListener(new myKey());

        // Установка ативности окна
        setFocusable(true);

        // Задание размеров окна
        setBounds(0, 0, 1000, 1000);

        // Задание заголовка окна
        setTitle("Игра: Десант");
        gameP = new field(difficulty);

        // Прикрепление(вложение) панели - игровое поле - в окно
        Container con = getContentPane();
        con.add(gameP);

        // Сделать окно видимым
        setVisible(true);
    }
}
