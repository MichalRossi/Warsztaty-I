package com.company;
import java.util.Scanner;
import java.util.Random;

public class GuessGame {
    public static void main(String[] args){
        guessNumber();
    }

    public static void  guessNumber (){
        Random rand = new Random();
        int number = rand.nextInt(101);

        int guess;

        Scanner scan = new Scanner(System.in);

        do{System.out.println("Zgadnij liczbę");
            guess = getIntValue(scan);

            if (guess<number){
                System.out.println("Za mało!");
            }
            else if (guess>number){
                System.out.println("Za dużo!");
            }
        }  while(guess!=number);

        System.out.println("Zgadłeś!");

    }

    static int getIntValue(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("To nie jest liczba");
        }

        int a = scanner.nextInt();
        return a;
    }
}
