package com.example.demo;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EntityScan( basePackages = {"com.exampl.demo"} )
@EnableAutoConfiguration
public class SistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemiApplication.class, args);
	}
	
	@Bean
	public KieContainer kieContainer() {
	      //return KieServices.Factory.get().getKieClasspathContainer();
		
		  KieServices ks = KieServices.Factory.get();
		  KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		  KieScanner kScanner = ks.newKieScanner(kContainer);
		  kScanner.start(10_000);
		  //KieSession kSession = kContainer.newKieSession();
		  return kContainer;
		 
	  }
}
