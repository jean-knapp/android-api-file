package app.jeankn.api.file;

import android.content.Context;

class Exists extends IO {

    Exists(String path, Context context) {
        super(path, context);
    }

    public boolean getResult() {
        java.io.File file = getFile();
        return (file.exists());
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return null;
    }
}
