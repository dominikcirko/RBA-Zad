package com.example.RBAZadatak.Service;

import com.example.RBAZadatak.Entity.User;

public interface UserService {

    User createUser(User user);

    User findByOib(String oib);

    void deleteByOib(String oib);

}
