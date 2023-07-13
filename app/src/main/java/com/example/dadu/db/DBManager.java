package com.example.dadu.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    public static String[] getRandomQuestion(SQLiteDatabase db, String tableName) {
        String[] questionData = new String[6];
        Cursor cursor = null;

        switch (tableName) {
            case "mathematics":
            case "geography":
            case "history":
            case "sports":
            case "sciences":
                cursor = db.rawQuery("SELECT * FROM " + tableName + " ORDER BY RANDOM() LIMIT 1", null);
                break;
            case "random":
                cursor = db.rawQuery("SELECT * FROM (SELECT * FROM mathematics UNION ALL " +
                        "SELECT * FROM geography UNION ALL " +
                        "SELECT * FROM history UNION ALL " +
                        "SELECT * FROM sports UNION ALL " +
                        "SELECT * FROM sciences) ORDER BY RANDOM() LIMIT 1", null);
                break;
        }

        if (cursor != null && cursor.moveToFirst()) {
            questionData[0] = cursor.getString(1); // question
            questionData[1] = cursor.getString(2); // option1
            questionData[2] = cursor.getString(3); // option2
            questionData[3] = cursor.getString(4); // option3
            questionData[4] = cursor.getString(5); // option4
            questionData[5] = cursor.getString(6); // image
        }

        if (cursor != null) cursor.close();

        return questionData;
    }
}
