/**
* Copyright 2010 Bizosys Technologies Limited
*
* Licensed to the Bizosys Technologies Limited (Bizosys) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The Bizosys licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.bizosys.oneline.user;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
public class PasswordGenerator 
{
	public int length;
	public boolean lowercaseIncluded;
	public boolean numbersIncluded;
	public boolean othersIncluded;
	public boolean uppercaseIncluded;

	private String password;
	private String template;

	public PasswordGenerator() {
		// by default, invlude lowercase, uppercase, and numbers
		// in the password, and make it 8 characters long.
		password = "";
		template = "";
		length = 8;
		lowercaseIncluded = true;
		uppercaseIncluded = true;
		numbersIncluded = true;
		othersIncluded = false;
		
		// start the ball rolling by generating a password so that
		// we keep our data integrity 
		// i.e. so length matches password.length());
		generatePassword();
	}

	/**
	 * @return true if at least one of the password generation flags
	 * is true, otherwise returns false
	 */
	private boolean flagsOK() {
		return lowercaseIncluded
			|| uppercaseIncluded
			|| numbersIncluded
			|| othersIncluded;
	}

	/**
	 * @return a random lowercase character from 'a' to 'z'
	 */
	private static char randomLowercase() {
		return (char) (97 + (int) (Math.random() * 26));
	}

	/**
	 * @return a random uppercase character from 'A' to 'Z'
	 */
	private static char randomUppercase() {
		return (char) (65 + (int) (Math.random() * 26));
	}

	/**
	 * @return a random character in this list: !"#$%&'()*+,-./
	 */
	private static char randomOther() {
		return (char) (33 + (int) (Math.random() * 15));
	}

	
	/**
	 * @return a random character from '0' to '9'
	 */
	private static char randomNumber() {
		return (char) (48 + (int) (Math.random() * 10));
	}

	public void generatePassword() /* throws InvalidPreferencesFormatException, ParseException */ {
		// clear password if necessary
		if (password.length() != 0) {
			password = "";
		}

		// check to make sure at least one "type" is included
		// for password generation if template is not defined
		
		// commented out because our setters/construcor should
		// ensure data integrity
		//if (!flagsOK() && template.length() == 0) {
		//	throw new java.util.prefs.InvalidPreferencesFormatException("At least one flag must be on to generate a password");
		//}
		

		// we know length >= 1 here because setLength
		// doesn't allow invalid lengths and the constructor
		// initializes length to 8

		// a template being defined overrides all flags
		if (template.length() > 0) {
			length = template.length();
			for (int i = 0; i < length; i++) {
				switch (template.charAt(i)) {
					case 'a' :
						password += randomLowercase();
						break;

					case 'A' :
						password += randomUppercase();
						break;

					case 'n' :
					case 'N' :
						password += randomNumber();
						break;

					case 'o' :
					case 'O' :
						password += randomOther();
						break;

					// commented out because our setters/constructor
					// should ensure data integrity
					//default :
					//	throw new ParseException("Password template contains an invalid character", i);
				}
			}
		} else {
			// In Java we can't create an array of function references
			// so I've created 4 "wrapper" classes that inherit from the
			// randomCharacter interface to provide the same
			// type of functionality.
			
			
			// create an ArrayList to store the functions that we're allowed
			// to call to generate the password, based on what the flags
			// are set to.
			List<randomCharacter> flags = new ArrayList<randomCharacter>();
			if (lowercaseIncluded) {
				flags.add(new randomLowercase());
			}
			if (uppercaseIncluded) {
				flags.add(new randomUppercase());	
			}
			if (othersIncluded) {
				flags.add(new randomOther());	
			}
			if (numbersIncluded) {
				flags.add(new randomNumber());	
			}
			
			int flagLength = flags.size();
			
			for (int i = 0; i < length; i++) {
				// get a random wrapper class from the flags ArrayList
				// and cast it to our interface so we can call the execute method
				// which just calls the function and returns its value.
				password += ((randomCharacter)flags.get((int)(Math.random() * flagLength))).execute();
			}
		}
	}

	/**
	 * @return the length of the generated password
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the generated password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return password template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @return lowercaseIncluded
	 */
	public boolean isLowercaseIncluded() {
		return lowercaseIncluded;
	}

	/**
	 * @return numbersIncluded
	 */
	public boolean isNumbersIncluded() {
		return numbersIncluded;
	}

	/**
	 * @return othersIncluded
	 */
	public boolean isOthersIncluded() {
		return othersIncluded;
	}

	/**
	 * @return uppercaseIncluded
	 */
	public boolean isUppercaseIncluded() {
		return uppercaseIncluded;
	}

	/**
	 * @param length, enforced to be a positive integer >= 3.
	 */
	public void setLength(int length) {
		this.length = (length < 3) ? 3 : length;
	}

	/**
	 * @param b
	 */
	public void setLowercaseIncluded(boolean b)  {
		lowercaseIncluded = b;

		// did we turn off the last flag?  if so
		// turn it back on and report error
		if (b == false && !flagsOK()) {
			lowercaseIncluded = true;
			throw new RuntimeException("At least one flag must be on to generate a password");
		}
	}

	/**
	 * @param b
	 */
	public void setNumbersIncluded(boolean b) {
		numbersIncluded = b;

		// did we turn off the last flag?  if so
		// turn it back on and report error
		if (b == false && !flagsOK()) {
			numbersIncluded = true;
			throw new RuntimeException("At least one flag must be on to generate a password");
		}
	}

	/**
	 * @param b
	 */
	public void setOthersIncluded(boolean b) {
		othersIncluded = b;

		// did we turn off the last flag?  if so
		// turn it back on and report error
		if (b == false && !flagsOK()) {
			othersIncluded = true;
			throw new RuntimeException("At least one flag must be on to generate a password");
		}
	}

	/**
	 * @param string
	 */
	public void setTemplate(String template) throws ParseException {
		// make sure the template contains only legal characters
		for (int i = 0; i < template.length(); i++) {
			switch (template.charAt(i)) {
				case 'a' :
				case 'A' :
				case 'n' :
				case 'N' :
				case 'o' :
				case 'O' :
					break;
					
				default :
					throw new ParseException("Password template contains an invalid character", i);
			}
		}
		this.template = template;
	}
	
	/**
	 * Clears the password template,making generation rely on the flags.
	 *
	 */
	public void clearTemplate() {
		template = "";	
	}

	/**
	 * @param b
	 */
	public void setUppercaseIncluded(boolean b) {
		uppercaseIncluded = b;

		// did we turn off the last flag?  if so
		// turn it back on and report error
		if (b == false && !flagsOK()) {
			uppercaseIncluded = true;
			throw new RuntimeException("At least one flag must be on to generate a password");		
		}
	}

	/*--------------------------------------------------------
	 Wrapper classes and interface to mimic the array of 
	 function references functionality required.
 	----------------------------------------------------------*/
 	private static class randomLowercase implements randomCharacter {
		public char execute() {
			return PasswordGenerator.randomLowercase();
		}
	}

	private static class randomUppercase implements randomCharacter {
		public char execute() {
			return PasswordGenerator.randomUppercase();
		}
	}

	private static class randomOther implements randomCharacter {
		public char execute() {
			return PasswordGenerator.randomOther();
		}
	}

	private static class randomNumber implements randomCharacter {
		public char execute() {
			return PasswordGenerator.randomNumber();
		}
	}

	private static interface randomCharacter {
		char execute();
	}
	
	public static void main(String[] args) throws Exception
	{
		PasswordGenerator gen = new PasswordGenerator();
		gen.length = 8;
		gen.lowercaseIncluded = false;
		
		int loop = 0;
		while( loop < 1000)
		{
			gen.generatePassword();
			String password = gen.getPassword();
			if (password.indexOf("0") >=0) continue;
			if (password.indexOf("O") >=0) continue;
			if (password.indexOf("1") >=0) continue;
			if (password.indexOf("I") >=0) continue;
			System.out.println(password);
			loop++;
		}
	}

	public static void main1(String[] args) throws Exception
	{
		PasswordGenerator gen = new PasswordGenerator();
		gen.length = 11;
		gen.lowercaseIncluded = false;
		gen.generatePassword();
		System.out.println(gen.getPassword());
		gen.generatePassword();
		System.out.println(gen.getPassword());
	}
}
