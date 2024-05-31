package org.apache.cxf.transport.http.asyncclient.hc5;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HiResponse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "hiResponse"
})
@XmlRootElement(name = "SayHiResponse")
public class SayHiResponse {

    @XmlElement(name = "HiResponse", required = true)
    protected String hiResponse;

    /**
     * Gets the value of the hiResponse property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHiResponse() {
        return hiResponse;
    }

    /**
     * Sets the value of the hiResponse property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHiResponse(String value) {
        this.hiResponse = value;
    }

}
