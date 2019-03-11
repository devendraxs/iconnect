package iconnect.psi.com.iconnect.database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import iconnect.psi.com.iconnect.model.ItinearyDatabase;

public interface ItinearyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insertItinearyData(ItinearyDatabase... itinearyDatabases);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insertItinearyData(List<ItinearyDatabase> memberDetails);

    @Query("Select * from itinearydatabase where flag_server=0 limit 1")
    List<ItinearyDatabase> getUnUploaded();
}
