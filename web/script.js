function pageload(){
	var home = document.getElementById("home");
	var name = document.getElementById("name");
	var contact = document.getElementById("contact");
	
	name.style.display = "none";
	contact.style.display = "none";
}

function goTo(page){
	var home = document.getElementById("home");
	var name = document.getElementById("name");
	var contact = document.getElementById("contact");
	
	if(page == "Home"){
		home.style.display = "block";
		name.style.display = "none";
		contact.style.display = "none";
	}
	else if(page == "Language"){
		home.style.display = "none";
		name.style.display = "block";
		contact.style.display = "none";
	}
	else if(page == "Contact"){
		home.style.display = "none";
		name.style.display = "none";
		contact.style.display = "block";
	}
}

function generate(language){
	var string = "";
	var charset, pron;
	var isReverse = false;
	if(language == "Chinese"){
		charset = chinese_charset;
		pron = chinese_pron;
	}
	else if(language == "Russian"){
		charset = russian_charset;
		pron = russian_pron;
	}
	else if(language == "Amharic"){
		charset = amharic_charset;
		pron = amharic_pron;
	}
	else if(language == "Greek"){
		charset = greek_charset;
		pron = greek_pron;
	}
	else if(language == "Hebrew"){
		charset = hebrew_charset;
		pron = hebrew_pron;
		isReverse = true;
	}
	else if(language == "Japanese"){
		charset = japanese_charset;
		pron = japanese_pron;
	}
	else if(language == "Latin"){
		charset = latin_charset;
		pron = latin_pron;
	}
	
	for(var count = 0; count < 5; count++){
		var char_string = ""
		var pron_string = "";
		var syllablelength = Math.floor((Math.random() * 4) + 2)
		for(var i = 0; i < syllablelength; i++){
			var rnd = Math.floor((Math.random() * charset.length));
			if(isReverse){
				char_string = charset[rnd] + char_string;
				pron_string = pron[rnd] + pron_string;
			}
			else {
				char_string += charset[rnd];
				pron_string += pron[rnd];
			}
			if(i < syllablelength - 1)
				if(isReverse)	
					pron_string = "-" + pron_string;
				else
					pron_string += "-";
			else
				pron_string = "(" + pron_string + ")";
		}
		string += "<div class='charset'>" + char_string + "</div>";
		string += "<div class='pron'>" + pron_string + "</div><br/>";
	}
	
	var txtName = document.getElementById("name");
	txtName.innerHTML = string;
	
	goTo("Language");
}
