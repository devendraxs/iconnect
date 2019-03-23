package iconnect.psi.com.iconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompleteList {
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<Datum> data = null;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public class Datum {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("emp_code")
        @Expose
        private String empCode;
        @SerializedName("booking_id")
        @Expose
        private Integer bookingId;
        @SerializedName("start_location")
        @Expose
        private String startLocation;
        @SerializedName("end_location")
        @Expose
        private String endLocation;
        @SerializedName("boss_code")
        @Expose
        private String bossCode;
        @SerializedName("feedback")
        @Expose
        private Object feedback;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("travel_completionDate")
        @Expose
        private String travelCompletionDate;
        @SerializedName("approved_by")
        @Expose
        private String approvedBy;
        @SerializedName("approved_date")
        @Expose
        private ApprovedDate approvedDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public Integer getBookingId() {
            return bookingId;
        }

        public void setBookingId(Integer bookingId) {
            this.bookingId = bookingId;
        }

        public String getStartLocation() {
            return startLocation;
        }

        public void setStartLocation(String startLocation) {
            this.startLocation = startLocation;
        }

        public String getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(String endLocation) {
            this.endLocation = endLocation;
        }

        public String getBossCode() {
            return bossCode;
        }

        public void setBossCode(String bossCode) {
            this.bossCode = bossCode;
        }

        public Object getFeedback() {
            return feedback;
        }

        public void setFeedback(Object feedback) {
            this.feedback = feedback;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTravelCompletionDate() {
            return travelCompletionDate;
        }

        public void setTravelCompletionDate(String travelCompletionDate) {
            this.travelCompletionDate = travelCompletionDate;
        }

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }

        public ApprovedDate getApprovedDate() {
            return approvedDate;
        }

        public void setApprovedDate(ApprovedDate approvedDate) {
            this.approvedDate = approvedDate;
        }

    }
    public class ApprovedDate {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("timezone_type")
        @Expose
        private Integer timezoneType;
        @SerializedName("timezone")
        @Expose
        private String timezone;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getTimezoneType() {
            return timezoneType;
        }

        public void setTimezoneType(Integer timezoneType) {
            this.timezoneType = timezoneType;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

    }

}
