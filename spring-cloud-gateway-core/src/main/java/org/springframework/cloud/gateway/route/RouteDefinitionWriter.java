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

package org.springframework.cloud.gateway.route;

import reactor.core.publisher.Mono;

/**
 * @author Spencer Gibb
 * 通过实现该接口对路由定义进行保存修改
 */
public interface RouteDefinitionWriter {

	/**
	 * 保存路由配置
	 *
	 * @param route 路由配置
	 * @return Mono<Void>
	 */
	Mono<Void> save(Mono<RouteDefinition> route);

	/**
	 * 删除路由配置
	 *
	 * @param routeId 路由编号
	 * @return Mono<Void>
	 */
	Mono<Void> delete(Mono<String> routeId);

}
