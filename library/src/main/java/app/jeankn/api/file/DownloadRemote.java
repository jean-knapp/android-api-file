package app.jeankn.api.file;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;
import static java.net.HttpURLConnection.HTTP_SEE_OTHER;

public class DownloadRemote extends Write {

    public DownloadRemote(String path, String remotePath, Context context, OnFinishListener finishListener) {
        super(path, remotePath, context, finishListener);
    }

    @Override
    protected Integer onExecute(File file, Object data) {
        String remotePath = (String) data;
        HttpURLConnection conn;
        try {
            conn = openConnection(remotePath);
            InputStream inputStream = conn.getInputStream();

            FileOutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[4096];
            int len1 = 0;
            while ((len1 = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len1);
            }

            outputStream.close();
            inputStream.close();

            return SUCCESS;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return FILE_NOT_FOUND;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    private HttpURLConnection openConnection(String url) throws IOException {
        HttpURLConnection connection;
        boolean redirected;
        do {
            connection = (HttpURLConnection) new URL(url).openConnection();
            int code = connection.getResponseCode();
            redirected = code == HTTP_MOVED_PERM || code == HTTP_MOVED_TEMP || code == HTTP_SEE_OTHER;
            if (redirected) {
                url = connection.getHeaderField("Location");
                connection.disconnect();
            }
        } while (redirected);
        return connection;
    }
}
