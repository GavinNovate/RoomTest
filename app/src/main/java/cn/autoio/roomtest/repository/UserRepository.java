package cn.autoio.roomtest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import cn.autoio.roomtest.data.DataBase;
import cn.autoio.roomtest.entity.User;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: gavin
 * created on: 2017-10-31
 * description:
 */
public class UserRepository {

    DataBase dataBase;

    private MutableLiveData<List<User>> userListLiveData;

    Context context;

    public void create(Context context) {
        this.context = context;
    }

    public LiveData<List<User>> getUserList() {
        if (userListLiveData == null) {
            userListLiveData = new MutableLiveData<>();
            getData(context)
                    .subscribeOn(Schedulers.newThread())
                    .flatMap(new Function<DataBase, Flowable<List<User>>>() {
                        @Override
                        public Flowable<List<User>> apply(DataBase dataBase) throws Exception {
                            return dataBase.userDao().findAll();
                        }
                    })
                    .subscribe(new Consumer<List<User>>() {
                        @Override
                        public void accept(List<User> users) throws Exception {
                            userListLiveData.postValue(users);
                        }
                    });
        }
        return userListLiveData;
    }

    public void installUser(User user) {
        getDatabase(context)
                .subscribe(new Consumer<DataBase>() {
                    @Override
                    public void accept(DataBase dataBase) throws Exception {
                        dataBase.userDao().install(user);
                    }
                });
    }


    private Flowable<DataBase> getData(Context context) {
        return Flowable.just(context)
                .map(new Function<Context, DataBase>() {
                    @Override
                    public DataBase apply(Context context) throws Exception {
                        if (dataBase == null) {
                            dataBase = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "database").build();
                        }
                        return dataBase;
                    }
                });
    }

    private Observable<DataBase> getDatabase(Context context) {
        return Observable.just(context)
                .observeOn(Schedulers.newThread())
                .map(new Function<Context, DataBase>() {
                    @Override
                    public DataBase apply(Context context) throws Exception {
                        if (dataBase == null) {
                            dataBase = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "database").build();
                        }
                        return dataBase;
                    }
                });
    }
}
