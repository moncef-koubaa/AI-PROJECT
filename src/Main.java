import deliveryPlanner.DeliveryPlanner;
import models.ExecutionResult;
import models.Grid;
import services.GridGenerator;

import java.io.IOException;
import com.sun.management.OperatingSystemMXBean;
import models.Grid;
import services.GridGenerator;
import services.GridService;

import java.lang.management.ManagementFactory;
import  deliveryPlanner.DeliveryPlanner.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GridGenerator gridGeneratorService = new GridGenerator();
        GridService gridService = new GridService();
        Grid grid = null;
        DeliveryPlanner deliveryPlanner = new DeliveryPlanner();
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
                    grid= gridGeneratorService.generate();
                    System.out.println("\nGenerated Map:");
                    gridService.print(grid);
                    pause(); break;

                case 2:
                    System.out.println("\nChoose search:");
                    System.out.println("1. BFS");
                    System.out.println("2. DFS");
                    System.out.println("3. Iterative Deepening Search");
                    System.out.println("4.UniformCostSearch");
                    System.out.println("5 .A* 1");
                    System.out.println("6.A* 2");
                    System.out.println("6.Greedy Search 1");
                    System.out.println("7.Greedy Search 2");
                    System.out.print("Choice: ");
                    int algo = getInt(sc);
                    String strategyKey = switch (algo) {
                        case 1 -> "BF";
                        case 2 -> "DF";
                        case 3 -> "ID";
                        case 4 -> "UC";
                        case 5 -> "AS1";
                        case 6 -> "AS2";
                        case 7 -> "GR1";
                        case 8 -> "GR2";
                        default -> "";
                    };


                    Grid finalGrid = grid;
                    String initialGridString = "6;6;2;1;1,1,4,4;0,0,5,5;2,2;0,0,0,1,1;0,0,1,0,1;0,1,0,0,1;0,1,0,2,1;0,1,1,1,1;0,2,0,1,1;0,2,0,3,1;0,2,1,2,1;0,3,0,2,1;0,3,0,4,1;0,3,1,3,1;0,4,0,3,1;0,4,0,5,1;0,4,1,4,1;0,5,0,4,1;0,5,1,5,1;1,0,0,0,1;1,0,1,1,1;1,0,2,0,1;1,1,0,1,1;1,1,1,0,1;1,1,1,2,1;1,1,2,1,1;1,2,0,2,1;1,2,1,1,1;1,2,1,3,1;1,2,2,2,1;1,3,0,3,1;1,3,1,2,1;1,3,1,4,1;1,3,2,3,1;1,4,0,4,1;1,4,1,3,1;1,4,1,5,1;1,4,2,4,1;1,5,0,5,1;1,5,1,4,1;1,5,2,5,1;2,0,1,0,1;2,0,2,1,1;2,0,3,0,1;2,1,1,1,1;2,1,2,0,1;2,1,2,2,1;2,1,3,1,1;2,2,1,2,1;2,2,2,1,1;2,2,2,3,1;2,2,3,2,1;2,3,1,3,1;2,3,2,2,1;2,3,2,4,1;2,3,3,3,1;2,4,1,4,1;2,4,2,3,1;2,4,2,5,1;2,4,3,4,1;2,5,1,5,1;2,5,2,4,1;2,5,3,5,1;3,0,2,0,1;3,0,3,1,1;3,0,4,0,1;3,1,2,1,1;3,1,3,0,1;3,1,3,2,1;3,1,4,1,1;3,2,2,2,1;3,2,3,1,1;3,2,3,3,1;3,2,4,2,1;3,3,2,3,1;3,3,3,2,1;3,3,3,4,1;3,3,4,3,1;3,4,2,4,1;3,4,3,3,1;3,4,3,5,1;3,4,4,4,1;3,5,2,5,1;3,5,3,4,1;3,5,4,5,1;4,0,3,0,1;4,0,4,1,1;4,0,5,0,1;4,1,3,1,1;4,1,4,0,1;4,1,4,2,1;4,1,5,1,1;4,2,3,2,1;4,2,4,1,1;4,2,4,3,1;4,2,5,2,1;4,3,3,3,1;4,3,4,2,1;4,3,4,4,1;4,3,5,3,1;4,4,3,4,1;4,4,4,3,1;4,4,4,5,1;4,4,5,4,1;4,5,3,5,1;4,5,4,4,1;4,5,5,5,1;5,0,4,0,1;5,0,5,1,1;5,1,4,1,1;5,1,5,0,1;5,1,5,2,1;5,2,4,2,1;5,2,5,1,1;5,2,5,3,1;5,3,4,3,1;5,3,5,2,1;5,3,5,4,1;5,4,4,4,1;5,4,5,3,1;5,4,5,5,1;5,5,4,5,1;5,5,5,4,1;";

                    monitorFunction(() -> deliveryPlanner.plan(initialGridString, strategyKey, true),true,strategyKey);

                    pause(); break;

                case 3:
                    System.out.println("\nRunning ALL searches...");
                    System.out.println("\nRunning ALL searches...");
                    String[] strategies = {"BF","DF","ID","UC","AS1","AS2","GR1","GR2"};
                    List<ExecutionResult> results = new ArrayList<>();
