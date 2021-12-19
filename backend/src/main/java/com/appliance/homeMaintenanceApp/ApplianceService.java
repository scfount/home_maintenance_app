package com.appliance.homeMaintenanceApp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplianceService {

    public static List<Appliance> appliances = new ArrayList<>();
    private static int idCounter = 0;

    public List<Appliance> getAllAppliances() {
        if (appliances.isEmpty()) {
            appliances.addAll(ApplianceDataManager.getAllDBAppliances());
            idCounter = setApplianceIDCounter();
            return appliances;
        }
        return appliances;
    }

    public Appliance save(Appliance appliance) {
        if (appliance.getId() == -1 || appliance.getId() == 0) {
            appliance.setId(++idCounter);
        } else {
            deleteById(appliance.getId());
        }
        appliances.add(appliance);
        ApplianceDataManager.saveDBAppliance(appliance);
        return appliance;
    }

    public int setApplianceIDCounter() {
        int nextID = 0;

        for (Appliance app : appliances) {
            int maxID = app.getId();
            if (maxID > nextID) {
                nextID = maxID;
            }
        }
        return nextID++;
    }

    public Appliance deleteById(int id) {

        ApplianceDataManager.deleteDBAppliance(id);

        Appliance appliance = findById(id);

        if (appliance == null)
            return null;

        if (appliances.remove(appliance)) {
            return appliance;
        }
        return null;
    }

    public Appliance findById(int id) {
        for (Appliance appliance : appliances) {
            if (appliance.getId() == id) {
                return appliance;
            }
        }
        return null;
    }
}
