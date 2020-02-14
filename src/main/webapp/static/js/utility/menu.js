 changeSelection = function(action){
	 var activeTab = document.getElementsByClassName("active")[0];
	 var act = activeTab.classList;
	 act.remove('active');
 	 activeTab.className = act;
	 var newActiveTab = document.getElementsByClassName(action)[0];
	 
	 var newActive = newActiveTab.classList;
	 newActive.add('active');
	 newActiveTab.className = newActive;
 };