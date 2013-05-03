package com.asccode.siteswatch.support;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.asccode.siteswatch.R;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 03/05/13
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class NotificationSupport {

    public static void showNotification( String message, Context context){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                                                                                    .setContentTitle("Cronjob Alert "+context.getString(R.string.app_name))
                                                                                    .setContentText(message)
                                                                                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                                                                                    .setAutoCancel(true);

        Intent resultIntent = new Intent(context, com.asccode.siteswatch.telas.Notification.class);

        // add data
        resultIntent.putExtra("message", message);

        TaskStackBuilder stackBuilder = TaskStackBuilder.from(context);

        stackBuilder.addParentStack(com.asccode.siteswatch.telas.Notification.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify( (int) System.currentTimeMillis(), mBuilder.getNotification() );

    }

}
