package com.mediaphile_bit272.mediaphilev2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "mediaManager";
 
    // Contacts table name
    private static final String TABLE_MOVIES = "movies";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_RELEASE_DATE = "release_date";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MPAA = "mpaa_rating";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_CAST = "cast";
    private static final String KEY_PLOT = "plot";
    private static final String KEY_RUNTIME = "runtime";
    private static final String KEY_FORMAT = "format";
    private static final String KEY_SIZE = "size";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_PATH = "path";
    private static final String KEY_LENT_TO = "lent_to";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_NOTE = "note";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
        		+ KEY_TITLE + " TEXT,"
                + KEY_RELEASE_DATE + " TEXT,"
                + KEY_YEAR + " TEXT,"
                + KEY_MPAA + " TEXT,"
                + KEY_GENRE + " TEXT,"
                + KEY_CAST + " TEXT,"
                + KEY_PLOT + " TEXT,"
                + KEY_RUNTIME + " TEXT,"
                + KEY_FORMAT + " TEXT,"
                + KEY_SIZE + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_PATH + " TEXT,"
                + KEY_LENT_TO + " TEXT,"
                + KEY_IMAGE_URL + " TEXT,"
                + KEY_NOTE  + " TEXT"+ ")";
        db.execSQL(CREATE_MOVIES_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
 
        // Create tables again
        onCreate(db);
    }
    
 // Adding new movie
    public void addMovie(Movie movie) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle()); // Movie Title
        values.put(KEY_RELEASE_DATE, movie.getReleaseDate()); // Movie Release Date
        values.put(KEY_YEAR, movie.getYear()); // Movie Year
        values.put(KEY_MPAA, movie.getMpaa()); // Movie MPAA Rating
        values.put(KEY_GENRE, movie.getGenre()); // Movie Genre
        values.put(KEY_CAST, movie.getCast()); // Movie Cast
        values.put(KEY_PLOT, movie.getPlot()); // Movie Plot
        values.put(KEY_RUNTIME, movie.getRuntime()); // Movie Runtime
        values.put(KEY_FORMAT, movie.getFormat()); // Movie Format
        values.put(KEY_SIZE, movie.getSize()); // Movie Size
        values.put(KEY_LOCATION, movie.getLocation()); // Movie Location
        values.put(KEY_PATH, movie.getPath()); // Movie Path
        values.put(KEY_LENT_TO, movie.getLentTo()); // Movie Lent To
        values.put(KEY_IMAGE_URL, movie.getImageUrl()); // Movie Image URL
        values.put(KEY_NOTE, movie.getNote()); // Movie Note
     
        // Inserting Row
        db.insert(TABLE_MOVIES, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single movie
    public Movie getMovie(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	String selectQuery = "SELECT * FROM " + TABLE_MOVIES + " WHERE id=" + id;
    	
    	
    	Cursor cursor = db.rawQuery(selectQuery, null);
    	 
//        Cursor cursor = db.query(TABLE_MOVIES, new String[] { KEY_ID,
//        		KEY_TITLE,
//        		KEY_RELEASE_DATE,
//        		KEY_YEAR,
//        		KEY_MPAA,
//        		KEY_GENRE,
//        		KEY_CAST,
//        		KEY_PLOT,
//        		KEY_RUNTIME,
//        		KEY_FORMAT,
//        		KEY_SIZE,
//        		KEY_LOCATION,
//        		KEY_PATH,
//        		KEY_LENT_TO,
//        		KEY_IMAGE_URL,
//        		KEY_NOTE}, KEY_ID + "=" + id,
//                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Movie movie = new Movie(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getString(7), cursor.getString(8), cursor.getString(9),
                cursor.getString(10), cursor.getString(11), cursor.getString(12),
                cursor.getString(13), cursor.getString(14), cursor.getString(15));
        // return movie
        return movie;
    }
     
    // Getting All Movies
    public List<Movie> getAllMovies() {
    	List<Movie> movieList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Movie movie = new Movie();
                movie.setID(Integer.parseInt(cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setReleaseDate(cursor.getString(2));
                movie.setYear(cursor.getString(3));
                movie.setMpaa(cursor.getString(4));
                movie.setGenre(cursor.getString(5));
                movie.setCast(cursor.getString(6));
                movie.setPlot(cursor.getString(7));
                movie.setRuntime(cursor.getString(8));
                movie.setFormat(cursor.getString(9));
                movie.setSize(cursor.getString(10));
                movie.setLocation(cursor.getString(11));
                movie.setPath(cursor.getString(12));
                movie.setLentTo(cursor.getString(13));
                movie.setImageUrl(cursor.getString(14));
                movie.setNote(cursor.getString(15));
                // Adding movie to list
                movieList.add(movie);
            } while (cursor.moveToNext());
        }     
        // return movie list
        return movieList;
    }
     
    // Getting movie Count
    public int getMovieCount() {
    	String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    // Updating single movie
    
    public int updateMovie(Movie movie) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RELEASE_DATE, movie.getReleaseDate());
        values.put(KEY_YEAR, movie.getYear());
        values.put(KEY_MPAA, movie.getMpaa());
        values.put(KEY_GENRE, movie.getGenre());
        values.put(KEY_CAST, movie.getCast());
        values.put(KEY_PLOT, movie.getPlot());
        values.put(KEY_RUNTIME, movie.getRuntime());
        values.put(KEY_FORMAT, movie.getFormat());
        values.put(KEY_SIZE, movie.getSize());
        values.put(KEY_LOCATION, movie.getLocation());
        values.put(KEY_PATH, movie.getPath());
        values.put(KEY_LENT_TO, movie.getLentTo());
        values.put(KEY_IMAGE_URL, movie.getImageUrl());
        values.put(KEY_NOTE, movie.getNote());
     
        // updating row
        return db.update(TABLE_MOVIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getID()) });
    }
     
    // Deleting single movie
    public void deleteMovie(Movie movie) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getID()) });
        db.close();
    }

}
