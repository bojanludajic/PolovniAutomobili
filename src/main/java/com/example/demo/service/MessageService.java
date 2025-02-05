package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.MessageRepository;

import model.Message;

@Service
public class MessageService {
	
	final MessageRepository mr;

	public MessageService(MessageRepository mr) {
		this.mr = mr;
	}

	public void newMessage(Integer idSender, Integer idReceiver, String text) {
		Message m = new Message();
		m.setIdSender(idSender);
		m.setIdReceiver(idReceiver);
		m.setContent(text);
		
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		m.setTimestamp(date);
		
		mr.save(m);
	}
	
	public List<Message> findAll() {
		List<Message> messages = mr.findAll();
		messages.sort(Comparator.comparing(Message::getTimestamp).reversed());
		
		return messages;
	}
	
	public void deleteMessage(Integer id) {
		mr.deleteById(id);
	}
	
}
