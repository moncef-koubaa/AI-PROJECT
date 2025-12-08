package models;

import java.util.List;

public class Truck {
    public int id;
    public Cell home;
    public Cell currentPos;
    public List<Delivery> deliveries;
    public int currentDeliveryIndex;
    public int currentStep;
    public boolean returningToStore;


    public Truck(int id, Cell home, List<Delivery> deliveries) {
        this.id = id;
        this.home = home;
        this.currentPos = home;
        this.deliveries = deliveries;
        this.currentDeliveryIndex = 0;
        this.currentStep = 0;
        this.returningToStore = false;
    }

    public boolean isAtStore() {
        return currentPos.row == home.row && currentPos.col == home.col;
    }

    public boolean moveNext() {
        if (currentDeliveryIndex >= deliveries.size()) {
            return false;
        }

        Delivery currentDelivery = deliveries.get(currentDeliveryIndex);

        if (returningToStore) {
            returningToStore = false;
            currentDeliveryIndex++;
            currentStep = 0;

            if (currentDeliveryIndex >= deliveries.size()) {
                return false;
            }

            currentDelivery = deliveries.get(currentDeliveryIndex);
        }

        if (currentStep >= currentDelivery.path.size()) {
            currentPos = home;
            returningToStore = true;
            return true;
        }

        ActionEnum action = currentDelivery.path.get(currentStep);
        if (currentPos.getTransition(action) != null) {
            currentPos = currentPos.getTransition(action).nextCell;
        }
        currentStep++;

        return true;
    }

    public String getStatus() {
        if (currentDeliveryIndex >= deliveries.size()) {
            return "IDLE at store - All deliveries complete (" + deliveries.size() + "/" + deliveries.size() + ")";
        }

        Delivery currentDelivery = deliveries.get(currentDeliveryIndex);

        if (returningToStore) {
            return "At store, preparing for next delivery (" + (currentDeliveryIndex + 1) + "/" + deliveries.size() + ")";
        } else if (currentStep == 0 && isAtStore()) {
            return "At store, starting delivery " + (currentDeliveryIndex + 1) + "/" + deliveries.size() +
                    " to Client (" + currentDelivery.targetClient.row + "," + currentDelivery.targetClient.col + ")";
        } else if (currentStep >= currentDelivery.path.size()) {
            return "Reached Client (" + currentDelivery.targetClient.row + "," + currentDelivery.targetClient.col +
                    "), teleporting to store - Delivery " + (currentDeliveryIndex + 1) + "/" + deliveries.size();
        } else {
            return "Delivery " + (currentDeliveryIndex + 1) + "/" + deliveries.size() +
                    " to Client (" + currentDelivery.targetClient.row + "," + currentDelivery.targetClient.col +
                    ") at (" + currentPos.row + "," + currentPos.col + ") - Step " + currentStep + "/" + currentDelivery.path.size();
        }
    }
}
