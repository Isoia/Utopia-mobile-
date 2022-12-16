 // 로그인 및 회원가입
let viewOn = false;

const loginElem = document.body.querySelector("#login-btn");
const loginModalConElem = document.body.querySelector(".login-modal-container");
const registerElem = document.body.querySelector("#register-btn");
const registerModalConElem = document.body.querySelector(".register-modal-container");

//login modal
loginElem.addEventListener("click", _ => {
	if(viewOn === true) {
		registerModalConElem.classList.add("invisible");
	}
	
	loginModalConElem.classList.remove("invisible");
	viewOn = true;
});
  	
loginModalConElem.addEventListener("click", e => {
    if (e.target === loginModalConElem) {
		loginModalConElem.classList.add("invisible");
		viewOn = false;
	}
});

//register modal
registerElem.addEventListener("click", _ => {
	if(viewOn === true) {
		loginModalConElem.classList.add("invisible");
	}
	
	registerModalConElem.classList.remove("invisible");
	viewOn = true;
});

registerModalConElem.addEventListener("click", e => {
    if (e.target === registerModalConElem) {
		registerModalConElem.classList.add("invisible");
		viewOn = false;
	}
});