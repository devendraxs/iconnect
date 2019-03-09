/*
package iconnect.psi.com.iconnect.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ItinearyConverter {
    @TypeConverter // note this annotation
    public String fromOptionValuesList(MemberDetails.HohDetails optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<MemberDetails.HohDetails>() {
        }.getType();
        String json = gson.toJson(optionValues, type);

        return json;
    }
    @TypeConverter // note this annotation
    public MemberDetails.HohDetails toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<MemberDetails.HohDetails>() {
        }.getType();
        MemberDetails.HohDetails productCategoriesList = gson.fromJson(optionValuesString, type);

        return productCategoriesList;
    }

}
*/
