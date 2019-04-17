package com.cy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cy.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: DemoController
 * @Description: (这里用一句话描述这个类的作用)
 * @author cy
 * @date 2018年8月21日 下午5:33:19
 *
 */
@RestController
@RequestMapping(value = { "/api/demo/" })
@Api(value = "/demo", tags = "Demo接口")
public class DemoController {

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value = "第一个页面", notes = "跳转页面", httpMethod = "POST")
	public String getAllProducts1() {
		Product product = new Product();
		product.setName("七级滤芯净水器");
		product.setId(1L);
		product.setProductClass("seven_filters");
		product.setProductId("T12345");
		return "First";
	}
}
