package iconnect.psi.com.iconnect.database;

        import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import iconnect.psi.com.iconnect.model.ItinearyDatabase;


@Database(entities = {ItinearyDatabase.class }, version = 2,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
}
