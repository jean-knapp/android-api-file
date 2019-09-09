package app.jeankn.api.file;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;

abstract class IO extends AsyncTask<Void, Integer, Integer> {
    public static final int ERROR = 0;
    public static final  int SUCCESS = 1;
    public static final int FILE_NOT_FOUND = 2;
    public static final int PERMISSION_DENIED = 4;

    private String path;

    protected Context context;
    protected Object data;

    public IO(String path, Context context) {
        this.path = path;
        this.context = context;
    }

    File getFile() {
        File file = new File(this.path);
        String directoryString = file.getParent();
        String fileName = file.getName();

        if (directoryString == null) {
            directoryString = context.getFilesDir().getAbsolutePath();
        } else {
            directoryString = context.getFilesDir().getAbsolutePath() + File.separator + directoryString;
        }

        File directory = new File(directoryString);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        file = new File(directory, fileName);
        return file;
    }

    protected File getExternalFile() {
        File root = android.os.Environment.getExternalStorageDirectory();

        File file = new File(path);
        String directoryString = file.getParent();

        if (directoryString == null) {
            directoryString = root.getAbsolutePath();
        } else {
            directoryString = root.getAbsolutePath() + File.separator + directoryString;
        }

        String fileName = file.getName();

        File directory = new File(directoryString);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        file = new File(directory, fileName);
        return file;
    }

    private File getTempFile(File file) {
        if (file.getParent() == null) {
            return new File(file.getName() + ".temp");
        } else {
            return new File(file.getParent(), file.getName() + ".temp");
        }
    }

    protected File getTempFile() {
        return getTempFile(getFile());
    }

    protected File getExternalTempFile() {
        return getTempFile(getExternalFile());
    }

    protected void logError(int error) {
        switch (error) {
            case FILE_NOT_FOUND:
                Log.e(getClass().getSimpleName(), "FILE NOT FOUND (" + path + ")");
                break;
            case PERMISSION_DENIED:
                Log.e(getClass().getSimpleName(), "PERMISSION DENIED (" + path + ")");
                break;
            case ERROR:
            default:
                Log.e(getClass().getSimpleName(), "ERROR (" + path + ")");
                break;
        }
    }
}
