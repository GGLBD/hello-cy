package com.cy.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cy.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: ProductController
 * @Description: (接口展示样例)
 * @author cy
 * @date 2018年1月15日 上午11:19:50
 *
 */
@RestController
@RequestMapping(value = { "/api/product/" })
@Api(value = "/product", tags = "Product接口")
public class ProductController {

	@RequestMapping(value = "{id}/{dowkd}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id获取产品信息", notes = "根据id获取产品信息", httpMethod = "GET", response = Product.class)
	public ResponseEntity<Product> get(@PathVariable Long id,@PathVariable String dowkd) {
		Product product = new Product();
		product.setName("七级滤芯净水器");
		product.setId(id);
		product.setProductClass(dowkd);
		product.setProductId("T12345");
		return ResponseEntity.ok(product);
	}

	@RequestMapping(value = "{produceName}/{produceId}/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "添加一个新的产品")
	// @ApiResponses(value = { @ApiResponse(code = 405, message = "参数错误") })
	public ResponseEntity<Product> add(@PathVariable String produceName, @PathVariable String produceId, @PathVariable Long id) {
		Product product = new Product();
		product.setName(produceName);
		product.setId(id);
		product.setProductClass("seven_filters");
		product.setProductId(produceId);
		return ResponseEntity.ok(product);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "更新一个产品")
	// @ApiResponses(value = { @ApiResponse(code = 400, message = "参数错误") })
	public ResponseEntity<String> update(Product product) {
		return ResponseEntity.ok("SUCCESS");
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "获取所有产品信息", notes = "获取所有产品信息", httpMethod = "GET", response = Product.class, responseContainer = "List")
	public ResponseEntity<List<Product>> getAllProducts() {
		Product product = new Product();
		product.setName("七级滤芯净水器");
		product.setId(1L);
		product.setProductClass("seven_filters");
		product.setProductId("T12345");
		return ResponseEntity.ok(Arrays.asList(product, product));
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value = "跳转页面", notes = "跳转页面", httpMethod = "POST")
	public String getAllProducts1() {
		Product product = new Product();
		product.setName("七级滤芯净水器");
		product.setId(1L);
		product.setProductClass("seven_filters");
		product.setProductId("T12345");
		return "admin-user";
	}

}
