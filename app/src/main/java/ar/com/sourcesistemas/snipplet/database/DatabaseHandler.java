package ar.com.sourcesistemas.snipplet.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import ar.com.sourcesistemas.snipplet.domain.Preferences;
import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Snipplet.db";
    private static final String TABLE_CATEGORIA = "categoria";
    private static final String TABLE_SNIPPLET = "snipplet";
    private static final String TABLE_PREFERENCES="preferences";


    public static final String COLUMN_ID = "_id";

    private SQLiteDatabase db;


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, 5);


        this.db = this.getWritableDatabase();

    }





    @Override
    public void onCreate(SQLiteDatabase db) {




        String CREATE_SNIPPLET_TABLE=" CREATE TABLE `snipplet` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,   `titulo`	TEXT,    `contenido`	TEXT,`id_categoria` INTEGER    )";

        String CREATE_PREFERENCES_TABLE=" CREATE TABLE `preferences` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,   `username`	TEXT,    `password`	TEXT,`url` TEXT    )";

        String CREATE_CATEGORIA_TABLE = "CREATE TABLE `categoria` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`nombre`	TEXT UNIQUE, `tags`	INTEGER )";

        String CARETE_TAGS_TABLE ="CREATE TABLE `tags` (   `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,  `nombre`	TEXT UNIQUE  )";

        String CREATE_CATEGORIATAG_TABLE = "CREATE TABLE `categoria_tag` ( `id_categoria`	INTEGER,  `id_tag`	INTEGER    )";


        db.execSQL(CREATE_SNIPPLET_TABLE);
        db.execSQL(CREATE_CATEGORIA_TABLE);
        db.execSQL(CARETE_TAGS_TABLE);
        db.execSQL(CREATE_CATEGORIATAG_TABLE);
        db.execSQL(CREATE_PREFERENCES_TABLE);

    }



    public Preferences getPreferences(){

        Preferences preferences = null;

        String sql ="SELECT * FROM "+TABLE_PREFERENCES;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            preferences = new Preferences();
            preferences.setId(cursor.getLong(0));
            preferences.setUsername(cursor.getString(1));
            preferences.setPasswd(cursor.getString(2));
            preferences.setUri(cursor.getString(3));
        }

        cursor.close();

        return preferences;

    }

    public void savePreferences(Preferences preferences){

        ContentValues contentValues  = new ContentValues();

        contentValues.put("username",preferences.getUsername());
        contentValues.put("password",preferences.getPasswd());
        contentValues.put("url",preferences.getUri());
        int nose = db.update(TABLE_PREFERENCES, contentValues, "id="+preferences.getId(), null);
        if(nose == 0){
            db.insert(TABLE_PREFERENCES,null,contentValues);
        }


    }



    public void deleteSnipplet(Snipplet snipplet){

        snipplet = getSnippletBySnippletId(snipplet);
        String table = TABLE_SNIPPLET;
        String whereClause = "id=?";
        String[] whereArgs = new String[] { String.valueOf(snipplet.getId()) };
        db.delete(table, whereClause, whereArgs);

    }





    public void addCategoria(CategoriaDTO categoriaDTO) {

        ContentValues values = new ContentValues();
        values.put("nombre", categoriaDTO.getNombre());
        values.put("tags", "null");

        long id = db.insert(TABLE_CATEGORIA, null, values);
        categoriaDTO.setIdCategoria(id);


    }

    public void saveSnipplet(Snipplet snipplet) {

        ContentValues contentValues  = new ContentValues();
        contentValues.put("titulo",snipplet.getTitulo());
        contentValues.put("contenido",snipplet.getContenido());
        db.update(TABLE_SNIPPLET, contentValues, "id="+snipplet.getId(), null);


    }


    /**
     * Devuelve false si no existe el snipplet
     * @param snipplet
     * @return
     */
    private boolean existeSnipplet(Snipplet snipplet){

        String sql ="select * from "+TABLE_SNIPPLET+" where id ="+snipplet.getId()+"";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.close();



        return cursor.moveToFirst();

    }

    public List<Snipplet> getAllSnipplets(){

        String sql ="SELECT * FROM "+TABLE_SNIPPLET;
        Cursor cursor = db.rawQuery(sql,null);
        List<Snipplet> snipplets = new LinkedList<Snipplet>();

        while(cursor.moveToNext()){
            Snipplet snipplet = new Snipplet();
            snipplet.setId(cursor.getLong(0));
            snipplet.setTitulo(cursor.getString(1));
            snipplet.setContenido(cursor.getString(2));
            snipplets.add(snipplet);
        }

        return snipplets;

    }


    public void insertNewSnipplet(CategoriaDTO categoriaDTO, String titulo, String contenido) {


        categoriaDTO = getCategoriaDTO(categoriaDTO.getNombre());
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("contenido", contenido);
        values.put("id_categoria",categoriaDTO.getIdCategoria());


        db.insert(TABLE_SNIPPLET, null, values);


    }


    public void deleteCategoria(CategoriaDTO categoriaDTO) {

        categoriaDTO = getCategoriaDTO(categoriaDTO.getNombre());

        String table = TABLE_CATEGORIA;
        String whereClause = "id=?";
        String[] whereArgs = new String[] { String.valueOf(categoriaDTO.getIdCategoria()) };
        db.delete(table, whereClause, whereArgs);


        table = TABLE_SNIPPLET;
        whereClause = "id_categoria=?";
         whereArgs = new String[] { String.valueOf(categoriaDTO.getIdCategoria()) };
        db.delete(table, whereClause, whereArgs);
    }


    public void addSnipplets(CategoriaDTO categoriaDTO){

        for (Snipplet sniplet: categoriaDTO.getSnipplets()  ) {

            ContentValues values = new ContentValues();
            values.put("titulo", sniplet.getTitulo());
            values.put("contenido", sniplet.getContenido());
            values.put("id_categoria",categoriaDTO.getIdCategoria());


            db.insert(TABLE_SNIPPLET, null, values);


        }
        db.close();

    }


    public List<CategoriaDTO> getAllCategoriasDTO(){

        String query = "Select * FROM " + TABLE_CATEGORIA ;

        Cursor cursor = db.rawQuery(query, null);
        List<CategoriaDTO> categoriasDTO = new LinkedList<CategoriaDTO>();
        while (cursor.moveToNext()){

            CategoriaDTO categoriaDTO = new CategoriaDTO(cursor.getString(1));
            categoriaDTO.setIdCategoria(cursor.getLong(0));

            List<Snipplet> snipplets = getSnippletsByCategoria(categoriaDTO.getIdCategoria());
            categoriaDTO.setSnipplets(snipplets);
            categoriasDTO.add(categoriaDTO);



        }
        cursor.close();



        return categoriasDTO;
    }

    public CategoriaDTO getCategoriaDTO(String nombreCategoria){

        String query = "Select * FROM " + TABLE_CATEGORIA + " WHERE nombre =  \"" + nombreCategoria + "\"";



        Cursor cursor = db.rawQuery(query, null);

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            categoriaDTO.setIdCategoria(Integer.parseInt(cursor.getString(0)));
            categoriaDTO.setNombre(cursor.getString(1));

            cursor.close();
        } else {
            categoriaDTO = null;
        }

        return categoriaDTO;


    }

    public Snipplet getSnippletBySnippletId(Snipplet snipplet){

        String query = "Select * FROM " + TABLE_SNIPPLET + " WHERE id=  \"" + snipplet.getId() + "\"";



        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return new Snipplet(cursor.getLong(0),cursor.getString(1),cursor.getString(2));

    }


    public List<Snipplet> getSnippletsByCategoria(long idCategoria){

        String query = "Select * FROM " + TABLE_SNIPPLET + " WHERE id_categoria =  \"" + idCategoria + "\"";



        Cursor cursor = db.rawQuery(query, null);


        List<Snipplet> snipplets = new LinkedList<Snipplet>();
        while (cursor.moveToNext()){

            snipplets.add(new Snipplet(cursor.getLong(0),cursor.getString(1),cursor.getString(2)));


        }
        cursor.close();
        return snipplets;

    }


    public CategoriaDTO getSnipplet(CategoriaDTO categoriaDTO){
        String query = "Select * FROM " + TABLE_SNIPPLET + " WHERE id_categoria =  \"" + categoriaDTO.getIdCategoria() + "\"";
        Cursor cursor = db.rawQuery(query, null);

        List<Snipplet> snipplets = new LinkedList<Snipplet>();
        while (cursor.moveToNext()){

            snipplets.add(new Snipplet(cursor.getLong(0),cursor.getString(1),cursor.getString(2)));


        }
        cursor.close();

        categoriaDTO.setSnipplets(snipplets);
        return categoriaDTO;


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        if(newVersion > oldVersion) {
            //String CREATE_PREFERENCES_TABLE = " CREATE TABLE `preferences` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,   `username`	TEXT,    `password`	TEXT,`url` TEXT    )";
            //db.execSQL(CREATE_PREFERENCES_TABLE);
        }
    }


    public void deleteALL(){
        SQLiteDatabase db = this.getWritableDatabase();
        String drop1= "drop table snipplet";
        String drop2 ="drop table categoria";
        String drop3 ="drop table tags";
        String drop4 ="drop table categoria_tag";
        db.execSQL(drop1);
        db.execSQL(drop2);
        db.execSQL(drop3);
        db.execSQL(drop4);

        String CREATE_SNIPPLET_TABLE=" CREATE TABLE `snipplet` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,   `titulo`	TEXT,    `contenido`	TEXT,`id_categoria` INTEGER    )";

        String CREATE_CATEGORIA_TABLE = "CREATE TABLE `categoria` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`nombre`	TEXT UNIQUE, `tags`	INTEGER )";

        String CARETE_TAGS_TABLE ="CREATE TABLE `tags` (   `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,  `nombre`	TEXT UNIQUE  )";

        String CREATE_CATEGORIATAG_TABLE = "CREATE TABLE `categoria_tag` ( `id_categoria`	INTEGER,  `id_tag`	INTEGER    )";


        db.execSQL(CREATE_SNIPPLET_TABLE);
        db.execSQL(CREATE_CATEGORIA_TABLE);
        db.execSQL(CARETE_TAGS_TABLE);
        db.execSQL(CREATE_CATEGORIATAG_TABLE);


    }

    public void closeDatabase(){

        db.close();

    }


}
