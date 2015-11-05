package yue.self.hangman.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import yue.self.hangman.entity.Question;

/**
 * Created by ThanhNH on 2/1/2015.
 */
public class DataSource {

    private static DataSource instance;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private DataSource() {
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    //import db from assets if need and open connection
    public void createDatabaseIfNeed() {
        this.openConnection();
    }

    private void openConnection() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            AssetDatabaseOpenHelper assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(context);
            sqLiteDatabase = assetDatabaseOpenHelper.openDatabase();
        }
    }

    private void closeConnection() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public void destroy() {
        closeConnection();
        instance = null;
    }

    public ArrayList<String> getTopics() {
        ArrayList<String> topics = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from sqlite_sequence", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            topics.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return topics;
    }

    public Question getQuestion(String topic) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + topic + " order by random() limit 1", null);
        cursor.moveToFirst();
        Question question = new Question(cursor.getString(0), "Unknown");
        return question;
    }
}