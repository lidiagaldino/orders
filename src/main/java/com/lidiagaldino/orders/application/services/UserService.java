package com.lidiagaldino.orders.application.services;

import com.lidiagaldino.orders.application.data.input.UserInputData;
import com.lidiagaldino.orders.application.data.output.UserOutputData;
import io.smallrye.mutiny.Uni;

public interface UserService {
    Uni<UserOutputData> create(UserInputData user);
    Uni<UserOutputData> findById(String id);
}
