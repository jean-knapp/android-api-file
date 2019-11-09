package app.jeankn.api.file;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

class ReadTextExternal extends ReadExternal {
    ReadTextExternal(String path, Context context, OnFinishListener finishListener) {
        super(path, context, finishListener);
    }

    ReadTextExternal(String path, Context context, OnFinishListener finishListener, String separator) {
        super(path, context, finishListener, separator);
    }

    ReadTextExternal(String path, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path,context, finishListener, progressListener);
    }

    @Override
    protected Integer onExecute(File file) {
        int READ_BLOCK_SIZE = 128;
        char[] inputBuffer = new char[READ_BLOCK_SIZE];
        StringBuilder stringBuilder = new StringBuilder();

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);

            InputStreamReader reader = new InputStreamReader(inputStream);

            int charRead;
            while ((charRead = reader.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                stringBuilder.append(readString);
            }
            reader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            logError(FILE_NOT_FOUND);
            return FILE_NOT_FOUND;
        } catch (IOException e) {
            logError(ERROR);
            return ERROR;
        }

        String string = stringBuilder.toString();

        this.data = string;

        return SUCCESS;
    }
}
