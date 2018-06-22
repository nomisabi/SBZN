package com.example.demo.dto;


import com.example.demo.model.Allergy;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Medicine;

public class AllergyDTO {
	private Long id;
	private IngredientDTO ingredient;
	private MedicineDTO medicine;
	
	public AllergyDTO() {
		
	}
	
public AllergyDTO(Allergy a) {
		this.id=a.getId();
		if (a.getIngredient()!=null)
			this.ingredient=new IngredientDTO(a.getIngredient());
		if (a.getMedicine()!=null)
			this.medicine=new MedicineDTO(a.getMedicine());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public IngredientDTO getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientDTO ingredient) {
		this.ingredient = ingredient;
	}
	public MedicineDTO getMedicine() {
		return medicine;
	}
	public void setMedicine(MedicineDTO medicine) {
		this.medicine = medicine;
	}
	
	public static Allergy getAllergy(AllergyDTO allergyDTO) {
		Ingredient ing=null;
		if (allergyDTO.getIngredient()!=null) {
			ing= IngredientDTO.getIngredient(allergyDTO.getIngredient());
			return new Allergy(allergyDTO.getId(), ing);
		}
		Medicine med=null;
		if (allergyDTO.getMedicine()!=null) {
			med= MedicineDTO.getMedicine(allergyDTO.getMedicine());
			return new Allergy(allergyDTO.getId(), med);
		}
		return new Allergy();
		
}
	
}
