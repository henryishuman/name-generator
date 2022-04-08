package main;

public class Language {
	private String name;
	private String[] syllables;
	
	public Language(){
		
	}
	
	public Language(String name, String[] syll){
		this.name = name;
		this.syllables = syll;
	}
	
	public String getName(){
		return name;
	}
	public String[] getSyllables(){
		return syllables;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setSyllables(String[] syllables){
		this.syllables = syllables;
	}
	
	public String toString(){
		return name;
	}
}
