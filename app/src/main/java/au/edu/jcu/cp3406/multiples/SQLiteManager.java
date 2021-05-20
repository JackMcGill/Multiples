package au.edu.jcu.cp3406.multiples;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;

    private static final String DATABASE_NAME = "MultiplesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Multiples";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String NAME = "name";
    private static final String SCORE = "score";

    public SQLiteManager(Context context) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
