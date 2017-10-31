package cn.autoio.roomtest.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: gavin
 * created on: 2017-10-31
 * description:
 */
public class DataBaseBuilder {

    private static final Object LOCK = new Object();

    private DataBase dataBase;

    private DataBaseBuilder() {
    }

    public static DataBaseBuilder get() {
        return SingletonHolder.INSTANCE;
    }

    public Flowable<DataBase> database(Context context) {
        return Flowable.just(context)
                .subscribeOn(Schedulers.io())
                .map(new Function<Context, DataBase>() {
                    @Override
                    public DataBase apply(Context context) throws Exception {
                        return build(context);
                    }
                });
    }

    private DataBase build(Context context) {
        if (dataBase == null) {
            synchronized (LOCK) {
                if (dataBase == null) {
                    dataBase = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "database").build();
                }
            }
        }
        return dataBase;
    }

    private static class SingletonHolder {
        private static final DataBaseBuilder INSTANCE = new DataBaseBuilder();
    }
}
