package com.auth.service;

import static com.auth.model.Constants.TOKEN_PREFIX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.config.JwtTokenUtil;
import com.auth.model.User;
import com.auth.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Service(value="userService")
public class UserService  implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	JwtTokenUtil util;

	@Autowired
	UserDetailsService userDetailService;

	public List getAllUsers() {
		
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(user -> users.add(user));
		
		return users;
	}

//	public Boolean validate(String header) {
//		String username;
//		UserDetails userDetails;
//		String authToken = null;
//		if (header != null && header.startsWith(TOKEN_PREFIX)) {
//			authToken = header.replace(TOKEN_PREFIX,"");
//		}
//		try{
//			username = getUser(header);
//			userDetails = userDetailService.loadUserByUsername(username);
//			username = util.getUsernameFromToken(authToken);
//			if(util.validateToken(authToken, userDetails)) {
//				return true;
//			}
//			return false;
//		}
//		catch(Exception e){
//			return false;
//		}
//
//	}
	
	public String getUser(String header) {
		
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                username = util.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                System.err.printf("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
            	System.err.printf("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
            	System.err.println("Authentication Failed. Username or Password not valid.");
            }
        } else {
        	System.err.println("couldn't find bearer string, will ignore the header");
        }
        
		return username;
	}


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}


	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}


	public User findById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

//   
//    public UserRepository update(UserRepository userRepository) {
//        User user = findById(userRepository.getId());
//        if(user != null) {
//            BeanUtils.copyProperties(userRepository, user, "password");
//            userRepository.save(user);
//        }
//        return userRepository;
//    }
//
//   
//    public User save(UserRepository user) {
//	    User newUser = new User();
//	    newUser.setUsername(user.getUsername());
//	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//        return userRepository.save(newUser);
//    }
	
}
