// 게시글	
const addMaterialBtn = document.body.querySelector("#add-material");
const materialList = document.body.querySelector("#material-list");
let jaeryo = document.body.querySelector("#jaeryo").value;
let image = document.body.querySelector("#image").value;
let contents = document.body.querySelector("#contents").value;

let jaeryolist = jaeryo.split(',');

let imageuploadcount = 1;
let removeBtnCount = 1;
let hiddenCount = 1;
let sn = 0;
var imageNum = 1;
const addSequenceBtn = document.body.querySelector("#add-sequence");
const sequenceList = document.body.querySelector("#sequence-list");
let imagelist = image.split(',');
let contentslist = contents.split(',');

window.onload = function(){	
	for(let i = 0; i < jaeryolist.length-1; i=i+2){
		let recipeMaterial = document.createElement("div");
		recipeMaterial.classList.add("recipe-material");
		recipeMaterial.classList.add("recipe-item");
		materialList.appendChild(recipeMaterial);
		
		let materialFood = document.createElement("input");
		materialFood.classList.add("recipe-item-input");
		recipeMaterial.classList.add("me-2");
		materialFood.name = "recipe-item-material-text";
		materialFood.placeholder = "재료";
		materialFood.value = jaeryolist[i];
		materialFood.required = "required";
		recipeMaterial.appendChild(materialFood);
		
		let materialAmount = document.createElement("input");
		materialAmount.classList.add("recipe-item-input");
		recipeMaterial.classList.add("me-2");
		materialAmount.name = "recipe-item-material-text";
		materialAmount.value = jaeryolist[i+1];
		materialAmount.placeholder = "용량";
		materialAmount.required = "required";
		recipeMaterial.appendChild(materialAmount);
		
		let deleteBtn = document.createElement("div");
		deleteBtn.classList.add("recipe-item-input");
		deleteBtn.classList.add("btn");
		deleteBtn.classList.add("d-inline");
		deleteBtn.innerHTML = "X";
		recipeMaterial.appendChild(deleteBtn);
		
		deleteBtn.addEventListener("click", e => {
			recipeMaterial.remove();
		});
	}
		
		//여기까지 재료용량칸	불러오기
		for(let i = 0; i < contentslist.length; i++){	
			let recipeSequence = document.createElement("div");			//recipe-sequence-item step 한개 전체
			recipeSequence.classList.add("recipe-sequence-item");
			sequenceList.appendChild(recipeSequence);
			
			let recipeSequenceStep = document.createElement("div");		//step 글자
			recipeSequenceStep.classList.add("recipe-sequence-step");
			recipeSequenceStep.classList.add("m-3");
			recipeSequenceStep.classList.add("h5");
			recipeSequenceStep.classList.add("d-print-inline-block");
			recipeSequenceStep.innerHTML = "Step " + (++sn);
			recipeSequence.appendChild(recipeSequenceStep);
			
			let deleteBtn = document.createElement("div");				//x버튼
			deleteBtn.classList.add("btn");
			deleteBtn.classList.add("d-print-inline-block");
			deleteBtn.innerHTML = "X";
			recipeSequence.appendChild(deleteBtn);
			
			let recipeSequenceContent = document.createElement("div");	//내용과 사진
			recipeSequenceContent.classList.add("recipe-sequence-content");
			recipeSequence.appendChild(recipeSequenceContent);
			
			let recipeTextarea = document.createElement("textarea");	//내용 textarea
			recipeTextarea.classList.add("recipe-item-input");
			recipeTextarea.classList.add("me-2");
			recipeTextarea.name = "recipe-item-step";
			recipeTextarea.value = contentslist[i];
			recipeTextarea.required = "required";
			recipeSequenceContent.appendChild(recipeTextarea);
			
			const recipeImageCon = document.createElement("div");		//이미지div
	    	recipeImageCon.classList.add("recipe-item-image");
	    	recipeSequenceContent.appendChild(recipeImageCon);
	    	
	    	const recipeImageLabel = document.createElement("label");	//버튼이름
	    	recipeImageLabel.classList.add("btn");
	    	recipeImageLabel.classList.add("btn-primary");
	    	recipeImageLabel.classList.add("btn-file");
	    	recipeImageLabel.innerText = "파일추가";
	    	recipeImageCon.appendChild(recipeImageLabel);
	    	
	    	const recipeImageBtn = document.createElement("input");		//버튼
	    	recipeImageBtn.classList.add("recipe-item-one-image");
	    	recipeImageBtn.name = "recipe-item-image"+imageNum++;
	    	recipeImageBtn.type = "file";
	    	recipeImageBtn.id = "file"+imageuploadcount++;
	    	recipeImageLabel.appendChild(recipeImageBtn);
	    	
	    	const recipeImageSpan = document.createElement("span");		//이미지 유무 텍스트
	    	recipeImageSpan.innerText=imagelist[i];
	    	recipeImageCon.appendChild(recipeImageSpan);
	    	
	    	const recipeImageDeleteBtn = document.createElement("input");	//이미지 삭제버튼
	    	recipeImageDeleteBtn.type = "button";
	    	recipeImageDeleteBtn.value = "삭제";
	    	recipeImageDeleteBtn.id = "removeBtn"+removeBtnCount++;
	    	recipeImageCon.appendChild(recipeImageDeleteBtn);
	    	
	    	const recipeImageHidden = document.createElement("input");
    		recipeImageHidden.classList.add("recipe-image-hidden");
	    	recipeImageHidden.type = "hidden";
	    	recipeImageHidden.value = imagelist[i];
	    	recipeImageHidden.name = "recipe-image-hidden"+hiddenCount++;
	    	recipeImageCon.appendChild(recipeImageHidden);
	    	
	    	recipeImageBtn.addEventListener('change', _ =>{		//불러온 사진 다른사진으로 변경
	    		for(var i = 1; i <= contentslist.length; i++){
					var recipeImageBtnName = "recipe-item-image"+(i);
					if((recipeImageBtn.name)===(recipeImageBtnName)){
						var btn = recipeImageBtn.value;
			    		var index = btn.lastIndexOf("\\");    		
			    		recipeImageSpan.innerText = btn.substring(index+1);	
			    		break;				
					}					
				}	
	    	});
	    	
	    	recipeImageDeleteBtn.addEventListener('click', _=>{	//이미지 삭제버튼
	    		recipeImageSpan.innerText = "이미지없음";
	    		for(var i = 1; i <= contentslist.length; i++){
					var recipeImageDeleteBtnId = "removeBtn"+(i);
					if((recipeImageDeleteBtn.id)==(recipeImageDeleteBtnId)){
						document.getElementsByName("recipe-item-image"+i).value = "";	//완전히 input file에서 값 삭제
						recipeImageHidden.value = "이미지없음";	//완전히 input file에서 값 삭제				
					}					
				}
	    	});
				
			deleteBtn.addEventListener("click", _ => {					// step한개 삭제
				imageNum--;
				hiddenCount--;
				recipeSequence.remove();
				recipeImageHidden.remove();
				if(--sn > 0) {
					let arr = sequenceList.getElementsByClassName("recipe-sequence-step");
					let imgName = sequenceList.getElementsByClassName("recipe-item-one-image");
					let hiddenImage = document.body.getElementsByClassName("recipe-image-hidden");
					for (let i = 0; i < sn; i++) {
						arr[i].innerHTML = "Step " + (i + 1);
						imgName[i].name = "recipe-item-image"+(i + 1);
						imgName[i].id = "file"+(i + 1);
						hiddenImage[i].name = "recipe-image-hidden" + (i + 1);
					}
				}
			});
		}
		//여기까지가 내용,이미지 불러오기
		
		addMaterialBtn.addEventListener("click", _ => {
		
		let recipeMaterial = document.createElement("div");
		recipeMaterial.classList.add("recipe-material");
		recipeMaterial.classList.add("recipe-item");
		materialList.appendChild(recipeMaterial);
		
		let materialFood = document.createElement("input");
		materialFood.classList.add("recipe-item-input");
		recipeMaterial.classList.add("me-2");
		materialFood.name = "recipe-item-material-text";
		materialFood.placeholder = "재료";
		materialFood.required = "required";
		recipeMaterial.appendChild(materialFood);
		
		let materialAmount = document.createElement("input");
		materialAmount.classList.add("recipe-item-input");
		recipeMaterial.classList.add("me-2");
		materialAmount.name = "recipe-item-material-text";
		materialAmount.placeholder = "용량";
		materialAmount.required = "required";
		recipeMaterial.appendChild(materialAmount);
		
		let deleteBtn = document.createElement("div");
		deleteBtn.classList.add("recipe-item-input");
		deleteBtn.classList.add("btn");
		deleteBtn.classList.add("d-inline");
		deleteBtn.innerHTML = "X";
		recipeMaterial.appendChild(deleteBtn);
		
		deleteBtn.addEventListener("click", e => {
			recipeMaterial.remove();
			});
		});	
		//여기까지가 재료,용량 추가
		
		addSequenceBtn.addEventListener("click", e => {
		
		let recipeSequence = document.createElement("div");
		recipeSequence.classList.add("recipe-sequence-item");
		sequenceList.appendChild(recipeSequence);
		
		let recipeSequenceStep = document.createElement("div");
		recipeSequenceStep.classList.add("recipe-sequence-step");
		recipeSequenceStep.classList.add("m-3");
		recipeSequenceStep.classList.add("h5");
		recipeSequenceStep.classList.add("d-print-inline-block");
		recipeSequenceStep.innerHTML = "Step " + (++sn);
		recipeSequence.appendChild(recipeSequenceStep);
		
		let deleteBtn = document.createElement("div");
		deleteBtn.classList.add("btn");
		deleteBtn.classList.add("d-print-inline-block");
		deleteBtn.innerHTML = "X";
		recipeSequence.appendChild(deleteBtn);
		
		let recipeSequenceContent = document.createElement("div");
		recipeSequenceContent.classList.add("recipe-sequence-content");
		recipeSequence.appendChild(recipeSequenceContent);
		
		let recipeTextarea = document.createElement("textarea");
		recipeTextarea.classList.add("recipe-item-input");
		recipeTextarea.classList.add("me-2");
		recipeTextarea.name = "recipe-item-step";
		recipeTextarea.required = "required";
		recipeSequenceContent.appendChild(recipeTextarea);

		const recipeImageCon = document.createElement("div");		//이미지div
    	recipeImageCon.classList.add("recipe-item-image");
    	recipeSequenceContent.appendChild(recipeImageCon);
    	
    	const recipeImageLabel = document.createElement("label");	//버튼이름
    	recipeImageLabel.classList.add("btn");
    	recipeImageLabel.classList.add("btn-primary");
    	recipeImageLabel.classList.add("btn-file");
    	recipeImageLabel.innerText = "파일추가";
    	recipeImageCon.appendChild(recipeImageLabel);
    	
    	const recipeImageBtn = document.createElement("input");		//버튼
    	recipeImageBtn.classList.add("recipe-item-one-image");
    	recipeImageBtn.name = "recipe-item-image"+imageNum++;
    	recipeImageBtn.type = "file";
    	recipeImageBtn.id = "file"+imageuploadcount++;
    	recipeImageLabel.appendChild(recipeImageBtn);
    	
    	const recipeImageSpan = document.createElement("span");		//이미지 유무 텍스트
    	recipeImageSpan.innerText="이미지없음";
    	recipeImageCon.appendChild(recipeImageSpan);
    	
    	const recipeImageDeleteBtn = document.createElement("input");	//이미지 삭제버튼
    	recipeImageDeleteBtn.type = "button";
    	recipeImageDeleteBtn.value = "삭제";
    	recipeImageCon.appendChild(recipeImageDeleteBtn);
    	
    	const recipeImageHidden = document.createElement("input");
    	recipeImageHidden.classList.add("recipe-image-hidden");
		recipeImageHidden.type = "hidden";
		recipeImageHidden.value = "이미지없음";
		recipeImageHidden.name = "recipe-image-hidden"+hiddenCount++;		
		recipeImageCon.appendChild(recipeImageHidden);
    	
		recipeImageBtn.addEventListener('change', _ =>{		//불러온 사진 다른사진으로 변경
			    console.log(recipeImageBtn.value);
	    		for(var i = 1; i <= imageNum; i++){
					var recipeImageBtnName = "recipe-item-image" + i;
					if((recipeImageBtn.name)==(recipeImageBtnName)){
						var btn = recipeImageBtn.value;
			    		var index = btn.lastIndexOf("\\");    		
			    		recipeImageSpan.innerText = btn.substring(index+1);					
					}					
				}	
	    	});
	    	
    	recipeImageDeleteBtn.addEventListener('click', _=>{	//이미지 삭제버튼
    		recipeImageSpan.innerText = "이미지없음";
    		for(var i = 1; i <= contentslist.length; i++){
				var recipeImageDeleteBtnId = "removeBtn"+(i);
				if((recipeImageDeleteBtn.id)==(recipeImageDeleteBtnId)){
					document.getElementsByName("recipe-item-image"+i).value = "";	//완전히 input file에서 값 삭제
					recipeImageHidden.value = "이미지없음";	//완전히 input file에서 값 삭제				
				}					
			}
    	});
		
		deleteBtn.addEventListener("click", e => {
			imageNum--;
			hiddenCount--;
			recipeSequence.remove();
			recipeImageHidden.remove();
			if(--sn > 0) {
				let arr = sequenceList.getElementsByClassName("recipe-sequence-step");
				let imgName = sequenceList.getElementsByClassName("recipe-item-one-image");
				let hiddenImage = sequenceList.getElementsByClassName("recipe-image-hidden");
				for (let i = 0; i < sn; i++) {
					arr[i].innerHTML = "Step " + (i + 1);
					imgName[i].name = "recipe-item-image"+(i + 1);
					imgName[i].id = "file"+(i + 1);
					hiddenImage[i].name = "recipe-image-hidden" + (i + 1);
					}
				}
			});
		});
}
		//여기까지가 내용,이미지 추가
