package br.com.adda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name="occurrences")
public class Occurrences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2070535942817794052L;

			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	private int categoryId;
	private Long userId;
	
	@Type(type="date")
	private Date occurrenceDate;
	
	private String latitude;
	private String longitude;
	private String description;
	
	public Occurrences(Long id, int categoryId, Long userId, Date occurrenceDate, String latitude, String longitude,
			String description) {
		this.id = id;
		this.categoryId = categoryId;
		this.userId = userId;
		this.occurrenceDate = occurrenceDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
	}

	public Occurrences() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getOccurrenceDate() {
		return occurrenceDate;
	}

	public void setOccurrenceDate(Date occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}