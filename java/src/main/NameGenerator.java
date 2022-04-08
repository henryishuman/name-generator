package main;
import java.util.ArrayList;

public class NameGenerator {
	private int flen_lo;
	private int flen_hi;
	private int slen_lo;
	private int slen_hi;
	
	private ArrayList<Language> languages;
	private int currentLanguage;
	
	public NameGenerator(){
		languages = new ArrayList<Language>();
		currentLanguage =  -1;
		
		flen_lo = 2;
		flen_hi = 3;
		slen_lo = 2;
		slen_hi = 3;
	}
	
	public void setLanguage(String name){
		currentLanguage = getIndexByName(name);
	}
	public void setForenameLimits(int min, int max){
		flen_lo = min;
		flen_hi = max;
	}
	public void setSurnameLimits(int min, int max){
		slen_lo = min;
		slen_hi = max;
	}
	
	private String[] getEtymologyList(){
		return languages.get(currentLanguage).getSyllables();
	}
	
	public ArrayList<Name> generate(int count){
		ArrayList<Name> names = new ArrayList<Name>();
		
		for(int i = 0; i < count; i++){
			int flen = RandomHelper.randomNumber(flen_lo, flen_hi);
			int slen = RandomHelper.randomNumber(slen_lo, slen_hi);
			names.add(new Name(buildName(flen), buildName(slen)));
		}
		
		return names;
	}
	
	public void addLanguage(Language l){
		languages.add(l);
		currentLanguage = 0;
	}
	
	public ArrayList<Language> getLanguages(){
		return languages;
	}
	
	public void clear(){
		languages = new ArrayList<Language>();
		currentLanguage = -1;
	}
	
	private String buildName(int syllablelength){
		String name = "";
		
		for(int i = 0; i < syllablelength; i++){
			int rnd = RandomHelper.randomNumber(0, getEtymologyList().length - 1);
			name += getEtymologyList()[rnd];
		}
		
		name = name.toUpperCase().charAt(0) + name.substring(1);
		return name;
	}
	
	private int getIndexByName(String name){
		for(int i = 0; i < languages.size(); i++)
			if(languages.get(i).getName().equals(name))
				return i;
		
		return -1;
	}
}
