package app.jeankn.api.file;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadText extends Read {
    public ReadText(String path, Context context, ReadText.OnFinishListener finishListener) {
        super(path, context, finishListener);
    }

    public ReadText(String path, Context context, ReadText.OnFinishListener finishListener, ReadText.OnProgressListener progressListener) {
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
