package app.jeankn.api.file;

import android.content.Context;

import java.io.File;

abstract class Write extends IO {

    private OnProgressListener progressListener = null;
    private OnFinishListener finishListener = null;

    Write(String path, Object data, Context context, OnFinishListener finishListener) {
        super(path, context);
        this.finishListener = finishListener;
        this.data = data;

        execute();
    }

    Write(String path, Object data, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path, context);
        this.finishListener = finishListener;
        this.progressListener = progressListener;
        this.data = data;

        execute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        int result = onExecute(getTempFile(), this.data);
        return result;
    }

    protected abstract Integer onExecute(File file, Object data);

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        if (result == SUCCESS) {
            moveTempToFinalFile();
        }

        if (finishListener != null) {
            finishListener.OnFinish(result);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (progressListener != null) {
            progressListener.OnProgress(values[0]);
        }
    }

    void moveTempToFinalFile() {
        File file = getFile();
        if (file.exists()) {
            file.delete();
        }
        getTempFile().renameTo(file);
    }

    public interface OnFinishListener {
        void OnFinish(int status);
    }

    public interface OnProgressListener {
        void OnProgress(int progress);
    }
}
