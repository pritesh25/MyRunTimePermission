package com.example.pritesh.myruntimepermission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static com.example.pritesh.myruntimepermission.FragmentLogin.STORAGE_PERMISSION_CODE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new FragmentLogin(),new FragmentLogin().getTag());
        transaction.addToBackStack(new FragmentLogin().getTag());
        transaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Displaying a toast
                Toast.makeText(getApplicationContext(),"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(getApplicationContext(),"Oops you just denied the permission",Toast.LENGTH_LONG).show();
                //startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"else executed",Toast.LENGTH_LONG).show();
        }
    }
}