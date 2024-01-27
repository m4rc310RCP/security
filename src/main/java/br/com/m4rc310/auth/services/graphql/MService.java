package br.com.m4rc310.auth.services.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.m4rc310.graphql.messages.i18n.M;
import br.com.m4rc310.graphql.security.MGraphQLJwtService;
import br.com.m4rc310.graphql.services.MFluxService;

@Configuration
public class MService {
	
	@Autowired
	protected MGraphQLJwtService jwt;
	
	@Autowired
	protected MFluxService flux;
	
	@Autowired
	protected M m;


}
