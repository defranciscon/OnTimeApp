package com.snhustudent.ontime.views;
import static android.Manifest.permission.SEND_SMS;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import com.snhustudent.ontime.R;

public class Permissions extends MainActivity {

    private static final String PERMISSION_SEND_SMS = SEND_SMS;
    private static final int PERMISSION_REQ_CODE = 100;
    Button permissionButton = findViewById(R.id.permission_button);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_request_permissions);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        permissionButton.setOnClickListener(view -> requestPermissionLauncher.launch(PERMISSION_SEND_SMS));

    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Read SMS Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Read SMS Permission Denied", Toast.LENGTH_SHORT).show();
                }
            });

    private void requestRuntimePermission() {

        if (ActivityCompat.checkSelfPermission(this, PERMISSION_SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted. You may continue to use the API.", Toast.LENGTH_LONG).show();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_SEND_SMS)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This app requires the SMS to send systems for enhancing the user experience.")
                    .setTitle("Permission Requested")
                    .setCancelable(false)
                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                        ActivityCompat.requestPermissions(this, new String[]{PERMISSION_SEND_SMS}, PERMISSION_REQ_CODE);
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_SEND_SMS}, PERMISSION_REQ_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted. You can use the API which requires permission",
                        Toast.LENGTH_LONG).show();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_SEND_SMS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("This feature is unavailable becuase this feature requires permission that you have denied. " +
                                "Please allow the system to send SMS message notifications from settings to proceed further.")
                        .setTitle("Permission Required")
                        .setCancelable(false)

                        .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())

                        .setPositiveButton("Settings", (dialogInterface, i) -> {

                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            dialogInterface.dismiss();
                        });
                builder.show();
            }
        }
    }
}
