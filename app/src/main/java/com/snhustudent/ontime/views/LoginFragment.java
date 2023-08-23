package com.snhustudent.ontime.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snhustudent.ontime.R;
import com.snhustudent.ontime.model.User;
import com.snhustudent.ontime.viewmodel.AuthUserViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    String LOGIN_INVALID_MESSAGE = "Invalid login information. Please try again";
    String LOGIN_SUCCESS_MESSAGE = "Login Successful. Welcome ";
    AuthUserViewModel userViewModel;
    NavController navController;
    EditText etUsername, etPassword;
    Button loginButton, createAcctButton, cancelButton;
    String username, password;
    List<User> UserList;

    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        etUsername = view.findViewById(R.id.username_edit_text);
        etPassword = view.findViewById(R.id.password_edit_text);
        loginButton = view.findViewById(R.id.login_button);
        createAcctButton = view.findViewById(R.id.button_create_account);
        cancelButton = view.findViewById(R.id.cancel_button);

        Log.d("LoginFragment", "LoginFragment onViewCreated");
        userViewModel = new ViewModelProvider(this).get(AuthUserViewModel.class);
        userViewModel.getAllUsers()
                .observe(getViewLifecycleOwner(), users -> UserList = new ArrayList<>(users));

        loginButton.setOnClickListener(v -> {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();

            int resultCode = loginValidator(UserList, username, password);

            if (resultCode == UserList.size()) {

                navController.navigate(R.id.action_login_to_event_list);

                Toast.makeText(this.getActivity(), LOGIN_SUCCESS_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this.getActivity(), LOGIN_INVALID_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
        });
        createAcctButton.setOnClickListener(v -> navController.navigate(R.id.action_login_to_register));

        cancelButton.setOnClickListener(view1 -> System.exit(0));
    }

    private int loginValidator(List<User> userList, String username, String password) {

        ArrayList<String> usernameList = new ArrayList<>();
        ArrayList<String> passwordList = new ArrayList<>();
        ArrayList<String> firstNameList = new ArrayList<>();

        for(User user : userList) {
            usernameList.add(user.getUserName());
            passwordList.add(user.getPassword());
            firstNameList.add(user.getFirstName());
        }
        int user_index = usernameList.indexOf(username);
        int password_index = passwordList.indexOf(password);
        int diff_index = user_index - password_index;

        int size = firstNameList.size();

        if (!usernameList.contains(username)) {
            return -1;
        }
        else if (!passwordList.contains(password)) {
            return -2;
        }
        else if (diff_index > 0) {
            return -3;
        }
        else {
            return size;
        }
    }
}