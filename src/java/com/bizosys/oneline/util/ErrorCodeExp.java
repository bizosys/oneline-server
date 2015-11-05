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

package com.bizosys.oneline.util;

public class ErrorCodeExp extends Exception {
	
	private static final long serialVersionUID = 5312518700534135019L;
	public String errorTitle;
	public String errorCode;
	public String errorMsg;
	public String errorType;
	
	public ErrorCodeExp(String errorTitle, String errorCode, String errorMsg, String type) {
		super (errorMsg );
		this.errorTitle = errorTitle;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorType = type;
	}

}
