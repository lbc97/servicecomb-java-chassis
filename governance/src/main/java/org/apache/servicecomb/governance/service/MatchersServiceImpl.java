/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.governance.service;

import java.util.Map;

import org.apache.servicecomb.governance.marker.GovernanceRequestExtractor;
import org.apache.servicecomb.governance.marker.RequestProcessor;
import org.apache.servicecomb.governance.marker.TrafficMarker;
import org.apache.servicecomb.governance.properties.MatchProperties;

public class MatchersServiceImpl implements MatchersService {
  private final RequestProcessor requestProcessor;

  private final MatchProperties matchProperties;

  public MatchersServiceImpl(RequestProcessor requestProcessor, MatchProperties matchProperties) {
    this.requestProcessor = requestProcessor;
    this.matchProperties = matchProperties;
  }

  @Override
  public boolean checkMatch(GovernanceRequestExtractor governanceRequest, String key) {
    Map<String, TrafficMarker> parsedEntity = matchProperties.getParsedEntity();

    TrafficMarker trafficMarker = parsedEntity.get(key);

    if (trafficMarker == null) {
      return false;
    }

    return trafficMarker.checkMatch(governanceRequest, requestProcessor);
  }
}
