package br.com.adda.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime occurrenceDate;
	
	private String latitude;
	private String longitude;
	private String description;
	
	@Transient
	private String categoryName;
	
	public Occurrences(Long id, int categoryId, Long userId, LocalDateTime occurrenceDate, String latitude, String longitude,
			String description, String categoryName) {
		this.id = id;
		this.categoryId = categoryId;
		this.userId = userId;
		this.occurrenceDate = occurrenceDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.categoryName = categoryName;
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

	public LocalDateTime getOccurrenceDate() {
		return occurrenceDate;
	}

	public void setOccurrenceDate(LocalDateTime occurrenceDate) {
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}