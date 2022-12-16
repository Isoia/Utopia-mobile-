package dto;

public class IngredientDTO {
	private int ingredientId;
	private String ingredientName;
	private int ingredientCal;
	private int ingredientStandardCal;
	
	public IngredientDTO(int ingredientId, String ingredientName, int ingredientCal, int ingredientStandardCal) {
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientCal = ingredientCal;
		this.ingredientStandardCal = ingredientStandardCal;
	}
	
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public int getIngredientCal() {
		return ingredientCal;
	}
	public void setIngredientCal(int ingredientCal) {
		this.ingredientCal = ingredientCal;
	}
	public int getIngredientStandardCal() {
		return ingredientStandardCal;
	}
	public void setIngredientStandardCal(int ingredientStandardCal) {
		this.ingredientStandardCal = ingredientStandardCal;
	}
	
	
}
