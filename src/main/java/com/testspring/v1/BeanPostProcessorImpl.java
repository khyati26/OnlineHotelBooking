package com.testspring.v1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class BeanPostProcessorImpl implements BeanPostProcessor, Ordered {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("0,PostProcessBeforeInitialization:" + bean + "=" + beanName);
		if (bean instanceof Book) {
			System.out.println("Book Post Befroe processor...");
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("0,PostProcessAfterInitialization:" + bean + "=" + beanName);
		if (bean instanceof Book) {
			System.out.println("Book Post after processor...");
		}
		return bean;
	}

	public int getOrder() {
		return 0;
	}

}
