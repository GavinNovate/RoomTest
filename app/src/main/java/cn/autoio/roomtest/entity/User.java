package cn.autoio.roomtest.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * author: gavin
 * created on: 2017-10-31
 * description:
 */
@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo
    private String firstName;

    @ColumnInfo
    private String lastName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User:" + userId + " " + firstName + " " + lastName;
    }
}
