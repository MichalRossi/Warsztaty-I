package com.company;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.Arrays;

import java.io.IOException;


public class WordSearch {
    public static void main(String[] args){
        String address = getAddress();
        getPopularWords(address);
        String[] forbiddenWordsList = forbiddenWordsList();
        getFilteredPopularWords(forbiddenWordsList);
    }

    public static String getAddress(){
        Scanner scan = new Scanner(System.in);
        String address;
        System.out.println("Podaj adres strony: ");
        while (true) {
            address = scan.nextLine();
            if (isValid(address)) break;
            else System.out.println("Podaj poprawny adres strony!");
        }
        return address;
    }

    public static boolean isValid(String url){
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void getPopularWords(String address){
        Connection connect = Jsoup.connect(address);

        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            PrintWriter printWriter = new PrintWriter("popular_words.txt");
            for (Element elem : links) {
                if (elem.text().length() > 3) {
                    printWriter.print(elem.text());
                    printWriter.println();
                }
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] forbiddenWordsList(){
        String[] forbiddenWords = new String[0];
        Scanner scan = new Scanner(System.in);
        String word;

        while(true){
            System.out.println(
                    "Podaj zakazane słowo. Jeżeli chcesz przerwać napisz \"stop\".");
            word = scan.nextLine();
            if(word.toLowerCase().equals("stop".toLowerCase())) break;
            else {
                forbiddenWords = Arrays.copyOf(forbiddenWords, forbiddenWords.length + 1);
                forbiddenWords[forbiddenWords.length - 1] = word;
            }
        }
        return forbiddenWords;
    }

    public static void getFilteredPopularWords(String[] forbiddenWordsList){
        File file = new File("popular_words.txt");
        String filteredLine;

        try {
            PrintWriter printWriter = new PrintWriter("filtered_popular_words.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                filteredLine = scan.nextLine();

                for(String element: forbiddenWordsList) {
                    filteredLine=filteredLine.replaceAll(element, "");
                }
                printWriter.print(filteredLine+"\n");
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
            e.printStackTrace();
        }
    }
}
