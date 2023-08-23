package com.snhustudent.ontime.repo;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.snhustudent.ontime.model.User;
import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user_table WHERE userID = :id")
    User getUser(int id);

    @Query("SELECT * FROM user_table WHERE username=:username")
    boolean isTaken(String username);

    @Query("SELECT EXISTS (SELECT * FROM user_table WHERE username=:username AND password=:password)")
    boolean checkLoginData(String username, String password);

    @Query("SELECT * FROM user_table ORDER BY lastName COLLATE NOCASE")
    LiveData<List<User>> getUsers();

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void addUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
