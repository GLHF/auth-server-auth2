package com.craftsmengroup.paymentsystem.authserver.secure.details;

import com.craftsmengroup.paymentsystem.authserver.model.User;
import com.craftsmengroup.paymentsystem.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

   @Override
   public SecureUser loadUserByUsername(final String email) throws UsernameNotFoundException {
      try {
         User userEntity = userRepository.findByEmail(email)
                 .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
         return new SecureUser(userEntity);
      } catch (Exception e) {
         throw new UsernameNotFoundException(String.format("User with email %s not found", email));
      }
   }
}