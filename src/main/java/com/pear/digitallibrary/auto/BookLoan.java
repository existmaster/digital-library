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
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="BookLoan")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookLoan implements Serializable {

	@Column(name="ID", nullable=false, length=10)	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@ManyToOne(targetEntity=BookCopy.class, fetch=FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="CopyNumber", referencedColumnName="CopyNumber", nullable=false) }, foreignKey=@ForeignKey(name="FKBookLoan891115"))
	@ToString.Exclude
	private BookCopy bookCopy;
	
	@ManyToOne(targetEntity=Customer.class, fetch=FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="SSN", referencedColumnName="SSN", nullable=false) }, foreignKey=@ForeignKey(name="FKBookLoan932383"))
	@ToString.Exclude
	private Customer customer;
	
	@Column(name="StartDate", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date StartDate;
	
	@Column(name="EndDate", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date EndDate;
	
	@Column(name="ReturnDate", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date ReturnDate;
	
	@Column(name="Status", nullable=true, length=255)	
	private String Status;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		BookLoan bookLoan = (BookLoan) o;
		return getID() != null && Objects.equals(getID(), bookLoan.getID());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
