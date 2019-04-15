package ca.stclairconnect.pritchard.curtis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


import ca.stclairconnect.pritchard.curtis.Objects.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "diy_database";

    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_LISTITEM = "list_item";
    public static final String TABLE_TAGS = "tags";
    public static final String TABLE_PICTURES = "picture";
    public static final String TABLE_CONTRIBUTORS = "contributors";

    /*
     * Project TABLE
     */
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_NAME = "name";
    public static final String PROJECT_COLUMN_IMAGE = "img";
    public static final String PROJECT_COLUMN_DESCRIPTION = "description";

    /*
     * LISTITEM TABLE
     */
    public static final String LISTITEM_COLUMN_ID = "id";
    public static final String LISTITEM_COLUMN_TEXT = "input";
    public static final String LISTITEM_COLUMN_ACTIVE = "active";
    public static final String LISTITEM_COLUMN_URL = "url";
    public static final String LISTITEM_COLUMN_PROJECT = "project_id";



    public static final String TAGS_COLUMN_ID = "id";
    public static final String TAGS_COLUMN_NAME = "name";


    public static final String PICTURE_COLUMN_ID = "id";
    public static final String PICTURE_COLUMN_RESOURCE = "resource";

    public static final String CONTRIBUTORS_COLUMN_ID = "id";
    public static final String CONTRIBUTORS_COLUMN_NAME = "name";
    public static final String CONTRIBUTORS_COLUMN_IMAGE = "image";
    public static final String CONTRIBUTORS_COLUMN_DESCRIPTION = "description";
    public static final String CONTRIBUTORS_COLUMN_POSITION = "position";
    public static final String CONTRIBUTORS_COLUMN_PROJECT = "project_id";


    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE `" + TABLE_PROJECT + "` (" +
            PROJECT_COLUMN_ID + " INTEGER PRIMARY KEY," +
            PROJECT_COLUMN_NAME + " TEXT NOT NULL," +
            PROJECT_COLUMN_IMAGE + " INT NOT NULL," +
            PROJECT_COLUMN_DESCRIPTION + " TEXT NOT NULL" +
            ")";

    public static final String CREATE_TAGS_TABLE = "CREATE TABLE `" + TABLE_TAGS + "` (" +
            TAGS_COLUMN_ID + " INTEGER NOT NULL," +
            TAGS_COLUMN_NAME + " VARCHAR(50) NOT NULL" +
            ")";

    public static final String CREATE_LISTITEM_TABLE = "CREATE TABLE `"+ TABLE_LISTITEM + "` (" +
            LISTITEM_COLUMN_ID + " INTEGER PRIMARY KEY," +
            LISTITEM_COLUMN_TEXT + " TEXT NOT NULL," +
            LISTITEM_COLUMN_URL + " TEXT," +
            LISTITEM_COLUMN_ACTIVE + " BIT DEFAULT 0," +
            LISTITEM_COLUMN_PROJECT + " INT NOT NULL, " +
            "FOREIGN KEY (" + LISTITEM_COLUMN_PROJECT + ") REFERENCES `" + TABLE_PROJECT + "`("+ PROJECT_COLUMN_ID +")"+
            ")";

    public static final String CREATE_CONTRIBUTORS_TABLE = "CREATE TABLE `" + TABLE_CONTRIBUTORS + "`(" +
            CONTRIBUTORS_COLUMN_ID+" INTEGER PRIMARY KEY," +
            CONTRIBUTORS_COLUMN_NAME+" TEXT NOT NULL," +
            CONTRIBUTORS_COLUMN_IMAGE+" INT NOT NULL," +
            CONTRIBUTORS_COLUMN_DESCRIPTION+" TEXT," +
            CONTRIBUTORS_COLUMN_POSITION+" TEXT,"+
            CONTRIBUTORS_COLUMN_PROJECT + " INT NOT NULL, " +
            "FOREIGN KEY (" + CONTRIBUTORS_COLUMN_PROJECT + ") REFERENCES `" + TABLE_PROJECT +"`(" + PROJECT_COLUMN_ID + ")" +
            ")";

    public static final String CREATE_PICTURES_TABLE = "CREATE TABLE " +
            TABLE_PICTURES + "(" + PICTURE_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + PICTURE_COLUMN_RESOURCE + " TEXT)";

    /**
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println(CREATE_PROJECT_TABLE);
        System.out.println(CREATE_TAGS_TABLE);
        System.out.println(CREATE_LISTITEM_TABLE);
//        addTag(new Tag("name"),);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROJECT_TABLE);
        db.execSQL(CREATE_TAGS_TABLE);
        db.execSQL(CREATE_PICTURES_TABLE);
        db.execSQL(CREATE_LISTITEM_TABLE);
        db.execSQL(CREATE_CONTRIBUTORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




    public ArrayList<Project> getAllProjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Project> projects = new ArrayList<Project>();
        String query = "SELECT * FROM " + TABLE_PROJECT;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                System.out.println("id: " + cursor.getString(0) +
                        "\nname: " + cursor.getString(1) + "\nimage: "+cursor.getString(2)+"\ndescription: "+cursor.getString(3));
                projects.add(new Project(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                System.out.println(projects.size());
            } while(cursor.moveToNext());
        }
        db.close();
        return projects;
    }

    public Project getProject(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Project project = null;
        Cursor cursor = db.query(TABLE_PROJECT,new String[]{PROJECT_COLUMN_ID,PROJECT_COLUMN_NAME,PROJECT_COLUMN_IMAGE, PROJECT_COLUMN_DESCRIPTION},PROJECT_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            // pass all inner values as an arraylist
            project = new Project(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2),cursor.getString(3));
        }
        db.close();
        return project;
    }

    public int addProject(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_COLUMN_NAME, project.getName());
        values.put(PROJECT_COLUMN_IMAGE, project.getImage());
        values.put(PROJECT_COLUMN_DESCRIPTION, project.getDescription());
        db.insert(TABLE_PROJECT, null, values);
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()",
                null);
        if(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            db.close();
            return id;
        }
        db.close();
        return -1;
    }

    //MARK: Tags
//    public ArrayList<Tag> getAllTags(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Tag> tags = new ArrayList<Tag>();
//        String query = "SELECT * FROM " + TABLE_TAGS;
//        Cursor cursor = db.rawQuery(query,null);
//        if (cursor.moveToFirst()){
//            do {
//                tags.add(new Tag(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
//            } while(cursor.moveToNext());
//        }
//        db.close();
//        return tags;
//    }
//
//    public Tag getTag(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//         Tag tag = null;
//        Cursor cursor = db.query(TABLE_TAGS,new String[]{TAGS_COLUMN_ID,TAGS_COLUMN_NAME},TAGS_COLUMN_ID + "=?",
//                new String[]{String.valueOf(id)},null,null,null);
//        if (cursor != null){
//            cursor.moveToFirst();
//            // pass all inner values as an arraylist
//            tag = new Tag(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
//        }
//        db.close();
//        return tag;
//    }
//
//    public int  addTag(Tag tag, Profile profile){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(TAGS_COLUMN_ID, tag.getId());
//        values.put(TAGS_COLUMN_NAME, tag.getName());
//        db.insert(TABLE_TAGS, null, values);
//        db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()",
//                null);
//        if(cursor.moveToFirst()){
//            int id = Integer.parseInt(cursor.getString(0));
//            db.close();
//            return id;
//        }
//        db.close();
//        return -1;
//
//    }




    public int addPicture(Picture pic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PICTURE_COLUMN_RESOURCE, pic.getResource());
        db.insert(TABLE_PICTURES, null, values);
        //Grab the picture ID
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()",
                null);
        if(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            db.close();
            return id;
        }
        db.close();
        return -1;
    }

    public Picture getPicture(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Picture picture = null;
        Cursor cursor = db.query(TABLE_PICTURES,
                new String[]{PICTURE_COLUMN_ID, PICTURE_COLUMN_RESOURCE},
                PICTURE_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null,null);
        if(cursor != null){
            cursor.moveToFirst();
            picture = new Picture(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
        }
        db.close();
        return picture;
    }

    public ArrayList<Picture> getAllPictures(){
        ArrayList<Picture> pictureList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PICTURES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                pictureList.add(new Picture(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        db.close();
        return pictureList;
    }

    public ArrayList<ListItem> addListItem(ListItem listItem) {
        System.out.println(listItem.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LISTITEM_COLUMN_PROJECT, listItem.getProjectId());
        values.put(LISTITEM_COLUMN_TEXT, listItem.getName());
        values.put(LISTITEM_COLUMN_URL, listItem.getUrl());
        values.put(LISTITEM_COLUMN_ACTIVE, listItem.isActive());
        db.insert(TABLE_LISTITEM, null, values);
        return this.getListItemsByProjectId(1);
    }


    public ArrayList<ListItem> getListItemsByProjectId(int id){
        ArrayList<ListItem> listItems = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_LISTITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                if(id == Integer.parseInt(cursor.getString(4)))
                    listItems.add(new ListItem(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Boolean.getBoolean(cursor.getString(3)),Integer.parseInt(cursor.getString(4))));
            }while (cursor.moveToNext());
        }

        db.close();
        return listItems;
    }

    public void addContributor(Contributor contributor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTRIBUTORS_COLUMN_NAME, contributor.getName());
        values.put(CONTRIBUTORS_COLUMN_IMAGE, contributor.getImage() );
        values.put(CONTRIBUTORS_COLUMN_POSITION, contributor.getPosition());
        values.put(CONTRIBUTORS_COLUMN_DESCRIPTION, contributor.getDescription());
        values.put(CONTRIBUTORS_COLUMN_PROJECT, contributor.getProjectId());
        db.insert(TABLE_CONTRIBUTORS, null, values);

    }



    public ArrayList<Contributor> getContributorByProjectId(int id){
        ArrayList<Contributor> contributors = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CONTRIBUTORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                if(id == Integer.parseInt(cursor.getString(5)))

                    contributors.add(new Contributor(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),Integer.parseInt(cursor.getString(2)),cursor.getString(4), Integer.parseInt(cursor.getString(5))));
            }while (cursor.moveToNext());
        }

        db.close();
        return contributors;
    }
}
