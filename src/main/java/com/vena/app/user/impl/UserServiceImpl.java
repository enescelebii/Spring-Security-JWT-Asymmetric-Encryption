package com.vena.app.user.impl;


import com.vena.app.exception.BusinessException;
import com.vena.app.exception.ErrorCode;
import com.vena.app.user.User;
import com.vena.app.user.UserMapper;
import com.vena.app.user.UserRepository;
import com.vena.app.user.UserService;
import com.vena.app.user.request.ChangePasswordRequest;
import com.vena.app.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.vena.app.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));
    }

    @Override
    public void updateProfileInfo(ProfileUpdateRequest request, String userId) {
        final User savedUser = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND, userId));
        this.userMapper.mergeUserInfo(savedUser, request);
        this.userRepository.save(savedUser);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, String userId) {
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())){
            throw new BusinessException(CHANGE_PASSWORD_MISMATCH);
        }
        final User savedUser = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND, userId));

        if (!this.passwordEncoder.matches(request.getOldPassword(), savedUser.getPassword())){
            throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);
        }

        final String encoded = this.passwordEncoder.encode(request.getNewPassword());
        savedUser.setPassword(encoded);
        this.userRepository.save(savedUser);
    }

    @Override
    public void deactivateAccount(String userId) {
        final User user = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND, userId));
        if (!user.isEnabled()){
            throw new BusinessException(ACCOUNT_ALREADY_DEACTIVATED);
        }
        user.setEnabled(false);
        this.userRepository.save(user);
    }

    @Override
    public void reactivateAccount(String userId) {
        final User user = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND, userId));
        if (user.isEnabled()){
            throw new BusinessException(ACCOUNT_ALREADY_ACTIVATED);
        }
        user.setEnabled(true);
        this.userRepository.save(user);
    }

    @Override
    public void deleteAccount(String userId) {
        // this method needs the rest of entities to be deleted
        // the logic is just the schedule a profile for deletion
        // and a schedule job will pick the profiles and delete everything
    }

}
