import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            clearScreen();

            System.out.println("====================================");
            System.out.println(" 🚚  Delivery Search System - MENU ");
            System.out.println("====================================");
            System.out.println("1. Generate Random Map");
            System.out.println("2. Run Search Algorithm");
            System.out.println("3. Run ALL Searches");
            System.out.println("0. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");
            choice = getInt(sc);

            switch (choice) {

                case 1:
                    System.out.println("\nGenerating map...");
                    // TODO: generateRandomMap();
                    pause(); break;

                case 2:
                    System.out.println("\nChoose search:");
                    System.out.println("1. BFS");
                    System.out.println("2. DFS");
                    System.out.println("3. Uniform Cost (UCS)");
                    System.out.println("4. A* Search");
                    System.out.print("Choice: ");
                    int algo = getInt(sc);

                    System.out.println("\nRunning search...");
                    // TODO: link to your algorithm calls
                    // if(algo == 1) BFS();
                    // if(algo == 2) DFS();
                    // if(algo == 3) UCS();
                    // if(algo == 4) AStar();

                    pause(); break;

                case 3:
                    System.out.println("\nRunning ALL searches...");
                    // TODO:
                    // BFS();
                    // DFS();
                    // UCS();
                    // AStar();
                    pause(); break;

                case 0:
                    System.out.println("\nExiting program. Goodbye!");
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    pause();
            }

        } while (choice != 0);
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
