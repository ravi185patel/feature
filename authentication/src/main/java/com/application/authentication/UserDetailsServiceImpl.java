package com.application.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("in method call");
//        Users users=usersDao.find(userName);
//        if(users==null){
//            users=new Users();
//            users.setUserName("ravidpatel");
//            users.setPassword("ravi1990");
//        }
//        System.out.println(users.getUserName()+" "+users.getPassword());
////        users.getRoles().forEach(s->System.out.println(s.getRoleName()));
//        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));//usersRepository.findByUserName(userName).get();
        List<GrantedAuthority> authorities = getUserAuthority(null);

        return new org.springframework.security.core.userdetails.User("ravi", bCryptPasswordEncoder.encode("ravi"),
                true, true, true, true, authorities);
//        System.out.println(users.getUserName()+" "+users.getPassword());

    }

    private List<GrantedAuthority> getUserAuthority(String userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
//        if(userRoles.isEmpty()){
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }else{
//            for (Role role : userRoles) {
//                roles.add(new SimpleGrantedAuthority(role.getRoleName()));
//            }
//        }
        roles.forEach(s->System.out.println(s.getAuthority()));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }
}
