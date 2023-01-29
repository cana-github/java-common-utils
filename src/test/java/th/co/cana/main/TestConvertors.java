/*
* -----------------------------------------------------------------------------------
* Copyright © 2020 by Cana enterprise co,.Ltd. All rights reserved.
* -----------------------------------------------------------------------------------
*/
package th.co.cana.main;

import th.co.cana.framework.utils.Convertors;

/**
* @author supot
* @version 1.0
*/
public class TestConvertors {

	public static void main(String[] args) {
		System.out.println(Double.MAX_VALUE);
		System.out.println("1 : " + Convertors.toBoolean("1"));
		System.out.println("true : " + Convertors.toBoolean(true));
		System.out.println("Boolean : " + Convertors.toBoolean(Boolean.TRUE));
	}

}
