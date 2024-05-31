
package org.apache.cxf.transport.http.asyncclient.hc5;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.apache.cxf.transport.http.asyncclient.hc5
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHi }
     *
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link SayHiResponse }
     *
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

}
