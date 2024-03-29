package com.example.nagoyameshi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
private final UserRepository userRepository;
private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;

public UserService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
}
 @Transactional
 public User create(SignupForm signupForm) {
	 User user = new User();
	 Role role = roleRepository.findByName("ROLE_GENERAL");
	 
	 user.setName(signupForm.getName());
     user.setFurigana(signupForm.getFurigana());
     user.setPostalCode(signupForm.getPostalCode());
     user.setAddress(signupForm.getAddress());
     user.setPhoneNumber(signupForm.getPhoneNumber());
     user.setBirthmonth(signupForm.getBirthmonth());
     user.setBirthday(signupForm.getBirthday());
     user.setGender(signupForm.getGender());
     user.setEmail(signupForm.getEmail());
     user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
     user.setRole(role);
     user.setEnabled(false);
     
     return userRepository.save(user);
 }
 
 @Transactional
 public void update(UserEditForm userEditForm) {
	 User user = userRepository.getReferenceById(userEditForm.getId());
	 
	 user.setName(userEditForm.getName());
     user.setFurigana(userEditForm.getFurigana());
     user.setPostalCode(userEditForm.getPostalCode());
     user.setAddress(userEditForm.getAddress());
     user.setPhoneNumber(userEditForm.getPhoneNumber());
     user.setEmail(userEditForm.getEmail());
     

     
     userRepository.save(user);
 }
 public boolean isEmailRegistered(String email) {
	 User user = userRepository.findByEmail(email);  
     return user != null;
 }
 public boolean isSamePassword(String password,String passwordConfirmation) {
	 return password.equals(passwordConfirmation);
 }
 
//ユーザーを有効にする
 @Transactional
 public void enableUser(User user) {
     user.setEnabled(true); 
     userRepository.save(user);
 }
 
 public boolean isEmailChanged(UserEditForm userEditForm) {
     User currentUser = userRepository.getReferenceById(userEditForm.getId());
     return !userEditForm.getEmail().equals(currentUser.getEmail());      
 } 
 
 
 
 @Transactional
 public void updateRole(User user, String roleName) {
     Role role = roleRepository.findByName(roleName);
     user.setRole(role);
     userRepository.save(user);
 }
 
 

 public void refreshAuthenticationByRole(String newRole) {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
     authorities.add(new SimpleGrantedAuthority(newRole));
     Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);

     SecurityContextHolder.getContext().setAuthentication(newAuth);
 }
 
 public int getGeneralUserCount() {
     List<User> generalUsers = userRepository.findByRoleName("ROLE_GENERAL");
     return generalUsers.size();
 }
 
 public int getPremiumUserCount() {
     List<User> premiumUsers = userRepository.findByRoleName("ROLE_PREMIUM");
     return premiumUsers.size();
 }
 
 public List<User> getManUsers() {
     return userRepository.findByGender("男性");
 }

 public int getManUserCount() {
     return userRepository.countByGender("男性");
 }
 public List<User> getWomanUsers() {
     return userRepository.findByGender("女性");
 }

 public int getWomanUserCount() {
     return userRepository.countByGender("女性");
 }
 
}
