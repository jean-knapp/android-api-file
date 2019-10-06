package app.jeankn.api.file;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class ReadCSVExternal extends ReadTextExternal {
    private String separator;

    ReadCSVExternal(String path, Context context, String separator, OnFinishListener finishListener) {
        super(path, context, finishListener, separator);
    }

    ReadCSVExternal(String path, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path,context, finishListener, progressListener);
    }

    @Override
    protected Integer onExecute(File file) {
        int result = super.onExecute(file);

        if (result != SUCCESS)
            return result;

        ArrayList<ArrayList<String>> items = new ArrayList<>();

        for (String line : ((String) data).split("\r\n")) {
            ArrayList<String> values = new ArrayList<>(Arrays.asList(line.split(separator)));
            items.add(values);
        }

        data = items;

        return SUCCESS;
    }
}
