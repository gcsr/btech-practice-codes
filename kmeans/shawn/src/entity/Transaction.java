/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hungndv
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
	@NamedQuery(name = "Transaction.findById", query = "SELECT t FROM Transaction t WHERE t.id = :id"),
	@NamedQuery(name = "Transaction.findByAmount", query = "SELECT t FROM Transaction t WHERE t.amount = :amount"),
	@NamedQuery(name = "Transaction.findByDatetime", query = "SELECT t FROM Transaction t WHERE t.datetime = :datetime")})
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	@Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
	private int amount;
	@Basic(optional = false)
    @NotNull
    @Column(name = "Datetime")
    @Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	@JoinColumn(name = "FromAccount", referencedColumnName = "id")
    @ManyToOne(optional = false)
	private Account fromAccount;
	@JoinColumn(name = "ToAccount", referencedColumnName = "id")
    @ManyToOne(optional = false)
	private Account toAccount;

	public Transaction() {
	}

	public Transaction(Long id) {
		this.id = id;
	}

	public Transaction(Long id, int amount, Date datetime) {
		this.id = id;
		this.amount = amount;
		this.datetime = datetime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
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
		if (!(object instanceof Transaction)) {
			return false;
		}
		Transaction other = (Transaction) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.Transaction[ id=" + id + " ]";
	}
	
}
