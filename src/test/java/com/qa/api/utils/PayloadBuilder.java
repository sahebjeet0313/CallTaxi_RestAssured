package com.qa.api.utils;

public class PayloadBuilder {
	
	    // Method to build XML payload for Add User
    public static String buildUserXML(String aadhaar, String email, String firstName, String lastName, String gender, String phone) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
               "<user>" +
               "<aadhaarNumber>" + aadhaar + "</aadhaarNumber>" +
               "<emailId>" + email + "</emailId>" +
               "<firstName>" + firstName + "</firstName>" +
               "<lastName>" + lastName + "</lastName>" +
               "<gender>" + gender + "</gender>" +
               "<phoneNumber>" + phone + "</phoneNumber>" +
               "</user>";
    }

    // Method to build XML payload for Update User (includes userId)
    public static String buildUserXMLWithId(String userId, String aadhaar, String email, String firstName, String lastName, String gender, String phone) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
               "<user>" +
               "<userId>" + userId + "</userId>" +
               "<aadhaarNumber>" + aadhaar + "</aadhaarNumber>" +
               "<emailId>" + email + "</emailId>" +
               "<firstName>" + firstName + "</firstName>" +
               "<lastName>" + lastName + "</lastName>" +
               "<gender>" + gender + "</gender>" +
               "<phoneNumber>" + phone + "</phoneNumber>" +
               "</user>";
    }

}
