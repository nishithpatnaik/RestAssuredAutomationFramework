package api.payloads;

public class StorePOJO_payload {
	
	/*
	 * {
  "id": 0,
  "petId": 0,
  "quantity": 0,
  "shipDate": "2025-08-16T22:48:37.779Z",
  "status": "placed",
  "complete": true
}
	 * 
	 * 
	 * */

	private Integer id;
	private Integer petId;
	private Integer quantity;
	private String shipDate="2025-08-17T02:56:20.020+0000";
	private String status;
	private Boolean complete = true;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getShipDate() {
		return shipDate;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
}
