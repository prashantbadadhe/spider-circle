package com.circle.model.organization;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.JoinColumn;
@Entity
@Table(name = "CONTACT_DETAIL")
public class ContactDetail {
 
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="ID")
		private long id;
		@Column(name="FIRST_NAME")
		private String firstName;
		@Column(name="MIDDLE_NAME")
		private String middleName;
		@Column(name="LAST_NAME")
		private String lastName;
		@Column(name="NICK_NAME")
		private String nickName;
		@Column(name="FIRST_PHONE")
		private String firstPhone;
		@Column(name="SECOND_PHONE")
		private String secondPhone;
		@Column(name="FIRST_MAIL")
		private String email;
		@Column(name="PROFILE_IMAGE" )
		private byte[] profileImage;
		@Column(name="ADDITIONAL_INFO")
		private String additionalInfo;
		//Record's address
		@Column(name="ADDRESS")
		private String address;
		@Column(name="LOCATIONS")
		@Embedded
		 @ElementCollection( fetch=FetchType.EAGER)
		 @CollectionTable( name="CONTACT_LOCATION",  joinColumns=@JoinColumn(name="LOCATION") )
		private Set<Location> locationDetails= new HashSet<>();
		//Every contact details belongs to any client. This will is client id
		@Column(name="CLIENT_ID")
		private long clientId;
		@Column(name = "CREATED_AT")
		@Temporal(TemporalType.TIMESTAMP)
		private Date createdAt;
		@Column(name = "CREATED_BY")
		private String createdBy;
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "UPDATED_AT")
		private Date updatedAt;
		@Column(name = "UPDATED_BY")
		private String updatedBy;
		@Column(name = "STATUS")
		private char status;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getNickName() {
			return nickName;
		}
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public String getFirstPhone() {
			return firstPhone;
		}
		public void setFirstPhone(String firstPhone) {
			this.firstPhone = firstPhone;
		}
		public String getSecondPhone() {
			return secondPhone;
		}
		public void setSecondPhone(String secondPhone) {
			this.secondPhone = secondPhone;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public byte[] getProfileImage() {
			return profileImage;
		}
		public void setProfileImage(byte[] profileImage) {
			this.profileImage = profileImage;
		}
		public String getAdditionalInfo() {
			return additionalInfo;
		}
		public void setAdditionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
 
 
		public Set<Location> getLocationDetails() {
			return locationDetails;
		}
		public void setLocationDetails(Set<Location> locationDetails) {
			this.locationDetails = locationDetails;
		}
		public long getClientId() {
			return clientId;
		}
		public void setClientId(long clientId) {
			this.clientId = clientId;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public char getStatus() {
			return status;
		}
		public void setStatus(char status) {
			this.status = status;
		}

		
}
