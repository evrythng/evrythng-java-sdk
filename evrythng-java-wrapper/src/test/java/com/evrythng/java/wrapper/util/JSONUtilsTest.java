/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link JSONUtils}.
 */
public class JSONUtilsTest {

	private static class MyObject {

		private String myProp;
		private Integer b;
		private MySubObject sub;

		public String getMyProp() {
			return myProp;
		}

		public void setMyProp(final String myProp) {
			this.myProp = myProp;
		}

		public Integer getB() {
			return b;
		}

		public void setB(final Integer b) {
			this.b = b;
		}

		public MySubObject getSub() {
			return sub;
		}

		public void setSub(final MySubObject sub) {
			this.sub = sub;
		}
	}

	private static class MySubObject {

		private Integer x;
		private Integer y;

		public Integer getX() {
			return x;
		}

		public void setX(final Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(final Integer y) {
			this.y = y;
		}
	}

	@Test
	public void deserialize() {

		String json = "{\"myProp\":\"   1 \",\"b\":2, \"sub\": {\"x\":14,\"y\":33} }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals(Integer.valueOf(2), o.getB());
		Assert.assertNotNull(o.getSub());
		Assert.assertSame(MySubObject.class, o.getSub().getClass());
		Assert.assertEquals(Integer.valueOf(14), o.getSub().getX());
		Assert.assertEquals(Integer.valueOf(33), o.getSub().getY());
	}

	@Test
	public void deserializeLess() {

		String json = "{\"myProp\":\"1\" }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertNull(o.getB());
		Assert.assertNull(o.getSub());
	}

	@Test
	public void deserializeLessInSubObject() {

		String json = "{\"myProp\":\"1\",\"b\":2, \"sub\": {\"y\":33} }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals(Integer.valueOf(2), o.getB());
		Assert.assertNotNull(o.getSub());
		Assert.assertSame(MySubObject.class, o.getSub().getClass());
		Assert.assertNull(o.getSub().getX());
		Assert.assertEquals(Integer.valueOf(33), o.getSub().getY());
	}

	@Test
	public void deserializeNoneInSubObject() {

		String json = "{\"myProp\":\"1\",\"b\":2, \"sub\": {} }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals(Integer.valueOf(2), o.getB());
		Assert.assertNotNull(o.getSub());
		Assert.assertSame(MySubObject.class, o.getSub().getClass());
		Assert.assertNull(o.getSub().getX());
		Assert.assertNull(o.getSub().getY());
	}

	@Test
	public void deserializeMore() {

		String json = "{\"myProp\":\"1\",\"b\":2,\"sub\": {\"x\":14,\"y\":33},\"unexpected\":\"3\", \"w\":{\"y\":42}}";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals(Integer.valueOf(2), o.getB());
		Assert.assertNotNull(o.getSub());
		Assert.assertSame(MySubObject.class, o.getSub().getClass());
		Assert.assertEquals(Integer.valueOf(14), o.getSub().getX());
		Assert.assertEquals(Integer.valueOf(33), o.getSub().getY());
	}

	@Test
	public void deserializeMoreInSubObject() {

		String json = "{\"myProp\":\"1\",\"b\":2, \"sub\": {\"x\":14,\"y\":33,\"u\":12} }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals(Integer.valueOf(2), o.getB());
		Assert.assertNotNull(o.getSub());
		Assert.assertSame(MySubObject.class, o.getSub().getClass());
		Assert.assertEquals(Integer.valueOf(14), o.getSub().getX());
		Assert.assertEquals(Integer.valueOf(33), o.getSub().getY());
	}

	@Test(expected = Exception.class)
	public void deserializeBadType() {

		String json = "{\"myProp\":\"1\",\"b\":\"a\", \"sub\": {\"x\":14,\"y\":33} }";

		JSONUtils.read(json, new TypeReference<MyObject>() {
		});
	}

	@Test(expected = Exception.class)
	public void deserializeBadSubType() {

		String json = "{\"myProp\":\"1\",\"b\":2, \"sub\": {\"x\":\"a\",\"y\":33} }";

		JSONUtils.read(json, new TypeReference<MyObject>() {
		});
	}

	@Test
	public void serialize() {

		MyObject exp = new MyObject();
		exp.setMyProp("1");
		exp.setB(2);

		String json = JSONUtils.write(exp);

		MyObject act = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals(exp.getMyProp(), act.getMyProp());
		Assert.assertEquals(exp.getB(), act.getB());
	}

	@Test
	public void serializeLess() {

		MyObject exp = new MyObject();
		exp.setMyProp("1");

		String json = JSONUtils.write(exp);

		Assert.assertFalse(json.contains("\"b\""));
		Assert.assertFalse(json.contains("\"sub\""));

		MyObject act = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals(exp.getMyProp(), act.getMyProp());
		Assert.assertEquals(exp.getB(), act.getB());
	}
}
