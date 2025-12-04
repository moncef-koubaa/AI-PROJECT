package tests;

import models.Grid;
import services.GridGenerator;

public class GenerateGrid {
    public static void main(String[] args) {
        GridGenerator generator = new GridGenerator();
        Grid grid = generator.generate();

        System.out.println("Grid Size: " + grid.getLength() + " x " + grid.getWidth());
        System.out.println("Stores:");
        grid.getStores().forEach(store -> 
            System.out.println(" - Store at (" + store.getRow() + ", " + store.getCol() + ")")
        );
        System.out.println("Clients:");
        grid.getClients().forEach(client -> 
            System.out.println(" - Client at (" + client.getRow() + ", " + client.getCol() + ")")
        );
        System.out.println("================= Grid String Representation =================");
        System.out.println(generator.toString(grid));

    }
}