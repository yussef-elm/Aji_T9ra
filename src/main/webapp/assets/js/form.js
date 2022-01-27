const form = document.getElementById('form');
const username = document.getElementById('username');
const firstname = document.getElementById('firstname');
const lastname = document.getElementById('lastname');
const email = document.getElementById('email');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');

form.addEventListener('submit', e => {
	e.preventDefault();

	if(checkInputs()){
			e.currentTarget.submit();
	}
});


function checkInputs() {
	// trim to remove the whitespaces
	const usernameValue = username.value.trim();
	const firstnameValue = firstname.value.trim();
	const lastnameValue = lastname.value.trim();
	const emailValue = email.value.trim();
	const passwordValue = password.value.trim();
	const password2Value = password2.value.trim();
	const p=true;

	if (usernameValue === '') {
		setErrorFor(username, 'Username cannot be blank');
		p=false;
	} else {
		setSuccessFor(username);
	}
	
	if(firstnameValue === '') {
		setErrorFor(firstname, 'Prénom non saisi');
				p=false;

	}else {
		setSuccessFor(firstname);
	}
	
	if(lastnameValue === '') {
		setErrorFor(lastname, 'Nom non saisi');
				p=false;

	} else {
		setSuccessFor(lastname);
	}

	if (emailValue === '') {
		setErrorFor(email, 'Email cannot be blank');
				p=false;

	} else if (!isEmail(emailValue)) {
		setErrorFor(email, 'Not a valid email');
				p=false;

	} else {
		setSuccessFor(email);
	}

	if (passwordValue === '') {
		setErrorFor(password, 'Password cannot be blank');		p=false;

	} else if (passTest(password)) {
		setErrorFor(password, 'En moins 8 caractéres avec un chiffre et une majiscule');		p=false;

	} else {
		setSuccessFor(password);
	}

	if (password2Value === '') {
		setErrorFor(password2, 'Password2 cannot be blank');		p=false;

	} else if (passwordValue !== password2Value) {
		setErrorFor(password2, 'Passwords does not match');		p=false;

	} else {
		setSuccessFor(password2);
	}
 return p;
}

function setErrorFor(input, message) {
	const formControl = input.parentElement;
	const small = formControl.querySelector('small');
	formControl.className = 'form-control error';
	small.innerText = message;
}

function setSuccessFor(input) {
	const formControl = input.parentElement;
	formControl.className = 'form-control success';
}

function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

function passTest(pass) {
	return /^(?=.*\d)(?=.*[A-Z])(?=.*[a-z]){8,16}$/.test(pass);
}











// SOCIAL PANEL JS
const floating_btn = document.querySelector('.floating-btn');
const close_btn = document.querySelector('.close-btn');
const social_panel_container = document.querySelector('.social-panel-container');

floating_btn.addEventListener('click', () => {
	social_panel_container.classList.toggle('visible')
});

close_btn.addEventListener('click', () => {
	social_panel_container.classList.remove('visible')
});