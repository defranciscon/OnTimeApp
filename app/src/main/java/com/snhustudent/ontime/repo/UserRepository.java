package com.snhustudent.ontime.repo;

import android.app.Application;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snhustudent.ontime.model.User;
import java.util.List;

public class UserRepository {

    private final UserDAO userDao;

    private final LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addUser(User user) {

        UserDatabase.databaseWriteExecutor.execute(() -> userDao.addUser(user));
    }

    public User getUser(int userId) {
        return userDao.getUser(userId);
    }


    public void updateUser(User user) {
        UserDatabase.databaseWriteExecutor.execute(()-> userDao.updateUser(user));
    }

    public void deleteUser(User user) {
        UserDatabase.databaseWriteExecutor.execute(()-> userDao.deleteUser(user));
    }
}
