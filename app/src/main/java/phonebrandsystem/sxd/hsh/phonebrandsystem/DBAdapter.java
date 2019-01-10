package phonebrandsystem.sxd.hsh.phonebrandsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
    private String TABLENAME = "phone";
    private Context context;
    private  ISQL isql;
    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        isql = new ISQL(context);
        db = isql.getWritableDatabase();
    }


    public boolean insert(Phone phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",phone.getName());
        contentValues.put("cpu",phone.getCpu());
        contentValues.put("battery",phone.getBattery());
        long t = db.insert(TABLENAME,null,contentValues);
        return t!=-1;
    }


    public boolean delete(Phone phone) {
        int delete = db.delete(TABLENAME, "name=? and cpu=? and battery=?", new String[]{phone.getName(), phone.getCpu(), phone.getBattery()});
        return delete!=-1;
    }


    public boolean deleteAll() {
        db.execSQL("delete from "+TABLENAME);
        return true;
    }



    public boolean update(Phone phone,Phone newPhone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",newPhone.getName());
        contentValues.put("cpu",newPhone.getCpu());
        contentValues.put("battery",newPhone.getBattery());
        int update = db.update(TABLENAME, contentValues, "name=? and cpu=? and battery=?", new String[]{phone.getName(), phone.getCpu(), phone.getBattery()});
        return update!=-1;
    }

    @SuppressLint("Recycle")
    public List<Phone> query() {
        List<Phone> list  = new ArrayList<>();
        Cursor cursor = db.query(TABLENAME, null, null, null, null, null, null, null);

        if (cursor.moveToFirst()){

            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String cpu = cursor.getString(cursor.getColumnIndex("cpu"));
                String battery = cursor.getString(cursor.getColumnIndex("battery"));

                Phone phone = new Phone();
               if (name!=null){
                   phone.setName(name);
               }
               if (cpu!=null){
                   phone.setCpu(cpu);
               }
               if (battery!=null){
                   phone.setBattery(battery);
               }
                list.add(phone);
            }while (cursor.moveToNext());
        }
        return list;
    }

    class ISQL extends SQLiteOpenHelper{

        public ISQL(Context context){
            super(context, "idata.db", null, 666);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table phone(name text ,cpu text , battery text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
