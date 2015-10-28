package com.pimentoso.games.lib.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;

public class ConnectionChecker implements HttpResponseListener {
	
	private OnConnectionCheckedListener listener;
	
	public interface OnConnectionCheckedListener {
		public void done();
		public void fail();
	}
	
	public ConnectionChecker(OnConnectionCheckedListener listener) {
		this.listener = listener;
	}

	@Override
	public void handleHttpResponse(HttpResponse response) {
		int code = response.getStatus().getStatusCode();
		if (code >= 200 && code < 300) {
			listener.done();
		} 
		else {
			listener.fail();
		}
	}
	
	@Override
	public void failed(Throwable t) {
		listener.fail();
	}
	
	public void check() {
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl("http://www.google.com/");
		request.setTimeOut(3000);
		Gdx.net.sendHttpRequest(request, this);
	}

	@Override
	public void cancelled() {
		listener.fail();
	}
}
