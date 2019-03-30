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
    public static final String TABLE_PICTURES = "picture";

    /*
     * PROFILE TABLE
     */
    public static final String PROFILE_COLUMN_ID = "id";
    public static final String PROFILE_COLUMN_NAME = "name";
    public static final String PROFILE_COLUMN_IMAGE = "image";
    public static final String PROFILE_COLUMN_DESCRIPTION = "description";
    public static final String PROFILE_COLUMN_ACTIVE = "active";

    /*
     * Project TABLE
     */
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_NAME = "name";
    public static final String PROJECT_COLUMN_USER_ID = "user";
    public static final String PROJECT_COLUMN_IMAGE = "img";
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
    public static final String PROFILE_TAGS_PROFILE_ID = "profile_id";
    public static final String PROFILE_TAGS_TAG_ID = "tag_id";


    public static final String PICTURE_COLUMN_ID = "id";
    public static final String PICTURE_COLUMN_RESOURCE = "resource";


    public static final String CREATE_PROFILE_TABLE = "CREATE TABLE `" + TABLE_PROFILE + "` (" +
            PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY," +
            PROFILE_COLUMN_NAME + " VARCHAR(100) NOT NULL," +
            PROFILE_COLUMN_IMAGE + " INT NOT NULL,"+
            PROFILE_COLUMN_DESCRIPTION + " TEXT DEFAULT NULL," +
            PROFILE_COLUMN_ACTIVE + " BIT DEFAULT 0)";

    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE `" + TABLE_PROJECT + "` (" +
            PROJECT_COLUMN_ID + " INTEGER PRIMARY KEY," +
            PROJECT_COLUMN_NAME + " TEXT NOT NULL," +
            PROJECT_COLUMN_USER_ID + " INTEGER REFERENCES "+ TABLE_PROFILE + "(" + PROFILE_COLUMN_ID +")," +
            PROJECT_COLUMN_IMAGE + " INT NOT NULL," +
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
            PROFILE_TAGS_PROFILE_ID + " INTEGER REFERENCES "+ TABLE_PROFILE + "("+ PROFILE_COLUMN_ID+")," +
            PROFILE_TAGS_TAG_ID + " INTEGER REFERENCES " + TABLE_TAGS + "(" + TAGS_COLUMN_ID + ")" +
            ")";

    public static final String CREATE_PICTURES_TABLE = "CREATE TABLE " +
            TABLE_PICTURES + "(" + PICTURE_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + PICTURE_COLUMN_RESOURCE + " TEXT)";

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
//        addTag(new Tag("name"),);
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
                profiles.add(new Profile(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3),Boolean.parseBoolean(cursor.getString(4))));
            } while(cursor.moveToNext());
        }
        db.close();
        return profiles;
    }

    public Profile getProfile(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Profile profile = null;
        Cursor cursor = db.query(TABLE_PROFILE,new String[]{PROFILE_COLUMN_ID,PROFILE_COLUMN_NAME,PROFILE_COLUMN_IMAGE,PROFILE_COLUMN_DESCRIPTION},PROFILE_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            // pass all inner values as an arraylist
            profile = new Profile(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3), Boolean.parseBoolean(cursor.getString(4)));
        }
        db.close();
        return profile;
    }

    public int addProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_COLUMN_NAME, profile.getName());
        values.put(PROFILE_COLUMN_IMAGE, profile.getImage());
        values.put(PROFILE_COLUMN_DESCRIPTION, profile.getDescription());
        values.put(PROFILE_COLUMN_ACTIVE, profile.isActive());
        db.insert(TABLE_PROFILE, null, values);
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

    public int updateProfile(Profile profile){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_COLUMN_ACTIVE, profile.getName());
        values.put(PROFILE_COLUMN_IMAGE, profile.getImage());
        values.put(PROFILE_COLUMN_DESCRIPTION, profile.getDescription());
        values.put(PROFILE_COLUMN_ACTIVE, profile.isActive());
        return db.update(TABLE_PROFILE, values, PROFILE_COLUMN_ID + "=?",
                new String[]{String.valueOf(profile.getId())});
    }




    public ArrayList<Project> getAllProjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Project> projects = new ArrayList<Project>();
        String query = "SELECT * FROM " + TABLE_PROJECT;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                projects.add(new Project(Integer.parseInt(cursor.getString(0)),cursor.getString(1), /*getProfile(Integer.parseInt(cursor.getString(2)))*/MainActivity.currentUser,Integer.parseInt(cursor.getString(3)),cursor.getString(4)));
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
                    cursor.getString(1),/*getProfile(Integer.getInteger(cursor.getString(2)))*/MainActivity.currentUser,Integer.parseInt(cursor.getString(3)),cursor.getString(4));
        }
        db.close();
        return project;
    }

    public void addProject(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_COLUMN_NAME, project.getName());
        values.put(PROJECT_COLUMN_IMAGE, project.getImage());
        values.put(PROJECT_COLUMN_DESCRIPTION, project.getDescription());
        db.insert(TABLE_PROJECT, null, values);
        db.close();
    }





    public void profileTagForeignKey(int profile, int tag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_TAGS_PROFILE_ID,profile);
        values.put(PROFILE_TAGS_TAG_ID, tag);
        db.insert(TABLE_PROFILE_TAGS,null,values);
    }



    public ArrayList getTagsByUserId(int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        ArrayList<Tag> filteredTags = new ArrayList<Tag>();



        return filteredTags;
    }

    //MARK: Tags
    public ArrayList<Tag> getAllTags(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        String query = "SELECT * FROM " + TABLE_TAGS;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                tags.add(new Tag(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
            } while(cursor.moveToNext());
        }
        db.close();
        return tags;
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

    public int  addTag(Tag tag, Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAGS_COLUMN_ID, tag.getId());
        values.put(TAGS_COLUMN_NAME, tag.getName());
        db.insert(TABLE_TAGS, null, values);
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

    public void projectItemForeignKey(Project project, ListItem listItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_LIST_PROJECT_ID,project.getId());
        values.put(PROJECT_LIST_ITEMLIST_ID, listItem.getId());
        db.insert(TABLE_PROJECT_LISTITEM,null,values);
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
}
