/*
 * Copyright 2012 - 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.solr.core.convert;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Test;

/**
 * @author Christoph Strobl
 */
public class DateTimeConvertersTests {

	@Test(expected = IllegalArgumentException.class)
	public void testJodaDateTimeConverterWithNullValue() {
		DateTimeConverters.JodaDateTimeConverter.INSTANCE.convert(null);
	}

	@Test
	public void testJodaDateTimeConverter() {
		DateTime dateTime = new DateTime(2012, 8, 21, 6, 35, 0, DateTimeZone.UTC);
		assertThat(DateTimeConverters.JodaDateTimeConverter.INSTANCE.convert(dateTime))
				.isEqualTo("2012\\-08\\-21T06\\:35\\:00.000Z");
	}

	@Test
	public void testStringToJodaDateTime() {
		String dateString = "2013-06-18T06:00:00Z";
		DateTime dateTime = DateTimeConverters.StringToJodaDateTimeConverter.INSTANCE.convert(dateString);
		assertThat(dateTime).isEqualTo(new DateTime(2013, 6, 18, 6, 0, 0, DateTimeZone.UTC));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateToJodaDateTimeConverterWithNull() {
		DateTimeConverters.DateToJodaDateTimeConverter.INSTANCE.convert(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateToJodaLocalDateTimeConverter() {
		DateTimeConverters.DateToLocalDateTimeConverter.INSTANCE.convert(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStringToJodaDateTimeWithNullValue() {
		DateTimeConverters.StringToJodaDateTimeConverter.INSTANCE.convert(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testJodaLocalDateTimeConverterWithNullValue() {
		DateTimeConverters.JodaLocalDateTimeConverter.INSTANCE.convert(null);
	}

	@Test
	public void testJodaLocalDateTimeConverter() {
		LocalDateTime dateTime = new LocalDateTime(new DateTime(2012, 8, 21, 6, 35, 0, DateTimeZone.UTC).getMillis(),
				DateTimeZone.UTC);
		assertThat(DateTimeConverters.JodaLocalDateTimeConverter.INSTANCE.convert(dateTime))
				.isEqualTo("2012\\-08\\-21T06\\:35\\:00.000Z");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testJavaDateConverterWithNullValue() {
		DateTimeConverters.JavaDateConverter.INSTANCE.convert(null);
	}

	@Test
	public void testJavaDateConverter() {
		DateTime dateTime = new DateTime(2012, 8, 21, 6, 35, 0, DateTimeZone.UTC);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(dateTime.getMillis());

		assertThat(DateTimeConverters.JavaDateConverter.INSTANCE.convert(calendar.getTime()))
				.isEqualTo("2012\\-08\\-21T06\\:35\\:00.000Z");
	}

}
