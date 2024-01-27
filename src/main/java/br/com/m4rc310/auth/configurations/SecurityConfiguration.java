package br.com.m4rc310.auth.configurations;

import java.util.Objects;

import org.springframework.context.annotation.Configuration;

import br.com.m4rc310.graphql.exceptions.MException;
import br.com.m4rc310.graphql.messages.i18n.M;
import br.com.m4rc310.graphql.security.IMAuthUserProvider;
import br.com.m4rc310.graphql.security.MAuthToken;
import br.com.m4rc310.graphql.security.MEnumToken;
import br.com.m4rc310.graphql.security.MGraphQLJwtService;
import br.com.m4rc310.graphql.security.dto.MUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityConfiguration implements IMAuthUserProvider  {

	private M m;

	@Override
	public MUser authUser(String username, Object password) throws Exception {
		log.info("AuthUser=>{}", username);
		return null;
	}

	@Override
	public MUser getUserFromUsername(String username) {
		
		MUser user = new MUser();
		user.setCode(270881L);
		user.setPassword("Escol@1979");
		user.setUsername("mlsilva");
		user.setRoles(new String[] {"ADMIN"});
		
		return user;
	}

	@Override
	public MUser loadUser(MGraphQLJwtService jwt, MEnumToken type, String token) throws MException {
		
		log.info("-> {} {}", type, token);
		
		switch (type) {
			case TEST:
				int indexDivisor = token.indexOf(":");
				String username = token.substring(0, indexDivisor);
				String password = token.substring(indexDivisor + 1);
				
				MUser user = getUserFromUsername(username);
				if (Objects.nonNull(user) && Objects.equals(password, user.getCode()+"")) {
					return user;
				}			
			case BEARER: 
				username = jwt.extractUsername(token);
				user = getUserFromUsername(username);
				if (!isValidUser(user)) {
					throw getWebException(402,  "error.access.unauthorized");
				}
				return user;
			case BASIC:
				default:
				throw getWebException(401, "error.access.unauthorized");
		}
		
	}

	private MException getWebException(int code, String message, Object... args) {
		if (m != null) {
			message = m.getString(message, args);			
		}
		return MException.get(code, message);
	} 
	
	@Override
	public boolean isValidUser(MUser user) {
		return true;
	}

	@Override
	public void setMessage(M m) {
		this.m = m;
	}

	@Override
	public void validUserAccess(MAuthToken authToken, String[] roles) throws MException {
		log.info("validate	");
	}
}
