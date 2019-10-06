package app.jeankn.api.file;

import android.content.Context;

import java.io.File;

abstract class Read extends IO {

    private OnProgressListener progressListener = null;
    private OnFinishListener finishListener = null;

    Read(String path, Context context, OnFinishListener finishListener) {
        super(path, context);
        this.finishListener = finishListener;

        execute();
    }

    Read(String path, Context context, OnFinishListener finishListener, String separator) {
        super(path, context);
        this.finishListener = finishListener;
        this.separator = separator;

        execute();
    }

    Read(String path, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path, context);
        this.finishListener = finishListener;
        this.progressListener = progressListener;

        execute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        int result = onExecute(getFile());
        return result;
    }

    protected abstract Integer onExecute(File file);

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        if (finishListener != null) {
            finishListener.OnFinish(result, data);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (progressListener != null) {
            progressListener.OnProgress(values[0]);
        }
    }

    public interface OnFinishListener {
        void OnFinish(int status, Object data);
    }

    public interface OnProgressListener {
        void OnProgress(int progress);
    }
}
