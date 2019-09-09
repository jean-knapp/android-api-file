package app.jeankn.api.file;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadCSV extends ReadText {
    public ReadCSV(String path, Context context, ReadText.OnFinishListener finishListener) {
        super(path, context, finishListener);
    }

    public ReadCSV(String path, Context context, ReadText.OnFinishListener finishListener, ReadText.OnProgressListener progressListener) {
        super(path,context, finishListener, progressListener);
    }

    @Override
    protected Integer onExecute(File file) {
        int result = super.onExecute(file);

        if (result != SUCCESS)
            return result;

        ArrayList<ArrayList<String>> items = new ArrayList<>();

        for (String line : ((String) data).split("\r\n")) {
            ArrayList<String> values = new ArrayList<>(Arrays.asList(line.split(",")));
            items.add(values);
        }

        data = items;

        return SUCCESS;
    }
}
