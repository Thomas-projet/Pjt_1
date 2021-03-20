package e.g.thomas.pjt_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavDB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "LikeDB";
    private static String TABLE_NAME = "favoriteFood";
    public static String KEY_ID = "id";
    public static String ITEM_TITLE = "itemTitle";
    public static String ITEM_IMAGE = "itemImage";
    public static String FAVORITE_STATUS = "fStatus";
    private static String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "("+ KEY_ID + " TEXT," + ITEM_TITLE+ " TEXT," + ITEM_IMAGE + " TEXT," + FAVORITE_STATUS+" TEXT)";

    public FavDB(Context context) {super(context,DATABASE_NAME,null,DB_VERSION);}



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertIntoTheDatabase(String item_title, String item_image, String id, String fav_status)
    {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_IMAGE, item_image);
        cv.put(FAVORITE_STATUS, fav_status);
        db.insert(TABLE_NAME, null,cv);
        Log.d("FavDB Status", item_title + ", favstatus - "+fav_status+" - . "+cv);


    }

    public Cursor read_all_data(String id) {
        SQLiteDatabase db= this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET "+FAVORITE_STATUS+" = '0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());
    }



}
