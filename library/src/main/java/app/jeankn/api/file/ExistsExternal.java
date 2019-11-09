package app.jeankn.api.file;

import android.content.Context;

class ExistsExternal extends Exists {

    ExistsExternal(String path, Context context) {
        super(path, context);
    }

    public boolean getResult() {
        java.io.File file = getExternalFile();
        return (file.exists());
    }
}
