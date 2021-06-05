package mains;

import windows.FrontWindow;
import windows.NewGameWindow;

import java.util.Scanner;

public class Program {
    private static int minValueOfPlayers = 2;
    private static int maxValueOfPlayers = 2;
    private static Scanner sc = new Scanner(System.in);
    private static FrontWindow frontWindow;
    private static Game game;

    public static void main(String[] args) {
        frontWindow = new FrontWindow(minValueOfPlayers, maxValueOfPlayers);
        //game = frontWindow.getNewGameWindow().getGame();
        //game.battle();
        //System.exit(0);
    }

//    private static void valueOfPlayersMethod(){
//        while(currentValueOfPlayers < 0 || currentValueOfPlayers > maxValueOfPlayers){
//            System.out.println("Выберите количество игроков от " + minValueOfPlayers +
//                    " до " + maxValueOfPlayers + ", или нажмите 0 для выхода\n");
//            currentValueOfPlayers = sc.nextInt();
//
//            if(currentValueOfPlayers == 0) System.exit(0);
//            else if (currentValueOfPlayers < 0) System.out.println("Игроков не может быть меньше чем " + minValueOfPlayers);
//            else if (currentValueOfPlayers > 5) System.out.println("Игроков не может быть больше чем " + maxValueOfPlayers);
//        }
//    }
}
