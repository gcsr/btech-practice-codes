/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
	@NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
	@NamedQuery(name = "Account.findByType", query = "SELECT a FROM Account a WHERE a.type = :type"),
	@NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "id")
	private Integer id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fromAccount")
	private Collection<Transaction> sentTransactionCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "toAccount")
	private Collection<Transaction> recievedTransactionCollection;
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "type")
	private int type;
	@Basic(optional = false)
    @NotNull
    @Column(name = "balance")
	private long balance;
	@JoinColumn(name = "user", referencedColumnName = "Username")
    @ManyToOne(optional = false)
	private User user;

	public Account() {
	}

	public Account(Integer id) {
		this.id = id;
	}

	public Account(Integer id, int type, long balance) {
		this.id = id;
		this.type = type;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
		public void credit(int amount){
		setBalance(getBalance() + amount);
	}
	
	public int debit(int amount){
		if (getBalance() < amount){
			return 0; // not enough money
		}
		setBalance(getBalance() - amount);
		return 1; // successfully withdraw
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Account)) {
			return false;
		}
		Account other = (Account) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.Account[ id=" + id + " ]";
	}

	@XmlTransient
	public Collection<Transaction> getSentTransactionCollection() {
		return sentTransactionCollection;
	}

	public void setSentTransactionCollection(Collection<Transaction> transactionCollection) {
		this.sentTransactionCollection = transactionCollection;
	}

	@XmlTransient
	public Collection<Transaction> getRecievedTransactionCollection() {
		return recievedTransactionCollection;
	}

	public void setRecievedTransactionCollection(Collection<Transaction> transactionCollection1) {
		this.recievedTransactionCollection = transactionCollection1;
	}
	
}
