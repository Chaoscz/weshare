package com.ws.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jfinal.handler.Handler;

public class WebSocketHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if(target.indexOf("/ws/getsingle") == -1){
			if(target.indexOf("/MP_verify_TfvqjMOIvPJVUR0D") != -1){
				target = "/mp_verify";
			}
			next.handle(target, request, response, isHandled);
		}
	}

}