//                    String gridString = grid
                    String gridString = "6;6;2;1;1,1,4,4;0,0,5,5;2,2;0,0,0,1,1;0,0,1,0,1;0,1,0,0,1;0,1,0,2,1;0,1,1,1,1;0,2,0,1,1;0,2,0,3,1;0,2,1,2,1;0,3,0,2,1;0,3,0,4,1;0,3,1,3,1;0,4,0,3,1;0,4,0,5,1;0,4,1,4,1;0,5,0,4,1;0,5,1,5,1;1,0,0,0,1;1,0,1,1,1;1,0,2,0,1;1,1,0,1,1;1,1,1,0,1;1,1,1,2,1;1,1,2,1,1;1,2,0,2,1;1,2,1,1,1;1,2,1,3,1;1,2,2,2,1;1,3,0,3,1;1,3,1,2,1;1,3,1,4,1;1,3,2,3,1;1,4,0,4,1;1,4,1,3,1;1,4,1,5,1;1,4,2,4,1;1,5,0,5,1;1,5,1,4,1;1,5,2,5,1;2,0,1,0,1;2,0,2,1,1;2,0,3,0,1;2,1,1,1,1;2,1,2,0,1;2,1,2,2,1;2,1,3,1,1;2,2,1,2,1;2,2,2,1,1;2,2,2,3,1;2,2,3,2,1;2,3,1,3,1;2,3,2,2,1;2,3,2,4,1;2,3,3,3,1;2,4,1,4,1;2,4,2,3,1;2,4,2,5,1;2,4,3,4,1;2,5,1,5,1;2,5,2,4,1;2,5,3,5,1;3,0,2,0,1;3,0,3,1,1;3,0,4,0,1;3,1,2,1,1;3,1,3,0,1;3,1,3,2,1;3,1,4,1,1;3,2,2,2,1;3,2,3,1,1;3,2,3,3,1;3,2,4,2,1;3,3,2,3,1;3,3,3,2,1;3,3,3,4,1;3,3,4,3,1;3,4,2,4,1;3,4,3,3,1;3,4,3,5,1;3,4,4,4,1;3,5,2,5,1;3,5,3,4,1;3,5,4,5,1;4,0,3,0,1;4,0,4,1,1;4,0,5,0,1;4,1,3,1,1;4,1,4,0,1;4,1,4,2,1;4,1,5,1,1;4,2,3,2,1;4,2,4,1,1;4,2,4,3,1;4,2,5,2,1;4,3,3,3,1;4,3,4,2,1;4,3,4,4,1;4,3,5,3,1;4,4,3,4,1;4,4,4,3,1;4,4,4,5,1;4,4,5,4,1;4,5,3,5,1;4,5,4,4,1;4,5,5,5,1;5,0,4,0,1;5,0,5,1,1;5,1,4,1,1;5,1,5,0,1;5,1,5,2,1;5,2,4,2,1;5,2,5,1,1;5,2,5,3,1;5,3,4,3,1;5,3,5,2,1;5,3,5,4,1;5,4,4,4,1;5,4,5,3,1;5,4,5,5,1;5,5,4,5,1;5,5,5,4,1;";


                    for (String key : strategies) {
                        System.out.println("▶ Running " + key + "...");
                        ExecutionResult res = monitorFunction( () -> deliveryPlanner.plan(gridString, key, true),false,key);
                        results.add(res);

                    }

                    System.out.println("\n================ Performance Comparison ================");
                    System.out.printf("%-8s %-12s %-12s %-12s %-8s%n",
                            "Algo", "Time(ms)", "Memory(MB)", "CPU Δ(%)", "Success");
                    System.out.println("---------------------------------------------------------------");

                    for (ExecutionResult r : results) {
                        System.out.printf("%-8s %-12.2f %-12.2f %-12.2f %-8s%n",
                                r.getName(), r.getTimeMs(), r.getMemUsedMB(), r.getCpuLoadChangePercent(), r.isSuccess() ? "✔" : "✘");
                    }

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

    // ---- CPU & RAM monitor wrapper ----
    public static ExecutionResult monitorFunction(Runnable task,boolean print,String name) throws Exception {
        OperatingSystemMXBean osBean =
                ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        Runtime runtime = Runtime.getRuntime();

        System.out.println("Monitoring Started...\n");

        long startUsedMem = (runtime.totalMemory() - runtime.freeMemory());
        long startTime = System.nanoTime();
        double startCPU = osBean.getProcessCpuLoad();
        try {

            task.run();
        }
        catch (Exception e) {
            System.out.println("Error during monitored function execution: " + e.getMessage());
            return new ExecutionResult(name,0,0,0,false);
        }
        long endTime = System.nanoTime();
        long endUsedMem = (runtime.totalMemory() - runtime.freeMemory());
        double endCPU = osBean.getProcessCpuLoad();

        long memUsedMB = (endUsedMem - startUsedMem) / (1024 * 1024);
        double cpuUsage = (endCPU - startCPU) * 100;
        if(print) {

            System.out.println("\n----- FUNCTION USAGE REPORT -----");
            System.out.println("Execution time: " + ((endTime - startTime) / 1e6) + " ms");
            System.out.println("Memory change: " + memUsedMB + " MB");
            System.out.printf("Approx CPU load change: %.2f%%\n", cpuUsage);
        }
        return new ExecutionResult(name,(endTime - startTime)/1e6, memUsedMB, cpuUsage, true);
    }
}
