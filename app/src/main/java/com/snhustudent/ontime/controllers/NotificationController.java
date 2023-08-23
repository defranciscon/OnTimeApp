package com.snhustudent.ontime.controllers;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import androidx.core.app.NotificationCompat;


public class NotificationController {

    private NotificationCompat.Builder builder;
    private Context context;
    private String CHANNEL_ID;
    private int icon;
    private String title;
    private String text;
    private int PRIORITY = NotificationCompat.PRIORITY_DEFAULT;
    private PendingIntent pendingIntent;
    private NotificationController(NotificationCompat.Builder aBuilder) {
        this.builder = aBuilder;
    }

    public NotificationController setNotificationController(NotificationCompat.Builder aBuilder) {
        builder = aBuilder;
        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(icon)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setPriority(PRIORITY)
                        .setContentIntent(pendingIntent);

        return new NotificationController(builder);
    }

    public NotificationCompat.Builder constructtNotificationContents(
            String CHANNEL_ID,
            Context contentContext,
            int imageIcon,
            String contentTitle,
            String contentText,
            int PRIORITY,
            PendingIntent contentIntent

    ) {
        this.CHANNEL_ID = CHANNEL_ID;
        this.context = contentContext;
        this.icon = imageIcon;
        this.title = contentTitle;
        this.text = contentText;
        this.PRIORITY = PRIORITY;
        this.pendingIntent = contentIntent;

        return new NotificationCompat.Builder(context, this.CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(this.PRIORITY)
                .setContentIntent(pendingIntent);
    }

    CharSequence name;
    String description;
    int importance;

    public void initNotificationChannel() {
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(context, NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }
}
