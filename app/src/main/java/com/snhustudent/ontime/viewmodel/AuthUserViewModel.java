package com.snhustudent.ontime.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snhustudent.ontime.model.User;
import com.snhustudent.ontime.repo.UserRepository;
import java.util.List;

public class AuthUserViewModel extends AndroidViewModel {

    UserRepository userRepo;
    private MutableLiveData<User> userMutableLiveData;
    private final LiveData<List<User>> allUsers;

    public AuthUserViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
        allUsers = userRepo.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public User getUser(int userId) {
        return userRepo.getUser(userId);
    }

    public void addUser(User user) {
        userRepo.addUser(user);
    }

    public void removeUser(User user) {
        userRepo.deleteUser(user);
    }

    public void updateUser(User user) {
        userRepo.updateUser(user);
    }
}
