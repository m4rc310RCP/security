package br.com.m4rc310.auth.services.graphql;

import java.util.Date;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import br.com.m4rc310.graphql.mappers.annotations.MDate;
import br.com.m4rc310.graphql.security.dto.MUser;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class MServiceTest extends MService {
	
	
	@GraphQLQuery(name = "Test")
	public String test() {
		MUser user = new MUser();
		user.setUsername("mlsilva");
		user.setPassword("Escol@1979");
		user.setRoles("ADMIN;USER;SYSTEM".split(";"));
		user.setUsername(m.getString("${error.message.test}"));
		
		
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
		
		MUser u = new MUser();
		u.setUsername("Test 2");
	 	
		flux.callPublish("test", u);

		return u.getUsername();
	}
	
	@GraphQLSubscription(name = "${subscription.teste}")
	public Publisher<MUser> testSubscription() {
		
		MUser u = new MUser();
		u.setUsername("MLS");
		
		return flux.publish(MUser.class, "test", u);
	}
	
	@MDate
	@GraphQLQuery(name = "dt_expiration")
	public Date getDateExpiration(@GraphQLContext MUser user) {
		return new Date();
	}
	
	
	
}
