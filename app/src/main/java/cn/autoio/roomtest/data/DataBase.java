package cn.autoio.roomtest.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cn.autoio.roomtest.entity.User;

/**
 * author: gavin
 * created on: 2017-10-31
 * description:
 */
@Database(entities = {User.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract UserDao userDao();
}
