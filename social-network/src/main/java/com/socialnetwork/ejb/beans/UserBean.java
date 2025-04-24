package com.socialnetwork.ejb.beans;

import com.socialnetwork.ejb.interfaces.UserService;
import com.socialnetwork.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserBean implements UserService {

    @PersistenceContext(unitName = "social-networkPU")
    private EntityManager entityManager;

    @Override
    public User registerUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(User.Role.USER); 
        entityManager.persist(user);
        return user;
    }

    @Override
    public User loginUser(String email, String password) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                            .setParameter("email", email)
                            .setParameter("password", password)
                            .getSingleResult();
    }

    @Override
    public void updateProfile(Long userId, String name, String bio, String password) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.setName(name);
            user.setBio(bio);
            user.setPassword(password);
            entityManager.merge(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
