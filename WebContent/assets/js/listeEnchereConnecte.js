document.getElementById("ventes").addEventListener('click', event => {
	document.getElementById("ventesencours").disabled = false;
	document.getElementById("ventesnondebutees").disabled = false;
	document.getElementById("ventesterminees").disabled = false;
	
	document.getElementById("encheresouvertes").disabled = true;
	document.getElementById("encheresencours").disabled = true;
	document.getElementById("encheresremportees").disabled = true;
	
	document.getElementById("encheresouvertes").checked = false;
	document.getElementById("encheresencours").checked = false;
	document.getElementById("encheresremportees").checked = false;
});

document.getElementById("achats").addEventListener('click', event => {
	document.getElementById("ventesencours").disabled = true;
	document.getElementById("ventesnondebutees").disabled = true;
	document.getElementById("ventesterminees").disabled = true;
	
	document.getElementById("encheresouvertes").disabled = false;
	document.getElementById("encheresencours").disabled = false;
	document.getElementById("encheresremportees").disabled = false;
	
	document.getElementById("ventesencours").checked = false;
	document.getElementById("ventesnondebutees").checked = false;
	document.getElementById("ventesterminees").checked = false;
});