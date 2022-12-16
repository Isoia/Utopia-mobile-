
document.body.querySelector(".info-grid").addEventListener("submit", e => {
	const pw = document.body.querySelector("#pw").value;
	const pw_check = document.body.querySelector("#pw-check").value;
	if(pw === pw_check && (pw * pw_check).length != 0) {
		return 1;
	} else {
		alert("비밀번호가 조건에 부합하지 않습니다");
		e.preventDefault();
	}
})