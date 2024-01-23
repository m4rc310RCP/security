package br.com.m4rc310.auth.services.graphql;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.m4rc310.graphql.security.MGraphQLJwtService;
import br.com.m4rc310.graphql.security.dto.MUser;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class MServiceTest {
	
	@Autowired
	private MGraphQLJwtService jwt;
	
	@GraphQLQuery(name = "Test")
	public String test() {
		MUser user = new MUser();
		user.setUsername("mlsilva");
		user.setPassword("Escol@1979");
		user.setRoles("ADMIN;USER;SYSTEM".split(";"));
		return jwt.generateToken(user);
	}
	
	@GraphQLQuery(name = "${query.username.token}")
	public String usernameFromToken(String token) {
		return jwt.extractUsername(token);
	}
	
	@GraphQLQuery(name = "validate")
	public boolean validateToken(String token) throws Exception {
		
		jwt.validateToken(token);
		
		return true;
	}
	
	
	@GraphQLMutation(name = "${mutation.message.test}")
	public String testMutation() throws Exception {
		throw new UnsupportedOperationException("Test");
	}
	
	@GraphQLSubscription(name = "${subscription.teste}")
	public Publisher<String> testSubscription() {
		throw new UnsupportedOperationException("Test error");		
	}
	
	
	
}
