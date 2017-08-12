/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author james
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentId = :paymentId")
    , @NamedQuery(name = "Payment.findByCardNumber", query = "SELECT p FROM Payment p WHERE p.cardNumber = :cardNumber")
    , @NamedQuery(name = "Payment.findByCardType", query = "SELECT p FROM Payment p WHERE p.cardType = :cardType")
    , @NamedQuery(name = "Payment.findByHolderName", query = "SELECT p FROM Payment p WHERE p.holderName = :holderName")
    , @NamedQuery(name = "Payment.findByHolderAddressLine1", query = "SELECT p FROM Payment p WHERE p.holderAddressLine1 = :holderAddressLine1")
    , @NamedQuery(name = "Payment.findByHolderAddressLine2", query = "SELECT p FROM Payment p WHERE p.holderAddressLine2 = :holderAddressLine2")
    , @NamedQuery(name = "Payment.findByHolderPostcode", query = "SELECT p FROM Payment p WHERE p.holderPostcode = :holderPostcode")
    , @NamedQuery(name = "Payment.findByHolderTownCity", query = "SELECT p FROM Payment p WHERE p.holderTownCity = :holderTownCity")
    , @NamedQuery(name = "Payment.findByHolderCountyState", query = "SELECT p FROM Payment p WHERE p.holderCountyState = :holderCountyState")
    , @NamedQuery(name = "Payment.findByHolderCountry", query = "SELECT p FROM Payment p WHERE p.holderCountry = :holderCountry")
    , @NamedQuery(name = "Payment.findByExpiryDate", query = "SELECT p FROM Payment p WHERE p.expiryDate = :expiryDate")
    , @NamedQuery(name = "Payment.findByCVCNumber", query = "SELECT p FROM Payment p WHERE p.cVCNumber = :cVCNumber")
    , @NamedQuery(name = "Payment.findByPaymentAmount", query = "SELECT p FROM Payment p WHERE p.paymentAmount = :paymentAmount")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PaymentId")
    private Integer paymentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CardNumber")
    private int cardNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CardType")
    private String cardType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HolderName")
    private String holderName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HolderAddressLine1")
    private String holderAddressLine1;
    @Size(max = 45)
    @Column(name = "HolderAddressLine2")
    private String holderAddressLine2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "HolderPostcode")
    private String holderPostcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HolderTownCity")
    private String holderTownCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HolderCountyState")
    private String holderCountyState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HolderCountry")
    private String holderCountry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ExpiryDate")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CVCNumber")
    private String cVCNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PaymentAmount")
    private BigDecimal paymentAmount;
    @JoinColumn(name = "Booking_BookingId", referencedColumnName = "BookingId")
    @ManyToOne(optional = false)
    private Booking bookingBookingId;
    @JoinColumn(name = "Customer_CustomerId", referencedColumnName = "CustomerId")
    @ManyToOne(optional = false)
    private Customer customerCustomerId;

    public Payment() {
    }

    public Payment(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Payment(Integer paymentId, int cardNumber, String cardType, String holderName, String holderAddressLine1, String holderPostcode, String holderTownCity, String holderCountyState, String holderCountry, Date expiryDate, String cVCNumber, BigDecimal paymentAmount) {
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.holderName = holderName;
        this.holderAddressLine1 = holderAddressLine1;
        this.holderPostcode = holderPostcode;
        this.holderTownCity = holderTownCity;
        this.holderCountyState = holderCountyState;
        this.holderCountry = holderCountry;
        this.expiryDate = expiryDate;
        this.cVCNumber = cVCNumber;
        this.paymentAmount = paymentAmount;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderAddressLine1() {
        return holderAddressLine1;
    }

    public void setHolderAddressLine1(String holderAddressLine1) {
        this.holderAddressLine1 = holderAddressLine1;
    }

    public String getHolderAddressLine2() {
        return holderAddressLine2;
    }

    public void setHolderAddressLine2(String holderAddressLine2) {
        this.holderAddressLine2 = holderAddressLine2;
    }

    public String getHolderPostcode() {
        return holderPostcode;
    }

    public void setHolderPostcode(String holderPostcode) {
        this.holderPostcode = holderPostcode;
    }

    public String getHolderTownCity() {
        return holderTownCity;
    }

    public void setHolderTownCity(String holderTownCity) {
        this.holderTownCity = holderTownCity;
    }

    public String getHolderCountyState() {
        return holderCountyState;
    }

    public void setHolderCountyState(String holderCountyState) {
        this.holderCountyState = holderCountyState;
    }

    public String getHolderCountry() {
        return holderCountry;
    }

    public void setHolderCountry(String holderCountry) {
        this.holderCountry = holderCountry;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCVCNumber() {
        return cVCNumber;
    }

    public void setCVCNumber(String cVCNumber) {
        this.cVCNumber = cVCNumber;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Booking getBookingBookingId() {
        return bookingBookingId;
    }

    public void setBookingBookingId(Booking bookingBookingId) {
        this.bookingBookingId = bookingBookingId;
    }

    public Customer getCustomerCustomerId() {
        return customerCustomerId;
    }

    public void setCustomerCustomerId(Customer customerCustomerId) {
        this.customerCustomerId = customerCustomerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Payment[ paymentId=" + paymentId + " ]";
    }
    
}
