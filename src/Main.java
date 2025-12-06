import deliveryPlanner.DeliveryPlanner;
import models.Grid;
import services.GridGenerator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GridGenerator generator = new GridGenerator();
        Grid grid = generator.generate();
        String initialGridString = "6;6;2;1;1,1,4,4;0,0,5,5;2,2;0,0,0,1,1;0,0,1,0,1;0,1,0,0,1;0,1,0,2,1;0,1,1,1,1;0,2,0,1,1;0,2,0,3,1;0,2,1,2,1;0,3,0,2,1;0,3,0,4,1;0,3,1,3,1;0,4,0,3,1;0,4,0,5,1;0,4,1,4,1;0,5,0,4,1;0,5,1,5,1;1,0,0,0,1;1,0,1,1,1;1,0,2,0,1;1,1,0,1,1;1,1,1,0,1;1,1,1,2,1;1,1,2,1,1;1,2,0,2,1;1,2,1,1,1;1,2,1,3,1;1,2,2,2,1;1,3,0,3,1;1,3,1,2,1;1,3,1,4,1;1,3,2,3,1;1,4,0,4,1;1,4,1,3,1;1,4,1,5,1;1,4,2,4,1;1,5,0,5,1;1,5,1,4,1;1,5,2,5,1;2,0,1,0,1;2,0,2,1,1;2,0,3,0,1;2,1,1,1,1;2,1,2,0,1;2,1,2,2,1;2,1,3,1,1;2,2,1,2,1;2,2,2,1,1;2,2,2,3,1;2,2,3,2,1;2,3,1,3,1;2,3,2,2,1;2,3,2,4,1;2,3,3,3,1;2,4,1,4,1;2,4,2,3,1;2,4,2,5,1;2,4,3,4,1;2,5,1,5,1;2,5,2,4,1;2,5,3,5,1;3,0,2,0,1;3,0,3,1,1;3,0,4,0,1;3,1,2,1,1;3,1,3,0,1;3,1,3,2,1;3,1,4,1,1;3,2,2,2,1;3,2,3,1,1;3,2,3,3,1;3,2,4,2,1;3,3,2,3,1;3,3,3,2,1;3,3,3,4,1;3,3,4,3,1;3,4,2,4,1;3,4,3,3,1;3,4,3,5,1;3,4,4,4,1;3,5,2,5,1;3,5,3,4,1;3,5,4,5,1;4,0,3,0,1;4,0,4,1,1;4,0,5,0,1;4,1,3,1,1;4,1,4,0,1;4,1,4,2,1;4,1,5,1,1;4,2,3,2,1;4,2,4,1,1;4,2,4,3,1;4,2,5,2,1;4,3,3,3,1;4,3,4,2,1;4,3,4,4,1;4,3,5,3,1;4,4,3,4,1;4,4,4,3,1;4,4,4,5,1;4,4,5,4,1;4,5,3,5,1;4,5,4,4,1;4,5,5,5,1;5,0,4,0,1;5,0,5,1,1;5,1,4,1,1;5,1,5,0,1;5,1,5,2,1;5,2,4,2,1;5,2,5,1,1;5,2,5,3,1;5,3,4,3,1;5,3,5,2,1;5,3,5,4,1;5,4,4,4,1;5,4,5,3,1;5,4,5,5,1;5,5,4,5,1;5,5,5,4,1;";
        System.out.println(initialGridString);
        DeliveryPlanner planner = new DeliveryPlanner();
        planner.PrintGrid(grid);
        planner.plan(initialGridString, "BF", false);
//        Scanner sc = new Scanner(System.in);
//        int choice;
//
//        do {
//            clearScreen();
//
//            System.out.println("====================================");
//            System.out.println(" 🚚  Delivery Search System - MENU ");
//            System.out.println("====================================");
//            System.out.println("1. Generate Random Map");
//            System.out.println("2. Run Search Algorithm");
//            System.out.println("3. Run ALL Searches");
//            System.out.println("0. Exit");
//            System.out.println("====================================");
//            System.out.print("Enter your choice: ");
//            choice = getInt(sc);
//
//            switch (choice) {
//
//                case 1:
//                    System.out.println("\nGenerating map...");
//                    // TODO: generateRandomMap();
//                    pause(); break;
//
//                case 2:
//                    System.out.println("\nChoose search:");
//                    System.out.println("1. BFS");
//                    System.out.println("2. DFS");
//                    System.out.println("3. Uniform Cost (UCS)");
//                    System.out.println("4. A* Search");
//                    System.out.print("Choice: ");
//                    int algo = getInt(sc);
//
//                    System.out.println("\nRunning search...");
//                    // TODO: link to your algorithm calls
//                    // if(algo == 1) BFS();
//                    // if(algo == 2) DFS();
//                    // if(algo == 3) UCS();
//                    // if(algo == 4) AStar();
//
//                    pause(); break;
//
//                case 3:
//                    System.out.println("\nRunning ALL searches...");
//                    // TODO:
//                    // BFS();
//                    // DFS();
//                    // UCS();
//                    // AStar();
//                    pause(); break;
//
//                case 0:
//                    System.out.println("\nExiting program. Goodbye!");
//                    break;
//
//                default:
//                    System.out.println("\nInvalid choice.");
//                    pause();
//            }
//
//        } while (choice != 0);
    }

    // ───── Helper Methods ─────

    private static int getInt(Scanner sc){
        while(!sc.hasNextInt()){
            System.out.print("Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static void pause(){
        System.out.println("\nPress ENTER to continue...");
        try { System.in.read(); } catch(Exception ignored){}
    }

    private static void clearScreen() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();

            // ANSI ESC sequence to clear screen and move cursor to top-left
            final String ansiClear = "\u001b[2J\u001b[H";
            System.out.print(ansiClear);
            System.out.flush();

            if (os.contains("win")) {
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (IOException | InterruptedException e) {
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
