package com.socialnetwork.ejb.interfaces;

import com.socialnetwork.model.User;

public interface UserService {
    User registerUser(String name, String email, String password);
    User loginUser(String email, String password);
    void updateProfile(Long userId, String name, String bio, String password);
    User getUserById(Long id);
}
