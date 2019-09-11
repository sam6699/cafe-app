package cafe.sam.uz.myapplication.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.net.URISyntaxException;

public class FileUtils {
    public static String getPath(ContentResolver contentResolver, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {

                cursor = contentResolver.query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
}
