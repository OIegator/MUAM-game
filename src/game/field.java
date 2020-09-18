package game;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class field extends JPanel {
    private Image hat; // Закрытая переменная класса, в которую загружается шапка
    private Image background; // Закрытая переменная класса, в которую загружается фон
    public int x = 500;
    private int difficulty; // Переменная сложности
    private gift[] gameGift; // Массив подарков
    private Image endGame; // Изображение окончания игры
    public Timer timerUpdate;
    public Timer timerSW;
    public Timer timerDraw; // Три таймера
    int stopwatch = 0;
    int score = 0;
    public int ct = 0;

    // Конструктор класса
    public field(int difficulty) {
        this.difficulty = difficulty;
        // Загрузка шапки из файла
        try {
            hat = ImageIO.read(new File("./hat.png"));
            //JOptionPane.showMessageDialog(null, "Кратинка для шапки успешно загружена.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка для шапки не загружена.");
        }

        // Загрузка фона из файла
        try {
            background = ImageIO.read(new File("./background.png"));
            //JOptionPane.showMessageDialog(null, "Кратинка фона игры успешно загружена.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка фона игры не загружена.");
        }

        // Загрузка окончания игры из файла
        try {
            endGame = ImageIO.read(new File("./endGame.png"));
            //JOptionPane.showMessageDialog(null, "Кратинка конца игры успешно загружена.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Картинка конца игры  не загружена.");
        }

        // Загрузка изображений подарков
        gameGift = new gift[10];
        for (int i = 0; i < 10; i++) {
            try {
                gameGift[i] = new gift(ImageIO.read(new File("./gift" + i + ".png")));
               // JOptionPane.showMessageDialog(null, "Кратинка подарка №" + i + " успешно загружена.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Кратинка подарка №" + i + " не загружена.");
            }
        }

        // Создание таймера, который будет раз в 2 секудны
        // проверять подарки и добавлять их на игровое поле
        timerUpdate = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Мето для проверки и добавления подарков на игровое поле
                updateStart();
            }
        });

        timerUpdate.start();

        timerSW = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch++;
            }
        });

        timerSW.start();

        // Создание таймера, который будет перерисововать
        // игровое поле 20 раз в секунду
        timerDraw = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint(); // Запуск метода перерисовки поля (public void paintComponent(Graphics gr))
            }
        });

        timerDraw.start(); // Запуск таймера для перерисовки
    }

    // Метод который отрисовывает графические объекты на панели
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Font font = new Font("Tahoma", Font.BOLD, 40);
        gr.drawImage(background, 0, 0, null);
        gr.drawImage(hat, x, 900, null);
        gr.setFont(font);
        gr.drawString("Time: " + stopwatch, 100, 100);
        gr.drawString("Score: " + score, 700, 100);

        for (int i = 0; i < 10; i++) {
            gameGift[i].draw(gr);
            if (gameGift[i].act) { // Если подарок пропущен
                // Если подарок достиг нижней границы
                if (gameGift[i].y + gameGift[i].img.getHeight(null) >= 900)
                    if (Math.abs(gameGift[i].x - x) > 48) { // Если подарок пропущен
                        // Вывод картинки Окончания игры
                        gr.drawImage(endGame, 0, 0, null);
                        gr.drawString("Uptime: " + stopwatch, 400, 500);
                        gr.drawString("Score: " + score, 400, 550);
                        timerDraw.stop(); // Остановка таймера timerDraw
                        timerSW.stop(); // Остановка таймера  timerSW
                        timerUpdate.stop(); // Остановка таймера timerUpdate
                        break; // Прерывание цикла
                    } else {
                        gameGift[i].act = false;
                        score++;
                    }
            }
        }
    }

    private void updateStart() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (!gameGift[i].act) {
                if (count < difficulty) {
                    gameGift[i].start();
                    ct++;
                    break; // Прерывание цикла
                }
            } else count++; // Если подарок на игровом поле
        }
    }
}
