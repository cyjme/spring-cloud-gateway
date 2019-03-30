/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.gateway.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

/**
 * @author Spencer Gibb
 * 从配置文件读取
 *
 * spring:
 *   cloud:
 *     gateway:
 *       routes:
 *       # =====================================
 *       - host_example_to_httpbin=${test.uri}, Host=**.example.org
 *
 *       # =====================================
 *       - id: host_foo_path_headers_to_httpbin
 *         uri: ${test.uri}
 *         predicates:
 *         - Host=**.foo.org
 *         - Path=/headers
 *         - Method=GET
 *         - Header=X-Request-Id, \d+
 *         - Query=foo, ba.
 *         - Query=baz
 *         - Cookie=chocolate, ch.p
 *         - After=1900-01-20T17:42:47.789-07:00[America/Denver]
 *         filters:
 *         - AddResponseHeader=X-Response-Foo, Bar
 *
 *       # =====================================
 *       - id: add_request_header_test
 *         uri: ${test.uri}
 *         predicates:
 *         - Host=**.addrequestheader.org
 *         - Path=/headers
 *         filters:
 *         - AddRequestHeader=X-Request-Foo, Bar
 */
@ConfigurationProperties("spring.cloud.gateway")
@Validated
public class GatewayProperties {

	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * List of Routes.
	 */
	@NotNull
	@Valid
	private List<RouteDefinition> routes = new ArrayList<>();

	/**
	 * List of filter definitions that are applied to every route.
	 */
	private List<FilterDefinition> defaultFilters = new ArrayList<>();

	private List<MediaType> streamingMediaTypes = Arrays
			.asList(MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_STREAM_JSON);

	public List<RouteDefinition> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteDefinition> routes) {
		this.routes = routes;
		if (routes != null && routes.size() > 0 && logger.isDebugEnabled()) {
			logger.debug("Routes supplied from Gateway Properties: " + routes);
		}
	}

	public List<FilterDefinition> getDefaultFilters() {
		return defaultFilters;
	}

	public void setDefaultFilters(List<FilterDefinition> defaultFilters) {
		this.defaultFilters = defaultFilters;
	}

	public List<MediaType> getStreamingMediaTypes() {
		return streamingMediaTypes;
	}

	public void setStreamingMediaTypes(List<MediaType> streamingMediaTypes) {
		this.streamingMediaTypes = streamingMediaTypes;
	}

	@Override
	public String toString() {
		return "GatewayProperties{" + "routes=" + routes + ", defaultFilters="
				+ defaultFilters + ", streamingMediaTypes=" + streamingMediaTypes + '}';
	}

}
