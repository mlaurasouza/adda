package br.com.adda.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sos_contact")
public class SosContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6357955755910006103L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	private Long userId;
	private String name;
	private String phone;
	
	public SosContact(Long id, Long userId, String name, String phone) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
	}


	public SosContact() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
