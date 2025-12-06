package tests;

import deliveryPlanner.DeliveryPlanner;
import models.Grid;
import services.GridGenerator;

public class GenerateGrid {
    public static void main(String[] args) {
        GridGenerator generator = new GridGenerator();
        Grid grid = generator.generate();
        String initialGridString = "10;6;2;1;1,1,4,2;0,0,5,5;2,0;0,0,0,1,5;0,1,0,2,3;4,2,5,2,2;2,0,2,1,0;";
        System.out.println(initialGridString);
        DeliveryPlanner planner = new DeliveryPlanner();
        planner.PrintGrid(grid);
        planner.plan(initialGridString, "DF", false);

        System.out.println("Grid Size: " + grid.getLength() + " x " + grid.getWidth());
        System.out.println("Clients:");
        grid.getClients().forEach(client -> 
            System.out.println(" - Client at (" + client.getRow() + ", " + client.getCol() + ")")
        );
        System.out.println("Stores:");
        grid.getStores().forEach(store -> 
            System.out.println(" - Store at (" + store.getRow() + ", " + store.getCol() + ")")
        );
        System.out.println("Tunnels:");
        grid.getTunnels().forEach(tunnel -> 
            System.out.println(" - Tunnel from (" + tunnel.get(0).getRow() + ", " + tunnel.get(0).getCol() + 
                               ") to (" + tunnel.get(1).getRow() + ", " + tunnel.get(1).getCol() + ")")
        );
        System.out.println("================= Grid String Representation =================");
        System.out.println(grid.toString());

    }
}