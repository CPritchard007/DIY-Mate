package ca.stclairconnect.pritchard.curtis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


import ca.stclairconnect.pritchard.curtis.Objects.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "diy_database";

    public static final String TABLE_PROFILE = "profile";
    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_LISTITEM = "list_item";
    public static final String TABLE_TAGS = "tags";
    public static final String TABLE_PROFILE_TAGS = "profile_tags";
    public static final String TABLE_PROJECT_LISTITEM = "project_list_item";

    /*
     * PROFILE TABLE
     */
    public static final String PROFILE_COLUMN_ID = "id";
    public static final String PROFILE_COLUMN_NAME = "name";
    public static final String PROFILE_COLUMN_DESCRIPTION = "description";

    /*
     * Project TABLE
     */
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_NAME = "name";
    public static final String PROJECT_COLUMN_IMAGE = "image";
    public static final String PROJECT_COLUMN_DESCRIPTION = "description";

    /*
     * LISTITEM TABLE
     */
    public static final String LISTITEM_COLUMN_ID = "id";
    public static final String LISTITEM_COLUMN_ACTIVE = "active";
    public static final String LISTITEM_COLUMN_URL = "url";

    public static final String PROJECT_LISTITEM_ID = "id";
    public static final String PROJECT_LIST_PROJECT_ID = "project_id";
    public static final String PROJECT_LIST_ITEMLIST_ID = "list_id";


    public static final String TAGS_COLUMN_ID = "id";
    public static final String TAGS_COLUMN_NAME = "name";


    public static final String PROFILE_TAGS_ID = "id";
    public static final String PROFILE_TAGS_PRIFILE_ID = "profile_id";
    public static final String PROFILE_TAGS_TAG_ID = "tag_id";


    public static final String CREATE_PROFILE_TABLE = "CREATE TABLE `" + TABLE_PROFILE + "` (" +
            PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY," +
            PROFILE_COLUMN_NAME + " VARCHAR(100) NOT NULL," +
            PROFILE_COLUMN_DESCRIPTION + " TEXT DEFAULT NULL)";

    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE `" + TABLE_PROJECT + "` (" +
            PROJECT_COLUMN_ID + " INTEGER PRIMARY KEY," +
            PROJECT_COLUMN_NAME + " TEXT NOT NULL," +
            PROJECT_COLUMN_IMAGE + " INTEGER NOT NULL," +
            PROJECT_COLUMN_DESCRIPTION + " INTEGER NOT NULL" +
            ")";

    public static final String CREATE_TAGS_TABLE = "CREATE TABLE `" + TABLE_TAGS + "` (" +
            TAGS_COLUMN_ID + " INTEGER NOT NULL," +
            TAGS_COLUMN_NAME + " VARCHAR(50) NOT NULL" +
            ")";

    public static final String CREATE_ITEMSLIST_TABLE = "CREATE TABLE `"+ TABLE_LISTITEM + "` (" +
            LISTITEM_COLUMN_ID + " INTEGER PRIMARY KEY," +
            LISTITEM_COLUMN_ACTIVE + " BIT DEFAULT 0," +
            LISTITEM_COLUMN_URL + " TEXT NOT NULL" +
            ")";

    public static final String CREATE_PROJECT_LIST_ITEM_TABLE = "CREATE TABLE `" + TABLE_PROJECT_LISTITEM + "` (" +
            PROJECT_LISTITEM_ID + " INTEGER NOT NULL," +
            PROJECT_LIST_PROJECT_ID + " INT NOT NULL," +
            PROJECT_LIST_ITEMLIST_ID + " INT NOT NULL" +
            ")";

    public static final String CREATE_PROFILE_TAGS_TABLE = "CREATE TABLE `" + TABLE_PROFILE_TAGS + "` ("+
            PROFILE_TAGS_ID + " INTEGER NOT NULL," +
            PROFILE_TAGS_PRIFILE_ID + " INT NOT NULL," +
            PROFILE_TAGS_TAG_ID + " INT NOT NULL" +
            ")";


    /**
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println(CREATE_PROFILE_TABLE);
        System.out.println(CREATE_PROJECT_TABLE);
        System.out.println(CREATE_TAGS_TABLE);
        System.out.println(CREATE_PROFILE_TAGS_TABLE);
        System.out.println(CREATE_PROJECT_LIST_ITEM_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_PROJECT_TABLE);
        db.execSQL(CREATE_TAGS_TABLE);
        db.execSQL(CREATE_ITEMSLIST_TABLE);
        db.execSQL(CREATE_PROFILE_TAGS_TABLE);
        db.execSQL(CREATE_PROJECT_LIST_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Profile> getAllProfiles(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Profile> profiles = new ArrayList<Profile>();
        String query = "SELECT * FROM " + TABLE_PROFILE;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                cursor.moveToFirst();
                profiles.add(new Profile(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2)));
            } while(cursor.moveToNext());
        }
        db.close();
        return profiles;
    }

    public Profile getProfile(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Profile profile = null;
        Cursor cursor = db.query(TABLE_PROFILE,new String[]{PROFILE_COLUMN_ID,PROFILE_COLUMN_NAME,PROFILE_COLUMN_DESCRIPTION},PROFILE_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            // pass all inner values as an arraylist
            profile = new Profile(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2));
        }
        db.close();
        return profile;
    }

    public void addProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_COLUMN_NAME, profile.getName());
        values.put(PROFILE_COLUMN_DESCRIPTION, profile.getDescription());
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }





    public ArrayList<Project> getAllProjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Project> projects = new ArrayList<Project>();
        String query = "SELECT * FROM " + TABLE_PROJECT;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                cursor.moveToFirst();
                projects.add(new Project(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)),cursor.getString(3)));
            } while(cursor.moveToNext());
        }
        db.close();
        return projects;
    }

    public Profile getProject(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Profile profile = null;
        Cursor cursor = db.query(TABLE_PROJECT,new String[]{PROJECT_COLUMN_ID,PROJECT_COLUMN_NAME,PROJECT_COLUMN_IMAGE, PROJECT_COLUMN_DESCRIPTION},PROJECT_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            // pass all inner values as an arraylist
            profile = new Profile(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2));
        }
        db.close();
        return profile;
    }

    public void addProject(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_COLUMN_NAME, profile.getName());
        values.put(PROFILE_COLUMN_DESCRIPTION, profile.getDescription());
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public ArrayList<Project> getAllTags(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Project> projects = new ArrayList<Project>();
        String query = "SELECT * FROM " + TABLE_PROJECT;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                cursor.moveToFirst();
                projects.add(new Project(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)),cursor.getString(3)));
            } while(cursor.moveToNext());
        }
        db.close();
        return projects;
    }

    public Tag getTag(int id){
        SQLiteDatabase db = this.getReadableDatabase();
         Tag tag = null;
        Cursor cursor = db.query(TABLE_TAGS,new String[]{TAGS_COLUMN_ID,TAGS_COLUMN_NAME},TAGS_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            // pass all inner values as an arraylist
            tag = new Tag(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        }
        db.close();
        return tag;
    }

    public void addTag(Tag tag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAGS_COLUMN_ID, tag.getId());
        values.put(TAGS_COLUMN_NAME, tag.getName());
        db.insert(TABLE_PROFILE, null, values);
        db.close();

    }
}
