package com.pick.security.auth;

import com.pick.entity.User;
import com.pick.repository.UserRepository;
import com.pick.security.bean.RoleConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleConverter roleConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username);

        if (user == null || !user.getDeleteFlag().equals(0)) {
            throw new UsernameNotFoundException("계정 체크 실패");
        }

        // 수직구조의 유저권한 설정
        List<GrantedAuthority> roles = new ArrayList<>();
        Integer roleInt = user.getRole();
        for (int i = 1; i <= roleInt; i++) {
            if (roleConverter.getKeys().contains(i)) {
                String roleStr = roleConverter.convertToString(i);
                roles.add(new SimpleGrantedAuthority(roleStr));
            }
        }
        return new UserDetailsImpl(user, roles);
    }
}
