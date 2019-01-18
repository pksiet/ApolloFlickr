package in.apollo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import static in.apollo.utils.Constant.FLICKR_IMAGE_DIR_NAME;

public class Utils {
    public static final String DATE_FORMAT_TAKEN_ON = "yyyy-MM-dd'T'HH:mm:ss-ss:ss";//2019-01-16T19:11:23-08:00

    public static long getLongFromDate(String date){
        long time = 0;
        if(date!=null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_TAKEN_ON);
            try {
                time = simpleDateFormat.parse(date).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return time;
    }

    /**
     * Used for saving image into storage
     * @param bitmap
     * @param fileName
     */

    public static void saveImageToStorage(Bitmap bitmap, String fileName) {
        if(!TextUtils.isEmpty(fileName) && bitmap != null) {
            String root = Environment.getExternalStorageDirectory().toString();
            File apolloDir = new File(root + "/" + FLICKR_IMAGE_DIR_NAME);
            apolloDir.mkdirs();
            File file = new File(apolloDir, fileName);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Call it to show image in phone gallery
     * @param fileName
     * @param context
     */
    public static void addImageToGallery(final String fileName, final Context context) {
        String root = Environment.getExternalStorageDirectory().toString();
        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
        values.put(MediaStore.MediaColumns.DATA, root + "/" + FLICKR_IMAGE_DIR_NAME + "/" + fileName);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }
}
