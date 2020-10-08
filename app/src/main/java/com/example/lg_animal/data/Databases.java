package com.example.lg_animal.data;

import android.provider.BaseColumns;


public final class Databases {

    /* Inner class that defines the table contents */
    public static final class HistoryDB implements BaseColumns {
        public static final String TABLE_NAME = "Historys";
        public static final String TITLE = "title";
        public static final String USERID = "userid";
        public static final String NAME = "name";
        public static final String CONTENT = "content";
        public static final String LIKE = "like";
        public static final String IMAGE = "image";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + HistoryDB.TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HistoryDB.TITLE + " TEXT NOT NULL, " +
                        HistoryDB.USERID + " TEXT NOT NULL, " +
                        HistoryDB.NAME + " TEXT NOT NULL, " +
                        HistoryDB.CONTENT + " TEXT NOT NULL, " +
                        HistoryDB.LIKE + " INTEGER DEFAULT 0, " +
                        HistoryDB.IMAGE + " TEXT DEFAULT 'N')";

        public static final String SQL_DROP_TABLE =
                "DROP TABLE " + HistoryDB.TABLE_NAME + ";";
    }

}
