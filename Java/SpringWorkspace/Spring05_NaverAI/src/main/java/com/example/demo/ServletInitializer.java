package com.example.demo;

import java.util.Collections;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServletInitializer extends SpringBootServletInitializer{
	
	@Bean
	public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
		
		TomcatServletWebServerFactory factory=new TomcatServletWebServerFactory() {
			
			@Override
			protected void postProcessContext(Context ctx) { //anonimous class
				super.postProcessContext(ctx);
				
				JspPropertyGroup jspGroup=new JspPropertyGroup(); 
				jspGroup.addUrlPattern("*.jsp");
				jspGroup.setPageEncoding("UTF-8");
				jspGroup.setScriptingInvalid("true");
				jspGroup.addIncludePrelude("/WEB-INF/views/inc/top.jspf");
				jspGroup.addIncludeCoda("/WEB-INF/views/inc/foot.jspf");
				jspGroup.setTrimWhitespace("true");
				jspGroup.setDefaultContentType("text/html");
				
				JspPropertyGroupDescriptorImpl jspPropertyGroupDescriptor = new JspPropertyGroupDescriptorImpl(
						jspGroup);
				ctx.setJspConfigDescriptor(new JspConfigDescriptorImpl(
						Collections.singletonList(jspPropertyGroupDescriptor), Collections.emptyList()));
			}
		}; //inner class end
		return factory;
	}//---------------------------
}////////////////////////////////
