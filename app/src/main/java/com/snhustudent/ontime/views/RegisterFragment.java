package com.snhustudent.ontime.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

public class RegisterFragment extends Fragment {

    String REGISTER_FAIL_MESSAGE = "Registration Failed. Please try again.";
    String REGISTER_SUCCESS_MESSAGE = "Registration Successful! Welcome ";
    AuthUserViewModel userViewModel;
    NavController navController;
    Button registerButton;
    EditText etFirstName, etLastName, etEmail, etUsername, etPassword, etConfirmPassword;
    String firstName, lastName, email, userName, password, confirmPassword;

    List<User> UserList;

    public RegisterFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        etFirstName = view.findViewById(R.id.register_firstname);
        etLastName = view.findViewById(R.id.register_lastname);
        etEmail = view.findViewById(R.id.register_email);

        etUsername = view.findViewById(R.id.register_username);
        etPassword = view.findViewById(R.id.register_password);
        etConfirmPassword = view.findViewById(R.id.register_confirm_password);

        registerButton = view.findViewById(R.id.button_register);

        Log.d("RegisterFragment", "RegisterFragment onViewCreated");
        userViewModel = new ViewModelProvider(this).get(AuthUserViewModel.class);
        userViewModel.getAllUsers()
                .observe(getViewLifecycleOwner(), users -> UserList = new ArrayList<>(users));

        registerButton.setOnClickListener(v -> {
            firstName = etFirstName.getText().toString();
            lastName = etLastName.getText().toString();
            email = etEmail.getText().toString();
            userName = etUsername.getText().toString();
            password = etPassword.getText().toString();
            confirmPassword = etConfirmPassword.getText().toString();

            int resultCode = registerValidator(UserList, firstName, lastName, email, userName, password, confirmPassword);

            if (resultCode == UserList.size() -1) {

                User user = new User(firstName, lastName, email, userName, password);

                userViewModel.addUser(user);

                navController.navigate(R.id.action_register_to_event_list);

                Toast.makeText(getContext(), REGISTER_SUCCESS_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == -1) {

                Toast.makeText(getContext(), REGISTER_FAIL_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == -2) {
                Toast.makeText(getContext(), REGISTER_FAIL_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == -3) {
                Toast.makeText(getContext(), REGISTER_FAIL_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == -4) {
                Toast.makeText(getContext(), REGISTER_FAIL_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == -5) {
                Toast.makeText(getContext(), REGISTER_FAIL_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == -6) {
                Toast.makeText(getContext(), REGISTER_FAIL_MESSAGE + resultCode, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private int registerValidator(List<User> userList, String firstName, String lastName, String email, String username, String password, String confirmPassword) {

        ArrayList<String> usernameList = new ArrayList<>();
        ArrayList<String> passwordList = new ArrayList<>();
        ArrayList<String> firstNameList = new ArrayList<>();
        ArrayList<String> lastNameList = new ArrayList<>();
        ArrayList<String> emailList = new ArrayList<>();
        ArrayList<Integer> userId = new ArrayList<>();

        for(User user : userList) {
            usernameList.add(user.getUserName());
            passwordList.add(user.getPassword());
            firstNameList.add(user.getFirstName());
            lastNameList.add(user.getLastName());
            emailList.add(user.getEmail());
            userId.add(user.getUserId());
        }

        int index = userId.size();

        if (firstNameList.contains(firstName) && lastNameList.contains(lastName)
                && firstNameList.indexOf(firstName) == lastNameList.indexOf(lastName)) {
            return -1;
        }
        else if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return -2;
        }
        else if (username.length() < 5 || password.length() < 5) {
            return -3;
        }
        else if (usernameList.contains(username) && passwordList.contains(password)
                && usernameList.indexOf(username) == passwordList.indexOf(password)) {
            return -4;
        }
        else if (emailList.contains(email)
                && emailList.indexOf(email) == usernameList.indexOf(username)
                && emailList.indexOf(email) == passwordList.indexOf(password)) {
            return -5;
        }
        else if (!confirmPassword.contentEquals(password) || confirmPassword.isEmpty()) {
            return -6;
        }
        else {
            return index;
        }
    }
}