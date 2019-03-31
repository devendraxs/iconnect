package iconnect.psi.com.iconnect.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("break_fast")
        @Expose
        private String breakFast;
        @SerializedName("lunch")
        @Expose
        private String lunch;
        @SerializedName("dinner")
        @Expose
        private String dinner;
        @SerializedName("nones")
        @Expose
        private String nones;
        @SerializedName("incedental")
        @Expose
        private String incedental;
        @SerializedName("estimated_perdiam")
        @Expose
        private String estimatedPerdiam;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBreakFast() {
            return breakFast;
        }

        public void setBreakFast(String breakFast) {
            this.breakFast = breakFast;
        }

        public String getLunch() {
            return lunch;
        }

        public void setLunch(String lunch) {
            this.lunch = lunch;
        }

        public String getDinner() {
            return dinner;
        }

        public void setDinner(String dinner) {
            this.dinner = dinner;
        }

        public String getNones() {
            return nones;
        }

        public void setNones(String nones) {
            this.nones = nones;
        }

        public String getIncedental() {
            return incedental;
        }

        public void setIncedental(String incedental) {
            this.incedental = incedental;
        }

        public String getEstimatedPerdiam() {
            return estimatedPerdiam;
        }

        public void setEstimatedPerdiam(String estimatedPerdiam) {
            this.estimatedPerdiam = estimatedPerdiam;
        }

    }

