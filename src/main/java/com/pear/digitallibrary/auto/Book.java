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
@Table(name="Book")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book implements Serializable {
    @Column(name="Isbn", nullable=false, length=255)
	@Id
	private String isbn;
	
	@Column(name="Title", nullable=true, length=255)	
	private String title;
	
	@Column(name="Author", nullable=true, length=255)	
	private String author;
	
	@Column(name="LocatorCode", nullable=true, length=255)	
	private String locatorCode;
	
	@OneToMany(mappedBy="book", targetEntity=BookCopy.class)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@ToString.Exclude
	private Set<BookCopy> bookCopy = new HashSet<>();


	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Book book = (Book) o;
		return getIsbn() != null && Objects.equals(getIsbn(), book.getIsbn());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
