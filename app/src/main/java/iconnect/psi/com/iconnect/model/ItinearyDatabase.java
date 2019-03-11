package iconnect.psi.com.iconnect.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
public class ItinearyDatabase implements Parcelable{
   @PrimaryKey(autoGenerate = true)
   @SerializedName("id_itineary")
   @Expose
   @ColumnInfo(name = "id_itineary")
   private int idItineary;

    public ItinearyDatabase() {
    }

    @SerializedName("same_day_return")
    @Expose
    @ColumnInfo(name = "same_day_return")
    private String same_day_return;

    @SerializedName("start_journey")
    @Expose
    @ColumnInfo(name = "start_journey")
    private String start_journey;

    @SerializedName("end_journey")
    @Expose
    @ColumnInfo(name = "end_journey")
    private String end_journey;

    @SerializedName("project")
    @Expose
    @ColumnInfo(name = "project")
    private String project;

    @SerializedName("date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;


    @SerializedName("destination")
    @Expose
    @ColumnInfo(name = "destination")
    private String destination;

/*
    @SerializedName("facilities")
    @Expose
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte facilities;
*/


    @SerializedName("via")
    @Expose
    @ColumnInfo(name = "via")
    private String via;

    @SerializedName("flag_server")
    @Expose
    @ColumnInfo(name = "flag_server")
    private boolean flag_server;


    protected ItinearyDatabase(Parcel in) {
        idItineary = in.readInt();
        same_day_return = in.readString();
        start_journey = in.readString();
        end_journey = in.readString();
        project = in.readString();
        destination = in.readString();
       // facilities = in.readByte();
        via = in.readString();
        date=in.readString();
        flag_server = in.readByte() != 0;
    }

    public static final Creator<ItinearyDatabase> CREATOR = new Creator<ItinearyDatabase>() {
        @Override
        public ItinearyDatabase createFromParcel(Parcel in) {
            return new ItinearyDatabase(in);
        }

        @Override
        public ItinearyDatabase[] newArray(int size) {
            return new ItinearyDatabase[size];
        }
    };

    public int getIdItineary() {
        return idItineary;
    }

    public void setIdItineary(int idItineary) {
        this.idItineary = idItineary;
    }

    public boolean isFlag_server() {
        return flag_server;
    }

    public void setFlag_server(boolean flag_server) {
        this.flag_server = flag_server;
    }

    public String getSame_day_return() {
        return same_day_return;
    }

    public void setSame_day_return(String same_day_return) {
        this.same_day_return = same_day_return;
    }

    public String getStart_journey() {
        return start_journey;
    }

    public void setStart_journey(String start_journey) {
        this.start_journey = start_journey;
    }

    public String getEnd_journey() {
        return end_journey;
    }

    public void setEnd_journey(String end_journey) {
        this.end_journey = end_journey;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

  /*  public byte getFacilities() {
        return facilities;
    }

    public void setFacilities(byte facilities) {
        this.facilities = facilities;
    }*/

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Ignore
    public ItinearyDatabase(@NonNull String same_day_return, String start_journey, String end_journey, String project, String destination, byte facilities,String date, String via, boolean flag_server) {
        this.same_day_return = same_day_return;
        this.start_journey = start_journey;
        this.end_journey = end_journey;
        this.project = project;
        this.destination = destination;
     //  this.facilities=facilities;
        this.via = via;
        this.flag_server = flag_server;
        this.date=date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idItineary);
        parcel.writeString(same_day_return);
        parcel.writeString(start_journey);
        parcel.writeString(end_journey);
        parcel.writeString(project);
        parcel.writeString(date);
        parcel.writeString(destination);
        parcel.createBinderArray();
        parcel.writeString(via);
        parcel.writeByte((byte) (flag_server ? 1 : 0));
    }
}
