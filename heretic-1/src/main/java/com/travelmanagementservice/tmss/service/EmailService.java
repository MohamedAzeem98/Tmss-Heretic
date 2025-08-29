package com.travelmanagementservice.tmss.service;

public interface EmailService {
	void sendEmailMessage(String to,String subject,String text);
}
