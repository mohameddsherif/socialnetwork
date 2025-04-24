package com.socialnetwork.ejb.interfaces;

import com.socialnetwork.model.Connection;
import com.socialnetwork.model.User;
import com.socialnetwork.model.Status;

import java.util.List;

public interface ConnectionService {
    
    // Send a friend request
    void sendFriendRequest(User user, User friend);
    
    // Accept a friend request and change the status to ACCEPTED
    void acceptFriendRequest(Connection connection);
    
    // Reject a friend request and change the status to REJECTED
    void rejectFriendRequest(Connection connection);
    
    // Get all pending friend requests for a user
    List<Connection> getPendingRequests(User user);
    
    // Get all friends for a user (connections with ACCEPTED status)
    List<User> getFriends(User user);
    
    // Get the status of a connection (e.g., PENDING, ACCEPTED, REJECTED)
    Status getConnectionStatus(Connection connection);
    
    // Update the status of a connection
    void updateConnectionStatus(Connection connection, Status status);
}
