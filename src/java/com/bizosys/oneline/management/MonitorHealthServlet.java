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

package com.bizosys.oneline.management;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bizosys.oneline.util.OnelineServerConstants;
import com.bizosys.oneline.util.ILogger;
import com.bizosys.oneline.util.LoggerFactory;

public class MonitorHealthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static ILogger l = LoggerFactory.getLogger(MonitorHealthServlet.class, OnelineServerConstants.IS_CONSOLE_LOG);


	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		l.info("Health Monitoring booted...");

		try 
		{
			StatCollectorAtInterval.getInstance().register(MetricAvgRollupGate.getInstance());
		} 
		catch (Exception ex) 
		{
			throw new ServletException(ex);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/plain");
		res.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		res.setHeader("Pragma", "no-cache");

		PrintWriter out = res.getWriter();
		try 
		{
			out.write(StatCollectorAtInterval.getInstance().healthCheck('\n'));
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (null != out) 
			{
				out.flush();
				out.close();
			}
		}
	}

}