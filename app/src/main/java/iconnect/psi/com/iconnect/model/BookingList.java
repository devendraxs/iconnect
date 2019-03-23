package iconnect.psi.com.iconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingList {
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<Datum> data;

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

    public class Datum
    {
        @SerializedName("booking_id")
        private Integer bookingId;

        @SerializedName("emp_id")
        private String empId;

        @SerializedName("name")
        private String name;

        @SerializedName("same_day")
        private String sameDay;

        @SerializedName("start_location")
        private String startLocation;

        @SerializedName("end_location")
        private String endLocation;

        @SerializedName("start_date")
        private String startDate;

        @SerializedName("end_date")
        private String endDate;

        @SerializedName("purpose")
        private String purpose;

        @SerializedName("attach_doc")
        private String attachDoc;

        @SerializedName("travel_type")
        private String travelType;

        @SerializedName("total_night")
        private String totalNight;

        @SerializedName("leaving_HQ")
        private String leavingHQ;

        @SerializedName("reaching_HQ")
        private Object reachingHQ;

        @SerializedName("superior_comment")
        private Object superiorComment;

        @SerializedName("submit_status")
        private Object submitStatus;

        @SerializedName("approval_status")
        private Object approvalStatus;

        @SerializedName("created_date")
        private Object createdDate;

        @SerializedName("updated_date")
        private Object updatedDate;

        public Integer getBookingId() {
            return bookingId;
        }

        public void setBookingId(Integer bookingId) {
            this.bookingId = bookingId;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSameDay() {
            return sameDay;
        }

        public void setSameDay(String sameDay) {
            this.sameDay = sameDay;
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

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getAttachDoc() {
            return attachDoc;
        }

        public void setAttachDoc(String attachDoc) {
            this.attachDoc = attachDoc;
        }

        public String getTravelType() {
            return travelType;
        }

        public void setTravelType(String travelType) {
            this.travelType = travelType;
        }

        public String getTotalNight() {
            return totalNight;
        }

        public void setTotalNight(String totalNight) {
            this.totalNight = totalNight;
        }

        public String getLeavingHQ() {
            return leavingHQ;
        }

        public void setLeavingHQ(String leavingHQ) {
            this.leavingHQ = leavingHQ;
        }

        public Object getReachingHQ() {
            return reachingHQ;
        }

        public void setReachingHQ(Object reachingHQ) {
            this.reachingHQ = reachingHQ;
        }

        public Object getSuperiorComment() {
            return superiorComment;
        }

        public void setSuperiorComment(Object superiorComment) {
            this.superiorComment = superiorComment;
        }

        public Object getSubmitStatus() {
            return submitStatus;
        }

        public void setSubmitStatus(Object submitStatus) {
            this.submitStatus = submitStatus;
        }

        public Object getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(Object approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
            this.createdDate = createdDate;
        }

        public Object getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(Object updatedDate) {
            this.updatedDate = updatedDate;
        }

    }

}
