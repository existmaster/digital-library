/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: KAIST
 * License Type: Purchased
 */
package com.pear.digitallibrary.auto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Customer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer implements Serializable {

	@Column(name="SSN", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer SSN;
	
	@Column(name="FName", nullable=true, length=50)	
	private String FName;
	
	@Column(name="LName", nullable=true, length=255)	
	private String LName;
	
	@Column(name="Phone", nullable=true, length=255)	
	private String Phone;
	
	@Column(name="Email", nullable=true, length=255)	
	private String Email;
	
	@Column(name="StreetAddress", nullable=true, length=255)	
	private String StreetAddress;
	
	@Column(name="City", nullable=true, length=255)	
	private String City;
	
	@Column(name="State", nullable=true, length=255)	
	private String State;
	
	@Column(name="Zip", nullable=true, length=255)	
	private String Zip;
	
	@OneToMany(mappedBy="customer", targetEntity=BookLoan.class)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@ToString.Exclude
	private Set<BookLoan> bookLoans = new HashSet<>();

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Customer customer = (Customer) o;
		return getSSN() != null && Objects.equals(getSSN(), customer.getSSN());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
