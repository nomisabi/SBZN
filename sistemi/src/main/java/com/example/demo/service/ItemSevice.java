package com.example.demo.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;


@Service
public class ItemSevice {

	@Autowired
	private ItemRepository itemRep;
	
	//@Autowired
	//private KieContainer kieContainer;
	
    public Item getClassifiedItem(Item i) {
   /*   KieSession kieSession = kieContainer.newKieSession("ExampleSession");
      kieSession.insert(i);
      kieSession.fireAllRules();
      kieSession.dispose();
      i=save(i);
      return i;*/
    	return null;
  }
    
    public Item save(Item i) {
    	return itemRep.save(i);
    }
}
