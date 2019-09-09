package app.jeankn.api.file;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.File;

abstract class WriteExternal extends Write {

    public WriteExternal(String path, Object data, Context context, OnFinishListener finishListener) {
        super(path, data, context, finishListener);
    }

    public WriteExternal(String path, Object data, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path, data, context, finishListener, progressListener);
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        if (isWriteStoragePermissionGranted()) {
            int result = onExecute(getExternalTempFile(), this.data);
            return result;
        } else {
            return PERMISSION_DENIED;
        }
    }

    @Override
    void moveTempToFinalFile() {
        File file = getExternalFile();
        if (file.exists()) {
            file.delete();
        }
        getExternalTempFile().renameTo(file);
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                logError(PERMISSION_DENIED);
                Log.e(getClass().getSimpleName(),"You must request 'Manifest.permission.WRITE_EXTERNAL_STORAGE'");
                return false;
            }
        }
        else {
            return true;
        }
    }

}
