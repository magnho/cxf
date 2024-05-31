package org.apache.cxf.transport.http.asyncclient.hc5;

import jakarta.jws.WebService;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Response;

import java.io.Closeable;
import java.net.URL;
import java.util.concurrent.Future;

@WebService(endpointInterface = "org.apache.cxf.transport.http.asyncclient.hc5.EchoService")
class EchoServiceStub implements EchoService, Closeable {

    private EchoService adapter;
    private Endpoint endpoint;

    public EchoServiceStub(URL address, EchoService adapter) {
        this.adapter = adapter;
        this.endpoint = Endpoint.publish(address.toString(), this);
    }

    public void stop() {
        endpoint.stop();
    }

    public void close() {
        stop();
    }

    @Override
    public Response<SayHiResponse> sayHiAsync(SayHi request) {
        return adapter.sayHiAsync(request);
    }

    @Override
    public Future<?> sayHiAsync(SayHi request, AsyncHandler<SayHiResponse> asyncHandler) {
        return adapter.sayHiAsync(request, asyncHandler);
    }

    @Override
    public SayHiResponse sayHi(SayHi request) {
        return adapter.sayHi(request);
    }
}
