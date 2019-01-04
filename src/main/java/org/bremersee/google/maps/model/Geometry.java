/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bremersee.google.maps.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The geometry of a geocoding result.
 *
 * @author Christian Bremer
 */
@JsonAutoDetect(
    fieldVisibility = Visibility.ANY,
    getterVisibility = Visibility.NONE,
    isGetterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuppressWarnings("WeakerAccess")
public class Geometry {

  /**
   * The bounding box which can fully contain the returned result (optionally returned). Note that
   * these bounds may not match the recommended viewport. (For example, San Francisco includes the
   * Farallon islands, which are technically part of the city, but probably should not be returned
   * in the viewport.)
   */
  @JsonProperty("bounds")
  private Bounds bounds;

  /**
   * The geocoded latitude/longitude value. For normal address lookups, this field is typically the
   * most important.
   */
  @JsonProperty("location")
  private LatLng location;

  /**
   * The level of certainty of this geocoding result.
   */
  @JsonProperty("location_type")
  private LocationType locationType;

  /**
   * The recommended viewport for displaying the returned result. Generally the viewport is used to
   * frame a result when displaying it to a user.
   */
  @JsonProperty("viewport")
  private Bounds viewport;

}
