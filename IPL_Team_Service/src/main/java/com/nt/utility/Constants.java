package com.nt.utility;

public final class Constants {

    // Status Messages
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String ERROR = "ERROR";

    // CRUD Messages
    public static final String SAVE_SUCCESS = "Record Saved Successfully";
    public static final String UPDATE_SUCCESS = "Record Updated Successfully";
    public static final String DELETE_SUCCESS = "Record Deleted Successfully";

    // User Messages
    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String INVALID_CREDENTIALS = "Invalid Credentials";

    // HTTP Status Codes
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;

    private Constants() {}
    
}