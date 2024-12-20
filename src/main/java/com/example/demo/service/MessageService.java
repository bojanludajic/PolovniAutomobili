package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MessageRepository;

import model.Message;

@Service
public class MessageService {
	
	@Autowired
	MessageRepository mr;

	public void newMessage(Integer idSender, Integer idReceiver, String text) {
		Message m = new Message();
		m.setIdSender(idSender);
		m.setIdReceiver(idReceiver);
		m.setContent(text);
		m.setTimestamp(null);
		
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		m.setTimestamp(date);
		
		mr.save(m);
	}
	
}
