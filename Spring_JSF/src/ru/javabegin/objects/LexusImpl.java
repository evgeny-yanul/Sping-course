package ru.javabegin.objects;

import org.springframework.stereotype.Component;

@Component
public class LexusImpl implements CarInterface {
	
	private String sound = "drrrr...!!!";
	
	@Override
	public String getSound() {
		return sound;
	}

}
