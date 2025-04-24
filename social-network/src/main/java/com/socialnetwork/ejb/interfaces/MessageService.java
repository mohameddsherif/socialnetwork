package com.socialnetwork.ejb.interfaces;

import java.util.List;

import com.socialnetwork.model.Message;

public interface MessageService {
    
    // Send a message
    void sendMessage(Message message);
    
    // Receive messages for a user
    void receiveMessages(Long userId);
    
    // Delete a message
    void deleteMessage(Long messageId);
    
    // Get all messages for a user
    List<Message> getMessagesForUser(Long userId);
}
