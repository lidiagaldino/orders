package com.lidiagaldino.orders.application.services;

import com.lidiagaldino.orders.application.data.input.AuthInputData;
import com.lidiagaldino.orders.application.data.output.AuthOutputData;
import io.smallrye.mutiny.Uni;

public interface SessionService {
    Uni<AuthOutputData> login(AuthInputData data);
}