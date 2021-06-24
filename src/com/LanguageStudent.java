package com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageStudent {
	
	public static List<String> knownLanguages =new ArrayList<String>();
	
	
	public Collection<String> getlanguages(){
		
		return knownLanguages;
		
	}
	
	public void addlanguages(String language) {
		
		knownLanguages.add(language);
	}

}
