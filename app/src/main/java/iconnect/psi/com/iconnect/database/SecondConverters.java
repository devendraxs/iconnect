/*
package iconnect.psi.com.iconnect.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import iconnect.psi.com.iconnect.model.ItinearyDatabase;

public class SecondConverters {
    @TypeConverter // note this annotation
    public String fromOptionValues(List<ItinearyDatabase> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ItinearyDatabase>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);

        return json;
    }
    @TypeConverter // note this annotation
    public List<ItinearyDatabase> toOptionValues(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ItinearyDatabase>>() {
        }.getType();
        List<ItinearyDatabase> productCategoriesList = gson.fromJson(optionValuesString, type);

        return productCategoriesList;
    }

}
*/
