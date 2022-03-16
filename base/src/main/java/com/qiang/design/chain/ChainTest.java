package com.qiang.design.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liq
 * @date 2021/6/9 16:22
 */
public class ChainTest {

    public static void main(String[] args) {
        Request request = new Request();
        request.setBody("大家好:) 我是<html>");
        Response response = new Response();
        FiterChain fiterChain = new FiterChain();
        fiterChain.addFiter(new HtmlFiter()).addFiter(new EmojiFiter()).doFiter(request, response);
    }
}

class Request {
    String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

class Response {
    String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}


interface Fiter {
    boolean doFiter(Request request, Response response, FiterChain chain);
}

class HtmlFiter implements Fiter {
    @Override
    public boolean doFiter(Request request, Response response, FiterChain chain) {
        request.setBody(request.getBody().replace("<html>", ""));
        System.out.println(request.getBody());
        chain.doFiter(request, response);
        response.setBody("response html 1");
        System.out.println(response.getBody());
        return true;
    }
}

class EmojiFiter implements Fiter {
    @Override
    public boolean doFiter(Request request, Response response, FiterChain chain) {
        request.setBody(request.getBody().replace(":)", ""));
        System.out.println(request.getBody());
        chain.doFiter(request, response);
        response.setBody("response emoji 2");
        System.out.println(response.getBody());
        return true;
    }
}

class FiterChain {
    List<Fiter> fiterChain = new ArrayList<>();
    int index = 0;

    public FiterChain addFiter(Fiter fiter) {
        fiterChain.add(fiter);
        return this;
    }

    public void doFiter(Request request, Response response) {
        if (index == fiterChain.size()) {
            return;
        }
        Fiter fiter = fiterChain.get(index);
        index++;
        fiter.doFiter(request, response, this);
    }
}
