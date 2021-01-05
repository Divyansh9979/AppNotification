package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Notification extends AppCompatActivity {

    Button btnToast, btnStatus, btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnToast = findViewById(R.id.btn_toast);
        btnStatus = findViewById(R.id.btn_status);
        btnDialog = findViewById(R.id.btn_dialog);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Notification.this, "This is an example.", Toast.LENGTH_LONG).show();
            }
        });

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is an example.";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        Notification.this
                )
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notification")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent = new Intent(Notification.this, MessageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);

                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(Notification.this);
                taskStackBuilder.addParentStack(MessageActivity.class);
                taskStackBuilder.addNextIntent(intent);

                PendingIntent pendingIntent = taskStackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
                notificationManager.notify(0, builder.build());
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
            }
        });
    }
}