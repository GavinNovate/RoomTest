package cn.autoio.roomtest.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import cn.autoio.roomtest.entity.User;
import io.reactivex.Flowable;

/**
 * author: gavin
 * created on: 2017-10-31
 * description:
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Flowable<List<User>> findAll();

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    List<User> findAllByUserIds(int[] userIds);

    @Query("SELECT * FROM user WHERE firstName LIKE :firstName AND lastName LIKE :lastName LIMIT 1")
    User findByName(String firstName, String lastName);

    @Insert
    void install(User... users);

    @Delete
    void delete(User... users);
}
