import coin.Coin;
import coinFactory.CoinFactory;
import enums.Coins;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList resultsList = new ArrayList();
    static boolean restartCalculator = false;
    static double amount = 0;
    static Coin selectedCoin = null;

    public static void main(String[] args) {

        do {
            //------------------------FIRST SCREEN -----------------------------//

            int coinTypeInd = showFirstScreen();

            while(coinTypeInd != 1 && coinTypeInd != 2){
                printInvalidChoiceErr();
                coinTypeInd = showFirstScreen();
            }

            //------------------------SECOND SCREEN -----------------------------//

            showSecondScreen(coinTypeInd);

            //------------------------THIRD SCREEN -----------------------------//

            showThirdScreen();

        } while(restartCalculator == true);
    }

    public static int showFirstScreen(){
        if (!Main.restartCalculator) {
            System.out.println("Welcome to currency converter");
        }

        System.out.println("Please choose an option (1/2):");
        System.out.println("1.Dollars to Shekels");
        System.out.println("2.Shekels to Dollars");

        Scanner coinTypeScanner = new Scanner(System.in);
        return coinTypeScanner.nextInt();
    }
    public static void showSecondScreen(int coinTypeInd) {
        System.out.println("Please enter an amount to convert");

        Scanner amountScanner = new Scanner(System.in);
        Main.amount = amountScanner.nextDouble();
        var coinType = Coins.values()[coinTypeInd - 1].toString();
        CoinFactory coinFactory = new CoinFactory();

        Main.selectedCoin = coinFactory.getCoin(coinType);
    }
    public static void showThirdScreen() {
        System.out.println("The Amount Is: " + selectedCoin.calculate(Main.amount));
        System.out.println("Do you wish to start over?");
        System.out.println("Y");
        System.out.println("N");

        Scanner restartCalculatorScanner = new Scanner(System.in);
        String restartCalculatorValue = restartCalculatorScanner.next();

        if(restartCalculatorValue.equalsIgnoreCase("y")) {
            restartCalculator = true;
            resultsList.add(Main.amount);
        } else if (restartCalculatorValue.equalsIgnoreCase("n")) {
            restartCalculator = false;
            resultsList.add(Main.amount);
            showEndScreen();
        } else {
            printInvalidChoiceErr();
            showThirdScreen();
        }
    }
    public static void showEndScreen() {
        System.out.println("Thanks for using our currency converter");
        String resultsListStr = getArrListAsString();
        tryWritingStrToTxtFile(resultsListStr);
        resultsList.clear();
    }
    public static String getArrListAsString() {
        var resultsListArr = Main.resultsList.toArray();
        String resultsListStr = "";

        for (int i = 0; i < resultsListArr.length; i++) {
            resultsListStr += resultsListArr[i] + System.lineSeparator();
            System.out.println(resultsListArr[i] + " ");
        }

        return resultsListStr;
    }
    public static void printInvalidChoiceErr() {
        System.out.println("Invalid Choice, please try again");
    }
    public static void tryWritingStrToTxtFile(String str) {
        try {
            FileWriter myWriter = new FileWriter("results.txt");
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
