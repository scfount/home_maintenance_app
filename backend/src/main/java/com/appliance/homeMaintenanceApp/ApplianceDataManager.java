package com.appliance.homeMaintenanceApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Retrieve information from mysql DB!
 *
 */
public class ApplianceDataManager {

    private static Statement stmt;
    private static PreparedStatement preparedStmt;
    private static ResultSet results;
    private static List<Appliance> dbApplianceList = new ArrayList<>();


    public static List<Appliance> getAllDBAppliances() {

        String sql_select = "SELECT * FROM appliances";

        try(Connection conn = DBconnection.createNewDBconnection()) {

            preparedStmt = conn.prepareStatement(sql_select);
            results = preparedStmt.executeQuery();

            while (results.next()) {

                Appliance appObject = new Appliance();

                appObject.setId(Integer.valueOf(results.getString("id")));
                appObject.setApplianceName(results.getString("name"));
                appObject.setServiceFrequency(results.getString("service_frequency"));
                appObject.setLastServiceDate(results.getString("last_service_date"));
                appObject.setNextServiceDate(results.getString("next_service_date"));
                appObject.setApplianceLink(results.getString("appliance_link"));
                appObject.setDescription(results.getString("service_instructions"));

                if (!dbApplianceList.contains(appObject)) {
                    dbApplianceList.add(appObject);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbApplianceList;
    }

    public static void deleteDBAppliance(int id) {
        String sql_delete = "DELETE FROM appliances " +
                "WHERE id = ?";

        try(Connection conn = DBconnection.createNewDBconnection()){

            preparedStmt = conn.prepareStatement(sql_delete);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveDBAppliance(Appliance appliance) {
        if (dbApplianceList.contains(appliance.getId())) {
            dbApplianceList.remove(appliance);
            deleteDBAppliance(appliance.getId());

        }
        String sql_insert = "INSERT INTO appliances (id, name, service_frequency, last_service_date, next_service_date, appliance_link, service_instructions) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconnection.createNewDBconnection()) {

            preparedStmt = conn.prepareStatement(sql_insert);
            preparedStmt.setInt(1, appliance.getId());
            preparedStmt.setString(2, appliance.getApplianceName());
            preparedStmt.setString(3, appliance.getServiceFrequency());
            preparedStmt.setString(4, appliance.getLastServiceDate());
            preparedStmt.setString(5, appliance.getNextServiceDate());
            preparedStmt.setString(6, appliance.getApplianceLink());
            preparedStmt.setString(7, appliance.getDescription());
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbApplianceList.add(appliance);
    }

}
