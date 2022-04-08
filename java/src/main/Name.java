package main;

public class Name {
	public String forename;
	public String surname;
	
	public Name(){
		
	}
	
	public Name(String f, String s){
		this.forename = f;
		this.surname = s;
	}
	
	public String toString(){
		return this.forename + " " + this.surname;
	}
}
