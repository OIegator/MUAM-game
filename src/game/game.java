package game;
// Подключение необходимых библиотек

import javax.swing.*; // Работа с формами

public class game {

    public static void main(String[] args) {

        String rez = JOptionPane.showInputDialog(null, "Введите сложность игры от 1 до 9:", "Сложность игры", 1);

        int difficulty = Integer.parseInt(rez); /* .charAt(0) - '0' */
        if ((difficulty >= 1) & (difficulty <= 9)) {
            window window = new window(difficulty);
        }
    }
}
