package app.jeankn.api.file;

import android.content.Context;

abstract class ReadExternal extends Read {
    public ReadExternal(String path, Context context, OnFinishListener finishListener) {
        super(path, context, finishListener);
    }

    public ReadExternal(String path, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path, context, finishListener, progressListener);
    }


    @Override
    protected Integer doInBackground(Void... voids) {

        int result = onExecute(getExternalFile());
        return result;
    }
}
