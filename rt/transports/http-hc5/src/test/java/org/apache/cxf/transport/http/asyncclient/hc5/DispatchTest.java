package org.apache.cxf.transport.http.asyncclient.hc5;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Dispatch;
import jakarta.xml.ws.Response;
import jakarta.xml.ws.Service;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static jakarta.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;
import static org.apache.cxf.testutil.common.TestUtil.getNewPortNumber;
import static org.junit.Assert.assertTrue;

public class DispatchTest {

    public static final String PORT = getNewPortNumber(DispatchTest.class);
    static EchoServiceStub ep;
    static String url;
    static ScheduledExecutorService executor;

    @BeforeClass
    public static void start() throws MalformedURLException {
        url = "http://localhost:" + PORT;
        executor = Executors.newScheduledThreadPool(4);
        ep = new EchoServiceStub(URI.create(url).toURL(), new EchoService() {
            @Override
            public Response<SayHiResponse> sayHiAsync(SayHi parameters) {
                return null;
            }

            @Override
            public Future<?> sayHiAsync(SayHi parameters, AsyncHandler<SayHiResponse> asyncHandler) {
                return null;
            }

            @Override
            public SayHiResponse sayHi(SayHi parameters) {
                SayHiResponse response = new SayHiResponse();
                response.setHiResponse(parameters.getHi());
                return response;
            }
        });
    }

    @AfterClass
    public static void stop() {
        executor.shutdown();
        ep.close();
    }

    @Test
    public void testWsdlBasedDispatch() throws Exception {
        URL wsdlUrl = getClass().getClassLoader().getResource("echo_service.wsdl");
        Service service = Service.create(wsdlUrl, new QName("http://www.bccs.uib.no/EchoService.wsdl", "EchoService"));

        Dispatch<SOAPMessage> client = service.createDispatch(
                new QName("http://www.bccs.uib.no/EchoService.wsdl", "EchoService"),
                SOAPMessage.class,
                Service.Mode.MESSAGE
        );
        client.getRequestContext().put(ENDPOINT_ADDRESS_PROPERTY, url);

        List<Future<Response<SOAPMessage>>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(executor.submit(() -> client.invokeAsync(buildMessage())));
        }

        for (Future<Response<SOAPMessage>> task : tasks) {
            SOAPMessage result = task.get(5, TimeUnit.SECONDS).get();
            verifyResult(result);
        }
    }

    private void verifyResult(SOAPMessage message) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(message.getSOAPBody().extractContentAsDocument()), new StreamResult(writer));
        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        assertTrue(output.contains("Hello"));
    }

    private SOAPMessage buildMessage() throws SOAPException, IOException {
        String soapMessage = """
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ech="http://www.bccs.uib.no/EchoService.wsdl"><soapenv:Header/><soapenv:Body><ech:SayHi><Hi>Hello</Hi></ech:SayHi></soapenv:Body></soapenv:Envelope>
                """.trim();
        ByteArrayInputStream bis = new ByteArrayInputStream(soapMessage.getBytes());
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, bis);
        message.saveChanges();
        bis.close();
        return message;
    }
}
