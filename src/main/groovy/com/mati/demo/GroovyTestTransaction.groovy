package com.mati.demo

import java.util.Date

import org.prevayler.Transaction;

class GroovyTestTransaction implements Transaction {

	
	/*
	 * public List getEmployeesLocatedInUK(List employees) {
  List ret = new ArrayList();
  for(Iterator it = employees.iterator(); it.hasNext(); ) {
    Employee e = (Employee) it.next();
    if(e.getLocation().getCountry().equals("United Kingdom")) {
      ret.add(e.getLocation().getStreet());
    }
  }
  return ret;
}
	 */
	public void executeOn(Object prevalentSystem, Date executionTime) {
		
		employees.location.findAll { it.country == 'United Kingdom' }.street

	}

}
