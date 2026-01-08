package com.vena.app.auth;


import com.vena.app.auth.request.AuthenticationRequest;
import com.vena.app.auth.request.RefreshRequest;
import com.vena.app.auth.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest request);
}
