package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

public class DatabaseGUI {
    TournieDBHandler db;
    public DatabaseGUI() {
        db = new TournieDBHandler();
        new MainMenu(db);
    }

    public static void main(String[] args) {
        new DatabaseGUI();
    }
}
