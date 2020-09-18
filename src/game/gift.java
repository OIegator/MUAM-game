package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class gift {

    public Image img; // Изоражение подарка
    public int x, y;
    // Полодение подарка на игровом поле, в пикселях, х - отступ слева,
    // у - отступ сверху
    // Переменная логического типа,
    // показывающая активность подарка, если он в игровом поле или нет
    public Boolean act;
    public static int verticalSpeed = 2;
    public static int horizontalSpeed = 15;
    Timer timerUpdate;
    Timer timerUpd;

    // Обработчик событий нажатий на клавиши
    private class myKey implements KeyListener {


        // Метод, который срабатывает при нажатии
        public void keyPressed(KeyEvent e) {

            // Получение кода нажатой клавиши
            int key_ = e.getKeyCode();

            if (key_ == 87) {

                // Увеличение скорости десантника на 1 пиксель
                verticalSpeed++;
                horizontalSpeed++;
            } else if (key_ == 83) {

                // Уменьшение скорости десантника на 1 пиксель
                verticalSpeed--;
                horizontalSpeed--;
            } else if (key_ == 68) {

                // Увеличение скорости десантника на 30 пикселей
                verticalSpeed += 30;
                horizontalSpeed += 30;
            } else if (key_ == 65) {

                // Уменьшение скорости десантника на 30 пикселей
                verticalSpeed -= 30;
                horizontalSpeed -= 30;
            }
        }

        public void keyReleased(KeyEvent e) {}

        public void keyTyped(KeyEvent e) {}
    }

    // Конструктор класса
    public gift(Image img) {

        // Создание и настройка таймера, отвечающего за движение подарка вниз
        timerUpdate = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                down();
            }
        });
        timerUpd = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horizontal();
            }
        });
        // Передача изображения из круглых скобок
        // Конструктора класса в переменную класса
        // Изначально делаем подарок неактивным,
        // Отступающим на игровом поле
        this.img = img;
        act = false;
    }

    // Метод, выполняющий активизацию подарка на игровом поле,
    // вывод сверху игрового поля
    public void start() {
        timerUpdate.start(); // Запуск таймера
        timerUpd.start();
        y = 0; // Отступ сверху в пикселях
        // Отступ слева в пикселях,
        // получаемый случайным образом от 0 до 700
        x = (int) (Math.random() * 700);
        act = true; // Свойство активности устанавливаем true
    }

    // Метод, осуществляющий движение подарка вниз
    public void down() {
        if (act) { // Если подарок активен на игровом поле
            y += verticalSpeed; // Увеличение отступа сверху на 4 пикселя
        }
        if ((y + img.getHeight(null)) >= 1000) { // Если подарок достиг нижней границы
            timerUpdate.stop(); // Остановка таймера
        }
    }

    public void horizontal() {
        if (act) { // Если подарок активен на игровом поле
            if (Math.random() > 0.5) {
                if (x + horizontalSpeed > -48) x += 30;
                else x = 952;
            } else {
                if (x - horizontalSpeed > -48) x -= 30;
                else x = 952;
            }
        }

    }

    // Метод, выполняющий отрисовку подарка на игровом поле, если он активен
    public void draw(Graphics gr) {
        if (act) {
            gr.drawImage(img, x, y, null); // Рисование изображения
        }
    }
}
