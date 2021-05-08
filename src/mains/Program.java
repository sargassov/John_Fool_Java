package mains;

import java.util.Scanner;

public class Program {
    private static int minValueOfPlayers = 1;
    private static int maxValueOfPlayers = 1;
    private static int currentValueOfPlayers = -1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        valueOfPlayersMethod();
        Game game = new Game(currentValueOfPlayers);
        game.battle();
        System.exit(0);
    }

    private static void valueOfPlayersMethod(){
        while(currentValueOfPlayers < 0 || currentValueOfPlayers > maxValueOfPlayers){
            System.out.println("Выберите количество игроков от " + minValueOfPlayers +
                    " до " + maxValueOfPlayers + ", или нажмите 0 для выхода\n");
            currentValueOfPlayers = sc.nextInt();

            if(currentValueOfPlayers == 0) System.exit(0);
            else if (currentValueOfPlayers < 0) System.out.println("Игроков не может быть меньше чем " + minValueOfPlayers);
            else if (currentValueOfPlayers > 5) System.out.println("Игроков не может быть больше чем " + maxValueOfPlayers);
        }
    }
}
