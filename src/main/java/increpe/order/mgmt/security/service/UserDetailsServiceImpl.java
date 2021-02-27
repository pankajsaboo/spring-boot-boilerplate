package increpe.order.mgmt.security.service;

import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import increpe.order.mgmt.model.Roles;
import increpe.order.mgmt.security.dto.AuthenticatedUserDto;
import increpe.order.mgmt.security.dto.RolesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) {

		final AuthenticatedUserDto authenticatedUser = userService.findAuthenticatedUserByUsername(username);

		if (Objects.isNull(authenticatedUser)) {
			throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
		}

		final String authenticatedUsername = authenticatedUser.getUsername();
		final String authenticatedPassword = authenticatedUser.getPassword();
		final RolesDto userRole = authenticatedUser.getUserRole();
		final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getTitle());

		return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
	}
}
