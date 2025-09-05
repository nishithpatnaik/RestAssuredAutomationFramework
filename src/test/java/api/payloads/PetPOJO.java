package api.payloads;

import java.util.List;

public class PetPOJO
{
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tags>tags;
	private String status;
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setPhotoUrls(List<String>photoUrls)
	{
		this.photoUrls = photoUrls;
	}
	
	public List<String> getPhotoUrls()
	{
		return photoUrls;
	}
	
	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}
	
	public void setCategory(Category category)
	{
		this.category = category;
	}
	
	public Category getCategory()
	{
		return category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	

}
