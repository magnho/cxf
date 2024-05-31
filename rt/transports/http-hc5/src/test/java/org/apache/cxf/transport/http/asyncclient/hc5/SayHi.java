package org.apache.cxf.transport.http.asyncclient.hc5;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Hi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "hi"
})
@XmlRootElement(name = "SayHi")
public class SayHi {

    @XmlElement(name = "Hi", required = true)
    protected String hi;

    /**
     * Gets the value of the hi property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHi() {
        return hi;
    }

    /**
     * Sets the value of the hi property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHi(String value) {
        this.hi = value;
    }

}
