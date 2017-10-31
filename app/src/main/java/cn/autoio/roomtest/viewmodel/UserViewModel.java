package cn.autoio.roomtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cn.autoio.roomtest.entity.User;
import cn.autoio.roomtest.repository.UserRepository;

/**
 * author: gavin
 * created on: 2017-10-31
 * description:
 */
public class UserViewModel extends AndroidViewModel {

    UserRepository userRepository;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
        userRepository.create(application);
    }


    public LiveData<List<User>> observeAllUser() {
        return userRepository.getUserList();
    }

    public void installUser(User user) {
        userRepository.installUser(user);
    }

}
