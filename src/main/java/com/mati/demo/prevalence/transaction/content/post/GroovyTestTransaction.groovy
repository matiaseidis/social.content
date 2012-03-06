package com.mati.demo.prevalence.transaction.content.post

import java.util.Date

import org.prevayler.Transaction;

class GroovyTestTransaction implements Transaction {

	public void executeOn(Object prevalentSystem, Date executionTime) {
		
		employees.location.findAll { it.country == 'United Kingdom' }.street

	}

}
