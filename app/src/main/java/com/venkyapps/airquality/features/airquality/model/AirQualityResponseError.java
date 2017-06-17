package com.venkyapps.airquality.features.airquality.model;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class AirQualityResponseError {

    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


    public class Error {

        private String message;
        private Integer code;
        private String info;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

    }

}
