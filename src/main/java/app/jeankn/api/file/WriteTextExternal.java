package app.jeankn.api.file;


import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class WriteTextExternal extends WriteExternal {

    WriteTextExternal(String path, String data, Context context, OnFinishListener finishListener) {
        super(path, data, context, finishListener);
    }

    WriteTextExternal(String path, String data, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path, data, context, finishListener, progressListener);
    }

    @Override
    protected Integer onExecute(File file, Object data) {
        String string = (String) data;

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            logError(FILE_NOT_FOUND);
            return FILE_NOT_FOUND;
        } catch (IOException e) {
            logError(ERROR);
            return ERROR;
        }

        return SUCCESS;
    }
}
