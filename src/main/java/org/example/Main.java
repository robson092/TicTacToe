package org.example;

import java.util.Random;
import java.util.Scanner;
class TicTacToe {
    static final int SIZE = 3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    private char[][] map = new char[SIZE][SIZE];
    private final Scanner scanner = new Scanner(System.in);
    private int xMove;
    private int yMove;
    private int moveCounter;
    public static void main(String[] args) {
        new TicTacToe().gameLoop();
    }
    public void gameLoop() {
        initMap();
        while (true) {
            humanTurn();
            if (checkWin(DOT_X)) {
                System.out.println("Gratulacje, wygrałeś!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Remis :(");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("AI wygrywa!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Remis :(");
                break;
            }
        }
        System.out.println("Koniec gry");
        printMap();
    }
    void initMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                map[i][j] = ' ';
                System.out.print(map[i][j] + "|");
            }
            System.out.println();
        }
    }
    void printMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "|");
            }
            System.out.println();
        }
    }
    private void humanTurn() {
        int x, y;
        do {
            System.out.println("Wpisz po sobie koordynaty X i Y, oddzielając je znakiem enter i wybierając liczbę od 1 do 3:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        yMove = y;
        xMove = x;
        moveCounter++;
    }
    private void aiTurn() {
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
        yMove = y;
        xMove = x;
        moveCounter++;
    }
    private boolean checkWin(char dot) {
        boolean isWin = false;
        //Rows
        for(int i = 0; i < map.length; i++) {
            if(map[yMove][i] != dot) {
                break;
            }
            if(i == map.length - 1) {
                isWin = true;
            }
        }

        //Columns
        for(int i = 0; i < map.length; i++) {
            if(map[i][xMove] != dot) {
                break;
            }
            if(i == map.length - 1) {
                isWin = true;
            }
        }

        //Diagonal
        if(xMove == yMove) {
            for(int i = 0; i < map.length; i++) {
                if(map[i][i] != dot) {
                    break;
                }
                if (i == map.length - 1) {
                    isWin = true;
                    break;
                }
            }
        }

        //Anti-diag
        if(xMove + yMove == map.length - 1) {
            for(int i = 0; i < map.length; i++) {
                if(map[i][(map.length-1)-i] != dot) {
                    break;
                }
                if(i == map.length - 1) {
                    isWin = true;
                }
            }
        }
        return isWin;
    }
    private boolean isMapFull() {
        return moveCounter == Math.pow(map.length, 2);
    }
    private boolean isCellValid(int x, int y) {
        if(x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] != DOT_X && map[y][x] != DOT_O;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}