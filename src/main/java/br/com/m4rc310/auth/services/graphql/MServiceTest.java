package br.com.m4rc310.auth.services.graphql;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class MServiceTest {
	
	@GraphQLQuery(name = "Test")
	public String test() {
		return "OK";
	}
}
