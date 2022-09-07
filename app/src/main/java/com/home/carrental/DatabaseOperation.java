package com.home.carrental;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperation extends SQLiteOpenHelper {

    public DatabaseOperation(@Nullable Context context) {
        super(context, "db_furniture", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer primary key AUTOINCREMENT, username varchar(250), useremail varchar(250), userpass varchar(250), address varchar(250), postalcode varchar(250), note varchar(250), car_owner INTEGER)");

        db.execSQL("create table cars(id integer primary key AUTOINCREMENT, user_id INTEGER, model varchar(250), number varchar(250), seats varchar(250), brand varchar(250))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean registerUser(String uname, String uemail, String upass, String address, String postalcode, String note, int carowner) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("insert into users (username, useremail, userpass, address, postalcode, note, car_owner) values ('" + uname + "','" + uemail + "','" + upass + "','" + address + "','" + postalcode + "','" + note + "','" + carowner + "')");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User login(String uname, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where useremail='" + uname + "' AND userpass='" + password + "'", null);
        User user = new User();
        while (cursor.moveToNext()) {
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.email = cursor.getString(2);
            user.password = cursor.getString(3);
            user.address = cursor.getString(4);
            user.postal = cursor.getString(5);
            user.note = cursor.getString(6);
            user.carowner = (cursor.getInt(7) == 1) ? true : false;
        }
        return user;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("useremail", user.email);
        values.put("userpass", user.password);
        values.put("username", user.name);
        values.put("address", user.address);
        values.put("postalcode", user.postal);
        values.put("note", user.note);
        values.put("car_owner", user.carowner);
        // updating row
        return db.update("users", values, "id" + " = ?",
                new String[]{String.valueOf(user.id)});
    }

    public boolean addCar(int userId, String model, String number, String seats, String brand) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("insert into cars (user_id, model, number, seats, brand) values ('" + userId + "', '" + model + "' , '" + number + "', '" + seats + "', '" + brand + "')");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Car> getAllCars() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from cars", null);
        List<Car> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Car car = new Car();
            car.id = cursor.getInt(0);
            car.userId = cursor.getInt(1);
            car.model = cursor.getString(2);
            car.number = cursor.getString(3);
            car.seats = cursor.getString(4);
            car.brand = cursor.getString(5);
            list.add(car);
        }
        return list;
    }
}