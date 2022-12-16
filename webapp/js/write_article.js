// 게시글	
const addMaterialBtn = document.body.querySelector("#add-material");
const materialList = document.body.querySelector("#material-list");

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

let sn = 0;
var imageNum = 1;
const addSequenceBtn = document.body.querySelector("#add-sequence");
const sequenceList = document.body.querySelector("#sequence-list");

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
	
	let recipeImageBtn = document.createElement("input");
	recipeImageBtn.classList.add("recipe-item-image");
	recipeImageBtn.name = "recipe-item-image" + imageNum++;
	recipeImageBtn.type = "file";
	recipeImageBtn.accept = "image/*";
	recipeSequenceContent.appendChild(recipeImageBtn);
		
	deleteBtn.addEventListener("click", e => {
		imageNum--;
		recipeSequence.remove();
		if(--sn > 0) {
			let arr = sequenceList.getElementsByClassName("recipe-sequence-step");
			let imgName = sequenceList.getElementsByClassName("recipe-item-image");
			for (let i = 0; i < sn; i++) {
				arr[i].innerHTML = "Step " + (i + 1);
				imgName[i].name = "recipe-item-image" + (i + 1);
			}
		}
	});
});