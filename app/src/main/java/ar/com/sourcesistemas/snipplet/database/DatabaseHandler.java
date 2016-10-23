package ar.com.sourcesistemas.snipplet.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import ar.com.sourcesistemas.snipplet.domain.Snipplet;
import ar.com.sourcesistemas.snipplet.dto.CategoriaDTO;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Snipplet.db";
    private static final String TABLE_CATEGORIA = "categoria";
    private static final String TABLE_SNIPPLET = "snipplet";

    public static final String COLUMN_ID = "_id";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);


        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {




        String CREATE_SNIPPLET_TABLE=" CREATE TABLE `snipplet` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,   `titulo`	TEXT,    `contenido`	TEXT,`id_categoria` INTEGER    )";

        String CREATE_CATEGORIA_TABLE = "CREATE TABLE `categoria` (  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`nombre`	TEXT UNIQUE, `tags`	INTEGER )";

        String CARETE_TAGS_TABLE ="CREATE TABLE `tags` (   `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,  `nombre`	TEXT UNIQUE  )";

        String CREATE_CATEGORIATAG_TABLE = "CREATE TABLE `categoria_tag` ( `id_categoria`	INTEGER,  `id_tag`	INTEGER    )";


        db.execSQL(CREATE_SNIPPLET_TABLE);
        db.execSQL(CREATE_CATEGORIA_TABLE);
        db.execSQL(CARETE_TAGS_TABLE);
        db.execSQL(CREATE_CATEGORIATAG_TABLE);
        db.close();
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


    public void insertNewSnipplet(CategoriaDTO categoriaDTO, String titulo, String contenido) {


        categoriaDTO = getCategoriaDTO(categoriaDTO.getNombre());
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("contenido", contenido);
        values.put("id_categoria",categoriaDTO.getIdCategoria());


        db.insert(TABLE_SNIPPLET, null, values);


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

            categoriasDTO.add(new CategoriaDTO(cursor.getString(1)));


        }
        cursor.close();
        db.close();


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
