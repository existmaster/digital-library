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
@Table(name="BookCopy")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookCopy implements Serializable {
	
	@Column(name="CopyNumber", nullable=false, length=10)	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer CopyNumber;
	
	@ManyToOne(targetEntity=Book.class, fetch=FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="Isbn", referencedColumnName="Isbn", nullable=false) }, foreignKey=@ForeignKey(name="FKBookCopy439982"))
	@ToString.Exclude
	private Book book;
	
	@Column(name="PurchaseDate", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date PurchaseDate;
	
	@OneToMany(mappedBy="bookCopy", targetEntity=BookLoan.class)
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
		BookCopy bookCopy = (BookCopy) o;
		return getCopyNumber() != null && Objects.equals(getCopyNumber(), bookCopy.getCopyNumber());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
